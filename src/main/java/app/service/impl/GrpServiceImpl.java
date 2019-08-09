package app.service.impl;

import app.entity.Grp;
import app.repository.GrpRepository;
import app.service.GrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис сущности "Группа"
 * @see Grp
 * @see GrpService
 */
@ComponentScan({"app.repository"})
@Service
public class GrpServiceImpl implements GrpService {

    /**
     * Поле GrpRepository
     * @see GrpRepository
     */
    @Autowired
    private GrpRepository grpRepository;

    /**
     * Добавление группы
     * @param grp Grp grp
     * @return Grp grp
     * @see Grp
     * @see GrpRepository#saveAndFlush(Object)
     */
    @Override
    public Grp addGroup(Grp grp) {
        Grp savedgroup = grpRepository.saveAndFlush(grp);
        return savedgroup;
    }

    /**
     * Нахождение группы по id
     * @param id long id
     * @return Grp grp
     * @see Grp
     * @see GrpRepository#findById(long)
     */
    @Override
    public Grp getById(long id)
    {
        return grpRepository.findById(id);
    }

    /**
     * Обновление группы
     * @param grp Grp grp
     * @see Grp
     * @see GrpRepository#updateGroup(String, String, long)
     */
    @Override
    public void updateGroup(Grp grp)
    {
        grpRepository.updateGroup(grp.getName(),grp.getFaculty(),grp.getId());
    }

    /**
     * Удаление группы по id
     * @param id long id
     * @see GrpRepository#deleteById(Object)
     */
    @Override
    public void deleteGroup(long id) {
        grpRepository.deleteById(id);
    }

    /**
     * Нахождение группы по имени
     * @param name String name
     * @return Grp grp
     * @see Grp
     * @see GrpRepository#findByName(String)
     */
    @Override
    public Grp getByName(String name) {
        return grpRepository.findByName(name);
    }

    /**
     * Нахождение группы по факультету
     * @param faculty String faculty
     * @return Grp grp
     * @see Grp
     * @see GrpRepository#findByFaculty(String)
     */
    @Override
    public Grp getByFaculty(String faculty) {
        return grpRepository.findByFaculty(faculty);
    }

    /**
     * Получение списка всех групп
     * @return List
     * @see java.util.List
     * @see Grp
     * @see GrpRepository#findAll()
     */
    @Override
    public List<Grp> getAll() {
        return grpRepository.findAll();
    }

    /**
     * Получение списка групп по имени и/или факультету
     * @param name String name
     * @param faculty String faculty
     * @return List
     * @see java.util.List
     * @see Grp
     * @see GrpRepository#findByOr(String, String)
     */
    @Override
    public List<Grp> getOr(String name, String faculty)
    {
        return grpRepository.findByOr(name, faculty);
    }

    /**
     * @deprecated please use getOr(String name, String faculty)
     * @see GrpService#getOr(String, String)
     */
    @Override
    public Grp getAnd(String name, String faculty, long id)
    {
        return grpRepository.findByAnd(name, faculty, id);
    }
}
