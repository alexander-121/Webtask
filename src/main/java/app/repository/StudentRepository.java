package app.repository;

import app.entity.Grp;
import app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Репозиторий для сущности "Студент"
 * @see Student
 * @see org.springframework.data.repository
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

        /**
         * Запрос на поиск студента по id
         * @param id long id
         * @return Student
         * @see Student
         */
        @Query ("select s from Student s where s.id = :id")
        Student findById(@Param("id") long id);

        /**
         * Запрос на поиск студентов без группы
         * @return List
         * @see java.util.List
         * @see Student
         */
        @Query ("select s from Student s where s.grp is null")
        List<Student> findByGrpNl();

        /**
         * Запрос на поиск студентов по группе
         * @param grp Grp grp
         * @see Student#getGrp()
         * @see Grp
         * @return List
         * @see java.util.List
         */
        @Query ("select s from Student s where s.grp = :grp")
        List<Student> findByGrp (@Param("grp") Grp grp);

        /**
         * Запрос на поиск студентов по ФИО и/или группе
         * @param name String name
         * @param grp Grp grp
         * @see Student#getGrp()
         * @see Grp
         * @return List
         * @see java.util.List
         */
        @Query ("select s from Student s where (:name is null or s.firstname like %:name% or s.fathersname like %:name% or s.lastname like %:name%) and (:grp is null or s.grp = :grp) order by id")
        List<Student> findByOr(@Param("name") String name, @Param("grp") Grp grp);

        /**
         * Запрос на обновление группы студента по id
         * @param grp Grp grp
         * @param id long id
         * @see Grp
         */
        @Modifying
        @Transactional
        @Query("update Student s set s.grp =:grp where s.id = :id")
        void updateStudentGrp(@Param("grp") Grp grp, @Param("id") long id);

        /**
         * Запрос на полное обновление студента
         * @param firstname String firstname
         * @param fathersname String fathersname
         * @param lastname String lastname
         * @param studytype String studytype
         * @param date Date date
         * @see java.sql.Date
         * @param grp Grp grp
         * @see Student#getGrp()
         * @see Grp
         * @param id long id
         */
        @Modifying
        @Transactional
        @Query("update Student s set s.firstname = :firstname, s.fathersname = :fathersname, s.lastname = :lastname, s.studytype = :studytype, s.date = :date, s.grp = :grp where s.id = :id")
        void updateStudent(@Param("firstname") String firstname, @Param("fathersname") String fathersname, @Param("lastname") String lastname, @Param("studytype") String studytype, @Param("date") Date date, @Param("grp") Grp grp, @Param("id") long id);
}

