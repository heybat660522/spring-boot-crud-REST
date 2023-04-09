package com.example.SpringBootDemo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Column(name = "studentId")
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long studentId;

    @Column(name = "entry_year")
    private Integer entryYear = null;

    @Column(name = "age")
    private Integer age = null;

    @Column(name = "birthDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date birthDate;

    @Column(name = "grade")
    private Integer grade = null;

    @Column(name = "name")
    private String name=null;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", birthDate=" + birthDate +
                ", entryYear=" + entryYear +
                ", age=" + age +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }
    public Student( Integer entryYear, Integer age, Date birthDate, Integer grade, String name) {
        this.entryYear = entryYear;
        this.age = age;
        this.birthDate = birthDate;
        this.grade = grade;
        this.name = name;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(Integer entryYear) {
        this.entryYear = entryYear;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
