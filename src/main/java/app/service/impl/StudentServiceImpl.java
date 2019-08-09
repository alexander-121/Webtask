package app.service.impl;

import app.entity.Grp;
import app.entity.Student;
import app.repository.StudentRepository;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Сервис сущности "Студент"
 * @see Student
 * @see StudentService
 */

@ComponentScan({"app.repository"})
@Service
public class StudentServiceImpl implements StudentService {

    /**
     * Поле StudentRepository
     * @see StudentRepository
     */
    @Autowired
    private StudentRepository stdRepository;

    /**
     * Добавление студента
     * @param student Student student
     * @return Student student
     * @see Student
     * @see StudentRepository#saveAndFlush(Object)
     */
    @Override
    public Student addStudent(Student student) {

        Student savedstd = stdRepository.saveAndFlush(student);
        return savedstd;
    }

    /**
     * Удаление студента по его id
     * @param id long id
     * @see StudentRepository#deleteById(Object)
     */
    @Override
    public void deleteStudent(long id) {
        stdRepository.deleteById(id);
    }

    /**
     * Нахождение студента по его id
     * @param id long id
     * @return Student student
     * @see Student
     * @see StudentRepository#findById(long)
     */
    @Override
    public Student getById(long id) {
        return stdRepository.findById(id);
    }

    /**
     * Обновление группы студента по его id
     * @param grp Grp grp
     * @param id long id
     * @see Student#getGrp()
     * @see Grp
     * @see StudentRepository#updateStudentGrp(Grp, long)
     */
    @Override
    public void updateStudentGrp(Grp grp, long id) {
        stdRepository.updateStudentGrp(grp, id);
    }

    /**
     * Полное обновление студента
     * @param std Student std
     * @see Student
     * @see StudentRepository#updateStudent(String, String, String, String, Date, Grp, long)
     */
    @Override
    public void updateStudent(Student std){
        stdRepository.updateStudent(std.getFirstname(), std.getFathersname(), std.getLastname(), std.getStudytype(), std.getDate(), std.getGrp(), std.getId());
    }

    /**
     * Получение списка всех студентов
     * @return List
     * @see java.util.List
     * @see Student
     * @see StudentRepository#findAll()
     */
    @Override
    public List<Student> getAll() {

        return stdRepository.findAll();
    }

    /**
     * @deprecated please use getByOr(String name, Grp grp)
     * @see StudentService#getByOr(String, Grp)
     */
    @Override
    public List<Student> getByName(String name) {
        return null;
    }

    /**
     * Получение списка студентов без групп
     * @return List
     * @see java.util.List
     * @see Student
     * @see StudentRepository#findByGrpNl()
     */
    @Override
    public List<Student> getByGroupNull() {

        return stdRepository.findByGrpNl();
    }

    /**
     * Получение списка студентов по группе
     * @param grp Grp grp
     * @see Student#getGrp()
     * @see Grp
     * @return List
     * @see java.util.List
     * @see Student
     * @see StudentRepository#findByGrp(Grp)
     */
    @Override
    public List<Student> getByGroup(Grp grp) {
        return stdRepository.findByGrp(grp);
    }

    /**
     * Получение списка студентов по подстроке ФИО и/или группе
     * @param name String name
     * @param grp Grp grp
     * @see Student#getGrp()
     * @see Grp
     * @return list
     * @see java.util.List
     * @see Student
     * @see StudentRepository#findByOr(String, Grp) 
     */
    @Override
    public List<Student> getByOr(String name, Grp grp)
    {
        return stdRepository.findByOr(name, grp);
    }
}
