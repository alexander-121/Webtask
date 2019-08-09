package app.service;

import app.entity.Grp;

import java.util.List;

/**
 * Интерфейс сервиса для сущности "Группа"
 * @see Grp
 * @see app.service.impl.GrpServiceImpl
 */
public interface GrpService {

    /**
     * Добавление группы
     * @param grp Grp grp
     * @return Grp grp
     * @see Grp
     */
    Grp addGroup(Grp grp);

    /**
     * Удаление группы по id
     * @param id long id
     */
    void deleteGroup(long id);

    /**
     * Нахождение группы по имени
     * @param name String name
     * @return Grp grp
     * @see Grp
     */
    Grp getByName(String name);

    /**
     * Нахождение группы по факультету
     * @param faculty String faculty
     * @return Grp grp
     * @see Grp
     */
    Grp getByFaculty(String faculty);

    /**
     * Нахождение группы по id
     * @param id long id
     * @return Grp grp
     * @see Grp
     */
    Grp getById(long id);

    /**
     * Обновление группы
     * @param grp Grp grp
     * @see Grp
     */
    void updateGroup(Grp grp);

    /**
     * Получение списка всех групп
     * @return List
     * @see java.util.List
     * @see Grp
     */
    List<Grp> getAll();

    /**
     * Получение списка групп по имени и/или факультету
     * @param name String name
     * @param faculty String faculty
     * @return List
     * @see java.util.List
     * @see Grp
     */
    List<Grp> getOr(String name, String faculty);

    /**
     * @deprecated please use getOr(String name, String faculty)
     * @see GrpService#getOr(String, String)
     */
    Grp getAnd(String name, String faculty, long id);
}
