package com.example.SpringBootDemo.dao;

import com.example.SpringBootDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByAgeEquals(Integer age);

    List<Student> findByGradeEquals(Integer grade);

    List<Student> findByEntryYearEquals(Integer entryYear);
}
