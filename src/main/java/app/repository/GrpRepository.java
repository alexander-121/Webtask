package app.repository;

import app.entity.Grp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Репозиторий для сущности "Группа"
 * @see Grp
 * @see org.springframework.data.repository
 */
@Repository
public interface GrpRepository extends JpaRepository<Grp, Long> {

        /**
         * Запрос на поиск группы по имени
         * @param name String name
         * @return Grp
         * @see Grp
         */
        @Query("select g from Grp g where g.name = :name")
        Grp findByName(@Param("name") String name);

        /**
         * Запрос на поиск группы по факультету
         * @param faculty String faculty
         * @return Grp
         * @see Grp
         */
        @Query("select g from Grp g where g.faculty = :faculty")
        Grp findByFaculty(@Param("faculty") String faculty);

        /**
         * Запрос на поиск группы по ID
         * @param id long id
         * @return Grp
         * @see Grp
         */
        @Query ("select g from Grp g where g.id = :id")
        Grp findById(@Param("id") long id);

        /**
         * Запрос на поиск групп по имени и/или факультету
         * @param name String name
         * @param faculty String faculty
         * @return List
         * @see java.util.List
         * @see Grp
         */
        @Query ("select g from Grp g where (:name is null or g.name like %:name%) and (:faculty is null or g.faculty = :faculty) order by id")
        List<Grp> findByOr(@Param("name") String name, @Param("faculty") String faculty);

        /**
         * Запрос на поиск всех групп и упорядовачивание их по id
         * @return List
         * @see java.util.List
         * @see Grp
         */
        @Query ("select g from Grp g order by id")
        List<Grp> findAll();

        /**
         * @deprecated Please use findByOr(String name, String faculty)
         * @see GrpRepository#findByOr(String, String)
         */
        @Query ("select g from Grp g where g.id = :id and g.name = :name and g.faculty = :faculty")
        Grp findByAnd(@Param("name") String name, @Param("faculty") String faculty, @Param("id") long id);

        /**
         * Запрос на обновление группы
         * @param name String name
         * @param faculty String faculty
         * @param id long id
         */
        @Modifying
        @Transactional
        @Query("update Grp g set g.name = :name, g.faculty = :faculty where g.id = :id")
        void updateGroup(@Param("name") String name, @Param("faculty") String faculty, @Param("id") long id);
}
