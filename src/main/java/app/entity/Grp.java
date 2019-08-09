package app.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Класс сущности "Группа"
 */
@Entity
@Table(name = "grps")
public class Grp {

    /**
     * Поле ID с автоинкрементацией
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name="grp_id", length = 6, nullable = false)
    private long id;

    /**
     * Поле ИМЯ
     */
    @Column(name="name")
    private String name;

    /**
     * Поле ФАКУЛЬТЕТ
     */
    @Column(name="faculty")
    private String faculty;

    /**
     * Конструктор
     * @param name
     * @param faculty
     */
    public Grp(String name, String faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    /**
     * Конструктор по умолчанию
     */
    public Grp() {

    }

    //Getters and Setters

    /**
     * Getter
     * @return field id value
     */
    public long getId() {
        return id;
    }

    /**
     * Getter
     * @return field name value
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return field faculty value
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Setter
     * @param id long value
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter
     * @param name String value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter
     * @param faculty String value
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
