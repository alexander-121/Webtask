package app.controller;

import app.entity.Grp;
import app.entity.Student;
import app.service.GrpService;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Главный контроллер приложения
 * @see app.App
 */

@ComponentScan({"app.service"})
@Controller
public class MainController {

    /**
     * Поле GrpService
     * @see GrpService
     */
    @Autowired
    private GrpService grpService;

    /**
     * Поле StudentService
     * @see StudentService
     */
    @Autowired
    private StudentService stdService;

    //Страницы для сущности "Группа"

    /**
     * Обработка запроса к главной странице
     * @return index.jsp
     * @see "webapp/WEB-INF/pages/index.jsp"
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * Заполнение таблицы всеми группами
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return grouppage.jsp
     * @see "webapp/WEB-INF/pages/grouppage.jsp"
     */
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String viewgrp(Model m) {
        m.addAttribute("command", new Grp());
        List<Grp> grp = grpService.getAll();
        m.addAttribute("grp", grp);
        return "grouppage";
    }

    /**
     * Удаление группы по её id
     * @param id long
     * @return redirect:/groups
     * @see "webapp/WEB-INF/pages/grouppage.jsp"
     */
    @RequestMapping(value="/deletegrp/{id}",method = RequestMethod.GET)
    public String deletegrp(@PathVariable long id){
        List<Student> std = stdService.getByGroup(grpService.getById(id));
        for (Student student : std)
        {
            student.setGrp(null);
            stdService.updateStudentGrp(student.getGrp(), student.getId());
        }
        grpService.deleteGroup(id);
        return "redirect:/groups";
    }

    /**
     * Открытие страницы для добавления группы
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return addgroup.jsp
     * @see "webapp/WEB-INF/pages/addgroup.jsp"
     */
    @RequestMapping("/addgroup")
    public String showeditgrp(Model m){
        m.addAttribute("command", new Grp());
        return "addgroup";
    }

    /**
     * Созранение новой группы по введенным параметрам и сохранение её в БД
     * @param grp Grp grp
     * @see Grp
     * @return redirect:/groups
     * @see "webapp/WEB-INF/pages/grouppage.jsp"
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String savegrp(@ModelAttribute("grp") Grp grp){
        grpService.addGroup(grp);
        return "redirect:/groups";
    }

    /**
     * Поиск групп по id, заданному имени и/или факультету
     * @param grp Grp grp
     * @see Grp
     * @param m Model m
     * @see org.springframework.ui.Model
     * @see MainController#findassis(Grp)
     * @return grouppage.jsp
     * @see "webapp/WEB-INF/pages/grouppage.jsp"
     */
    @RequestMapping(value="/find",method = RequestMethod.POST)
    public String findgrp(@ModelAttribute("grp") Grp grp,  Model m){
        m.addAttribute("command", new Grp());
        List<Grp> grps = new ArrayList<>();
        if (grp.getId() != 0)
        {
            if (grpService.getById(grp.getId()) != null) {
                grps.add(grpService.getById(grp.getId()));
            }
            else
            {
                grps = findassis(grp);
            }
        }
        else {
            grps = findassis(grp);
        }
        try {
            m.addAttribute("grp", grps);
        }
        catch (java.lang.NumberFormatException e)
        {
            return "grouppage";
        }
        return "grouppage";
    }

    /**
     * Заполнение формы редактирования парамтрами группы по её id
     * @param id long id
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return editgroup.jsp
     * @see "webapp/WEB-INF/pages/editgroup.jsp"
     */
    @RequestMapping(value="/editgrp/{id}")
    public String editgrp(@PathVariable long id, Model m){
        Grp grp = grpService.getById(id);
        m.addAttribute("command",grp);
        return "editgroup";
    }

    /**
     * Обновление параметров группы после редактирования
     * @param grp Grp grp
     * @see Grp
     * @return redirect:/groups
     * @see "webapp/WEB-INF/pages/grouppage.jsp"
     */
    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsavegrp(@ModelAttribute("grp") Grp grp){
        grpService.updateGroup(grp);
        return "redirect:/groups";
    }

    //Страницы для сущности "Студент"

    /**
     * Заполнение таблицы всеми студентами
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return studentpage.jsp
     * @see "webapp/WEB-INF/pages/studentpage.jsp"
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String viewstd(Model m) {
        m.addAttribute("command", new Student());
        List<Grp> grp = grpService.getAll();
        m.addAttribute("grp", grp);
        List<Student> std = stdService.getAll();
        for (Student student : std)
        {
            if (student.getGrp() == null)
            {
                student.setGrp(new Grp("",""));
            }
        }
        m.addAttribute("std", std);
        return "studentpage";
    }

    /**
     * Открытие страницы для добавления нового студента
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return addstudent.jsp
     * @see "webapp/WEB-INF/pages/addstudent.jsp"
     */
    @RequestMapping("/addstudent")
    public String showeditstd(Model m){
        m.addAttribute("command", new Student());
        List<Grp> grp = grpService.getAll();
        m.addAttribute("grp", grp);
        return "addstudent";
    }

    /**
     * Создание нового студента по введенным параметрам и сохранение его в БД
     * @param std Student std
     * @see Student
     * @return redirect:/students
     * @see "webapp/WEB-INF/pages/studentpage.jsp"
     */
    @RequestMapping(value="/savestd",method = RequestMethod.POST)
    public String savestd(@ModelAttribute("std") Student std){
        Grp grp;
        if ((std.getGrp()).getName() != "") {
            grp = grpService.getByName((std.getGrp()).getName());
        }
        else
        {
            grp = null;
        }
        std.setGrp(grp);

        stdService.addStudent(std);
        return "redirect:/students";
    }

    /**
     * Удаление студента по его id
     * @param id long id
     * @return redirect:/studnets
     * @see "webapp/WEB-INF/pages/studentpage.jsp"
     */
    @RequestMapping(value="/deletestd/{id}",method = RequestMethod.GET)
    public String deletestd(@PathVariable long id){
        stdService.deleteStudent(id);
        return "redirect:/studnets";
    }

    /**
     * Заполнение формы редактирования параметрами студента по его id
     * @param id long id
     * @param m Model m
     * @see org.springframework.ui.Model
     * @return editstudent.jsp
     * @see "webapp/WEB-INF/pages/editstudent.jsp"
     */
    @RequestMapping(value="/editstd/{id}")
    public String editstd(@PathVariable long id, Model m){
        Student std = stdService.getById(id);
        m.addAttribute("command",std);
        List<Grp> grp = grpService.getAll();
        m.addAttribute("grp", grp);
        return "editstudent";
    }

    /**
     * Обновление записи студента в БД по введенным параметрам
     * @param std Student std
     * @see Student
     * @return redirect:/students
     * @see "webapp/WEB-INF/pages/studentpage.jsp"
     */
    @RequestMapping(value="/editsavestd",method = RequestMethod.POST)
    public String editsavestd(@ModelAttribute("std") Student std){
        Grp grp;
        if (!(std.getGrp()).getName().equals("")) {
            grp = grpService.getByName((std.getGrp()).getName());
        }
        else
        {
            grp = null;
        }
        std.setGrp(grp);
        stdService.updateStudent(std);
        return "redirect:/students";
    }

    /**
     * Поиск студента(ов) по id, подстроке ФИО и/или группе
     * @param std Student std
     * @see Student
     * @param m Model m
     * @see org.springframework.ui.Model
     * @see MainController#findassistd(Student)
     * @return studentpage.jsp
     * @see "webapp/WEB-INF/pages/studentpage.jsp"
     */
    @RequestMapping(value="/findstd",method = RequestMethod.POST)
    public String findstd(@ModelAttribute("std") Student std,  Model m){
        m.addAttribute("command", new Student());
        List<Student> stds = new ArrayList<>();
        if (std.getId() != 0)
        {
            if (stdService.getById(std.getId()) != null) {
                stds.add(stdService.getById(std.getId()));
            }
            else
            {
                stds = findassistd(std);
            }
        }
        else {
            stds = findassistd(std);
        }
        try {
            m.addAttribute("std", stds);
        }
        catch (java.lang.NumberFormatException e)
        {
            List<Grp> grp = grpService.getAll();
            m.addAttribute("grp", grp);
            return "studentpage";
        }
        List<Grp> grp = grpService.getAll();
        m.addAttribute("grp", grp);
        return "studentpage";
    }

    //Вспомогательные методы

    /**
     * Формирует критерии поискового запроса для сущности "Группа"
     * @param grp Grp grp
     * @see Grp
     * @return List
     * @see java.util.List
     * @see MainController#findgrp(Grp, Model)
     */
    public List<Grp> findassis(Grp grp)
    {
        if (grp.getName().equals("")) {
            grp.setName(null);
        }
        if (grp.getFaculty().equals("<Empty>")) {
            grp.setFaculty(null);
        }
        if ((grp.getName() == null) && (grp.getFaculty() == null))
        {
            return null;
        }
        else {
            return grpService.getOr(grp.getName(), grp.getFaculty());
        }
    }

    /**
     * Формирует критерии поискового запроса для сущности "Студент"
     * @param std Student std
     * @see Student
     * @return List
     * @see java.util.List
     * @see MainController#findstd(Student, Model)
     */
    public List<Student> findassistd(Student std)
    {
        if (std.getFirstname().equals("")) {
            std.setFirstname(null);
        }
        if ((std.getGrp()).getName().equals("<Empty>")) {
            std.setGrp(null);
        }
        else {
            std.setGrp(grpService.getByName(std.getGrp().getName()));
        }
        if ((std.getFirstname() == null && std.getGrp()== null))
        {
            return stdService.getByGroupNull();
        }
        else {
            return stdService.getByOr(std.getFirstname(), std.getGrp());
        }
    }

    /**
     * Настраивает стиль отображения даты и позволяет получать значение null
     * @param binder WebDataBinder binder
     * @see org.springframework.web.bind.WebDataBinder
     * @see Student#getDate()
     * @see MainController#savestd(Student)
     * @see MainController#editsavestd(Student)
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

}
