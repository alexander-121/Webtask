package app.service;

import app.entity.Grp;
import app.entity.Student;

import java.util.List;

/**
 * Интерфейс сервиса для сущности "Студент"
 * @see Student
 * @see app.service.impl.StudentServiceImpl
 */
public interface StudentService {

    /**
     * Добавление студента
     * @param student Student student
     * @return Student student
     * @see Student
     */
    Student addStudent(Student student);

    /**
     * Удаление студента по его id
     * @param id long id
     */
    void deleteStudent(long id);

    /**
     * Нахождение студента по его id
     * @param id long id
     * @return Student student
     * @see Student
     */
    Student getById(long id);

    /**
     * Обновление группы студента по его id
     * @param grp Grp grp
     * @param id long id
     * @see Student#getGrp()
     * @see Grp
     */
    void updateStudentGrp(Grp grp, long id);

    /**
     * Полное обновление студента
     * @param std Student std
     * @see Student
     */
    void updateStudent(Student std);

    /**
     * Получение списка всех студентов
     * @return List
     * @see java.util.List
     * @see Student
     */
    List<Student> getAll();

    /**
     * @deprecated please use getByOr(String name, Grp grp)
     * @see StudentService#getByOr(String, Grp)
     */
    List<Student> getByName(String name);

    /**
     * Получение списка студентов без групп
     * @return List
     * @see java.util.List
     * @see Student
     */
    List<Student> getByGroupNull();

    /**
     * Получение списка студентов по группе
     * @param grp Grp grp
     * @see Student#getGrp()
     * @see Grp
     * @return List
     * @see java.util.List
     * @see Student
     */
    List<Student> getByGroup(Grp grp);

    /**
     * Получение списка студентов по подстроке ФИО и/или группе
     * @param name String name
     * @param grp Grp grp
     * @see Student#getGrp()
     * @see Grp
     * @return list
     * @see java.util.List
     * @see Student
     */
    List<Student> getByOr(String name, Grp grp);
}
