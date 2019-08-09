package app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Класс сущности "Студент"
 */
@Entity
@Table(name="stdnts")
public class Student {

    /**
     * Поле ID с автоинкрементацией
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name="id", length = 6, nullable = false)
    private long id;

    /**
     * Поле ИМЯ
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Поле ОТЧЕСТВО
     */
    @Column(name="fathersname")
    private String fathersname;

    /**
     * Поле ФАМИЛИЯ
     */
    @Column(name="lastname")
    private String lastname;

    /**
     * Поле ФОРМА ОБУЧЕНИЯ
     */
    @Column(name="studytype")
    private String studytype;

    /**
     * Поле ДАТА НАЧАЛА ОБУЧЕНИЯ
     */
    @Column(name="enrollmentdata")
    private Date date;

    /**
     * Поле ГРУППА, в которой обучается студент
     * Связь ManyToOne
     * @see Grp#Grp()
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="grp_id")
    private Grp grp;

    /**
     * Конструктор
     * @param firstname
     * @param fathersname
     * @param lastname
     * @param studytype
     * @param date
     * @param grp
     */
    public Student(String firstname, String fathersname, String lastname, String studytype, Date date, Grp grp) {
        this.firstname = firstname;
        this.fathersname = fathersname;
        this.lastname = lastname;
        this.studytype = studytype;
        this.date = date;
        this.grp = grp;
    }

    /**
     * Конструктор по умолчанию
     */
    public Student(){

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
     * Setter
     * @param id long value
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter
     * @return field firstname value
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter
     * @param firstname String value
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter
     * @return field fathersname value
     */
    public String getFathersname() {
        return fathersname;
    }

    /**
     * Setter
     * @param fathersname String value
     */
    public void setFathersname(String fathersname) {
        this.fathersname = fathersname;
    }

    /**
     * Getter
     * @return filed lastname value
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter
     * @param lastname String value
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter
     * @return field studytype value
     */
    public String getStudytype() {
        return studytype;
    }

    /**
     * Setter
     * @param studytype String value
     */
    public void setStudytype(String studytype) {
        this.studytype = studytype;
    }

    /**
     * Getter
     * @return field date value
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter
     * @param date Date value
     * @see java.sql.Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter
     * @return field grp value
     */
    public Grp getGrp() {
        return grp;
    }

    /**
     * Setter
     * @param grp Grp value
     * @see Grp
     */
    public void setGrp(Grp grp) {
        this.grp = grp;
    }

}
