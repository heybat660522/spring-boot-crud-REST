package com.example.SpringBootDemo.service;

import com.example.SpringBootDemo.dao.StudentRepository;
import com.example.SpringBootDemo.errorHandler.StudentNotFoundException;
import com.example.SpringBootDemo.model.Student;
import com.example.SpringBootDemo.util.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements IService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private DataLoader dataLoader;

    private Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public List<Student> findAll() {
        List<Student> result = repository.findAll();
        return result;
    }

    public Student addNewStudent(Student newStudent) {
        return repository.save(newStudent);
    }

    public Student findOneStudentId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> findBySearchCriteria(Student searchCriteria) {
        // Goal is to return Union of various search result based on criteria
        List<Student> result = findAll();
        if (searchCriteria.getName() == null
                && searchCriteria.getAge() == null
                && searchCriteria.getGrade() == null
                && searchCriteria.getEntryYear() == null)
            return result;

        HashMap<Long, Student> studentHashMap = new HashMap<>();
        System.out.println("search Criteria" + searchCriteria);
        if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
            repository
                    .findByNameContainingIgnoreCase(searchCriteria.getName())
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getAge() != null) {
            result.stream()
                    .filter(a -> a.getAge().equals(searchCriteria.getAge()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getGrade() != null) {
            result.stream()
                    .filter(a -> a.getGrade().equals(searchCriteria.getGrade()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));

        } else if (searchCriteria.getEntryYear() != null) {
            result.stream()
                    .filter(a -> a.getEntryYear().equals(searchCriteria.getEntryYear()))
                    .forEach(s -> studentHashMap.put(s.getStudentId(), s));
        }
        return new ArrayList<Student>(studentHashMap.values());
    }

    private List<Student> priorityBasedSearch(Student searchCriteria) {
        List<Student> result = new ArrayList<>();
        if (searchCriteria.getName() != null || !searchCriteria.getName().isEmpty()) {
            result = repository.findByNameContainingIgnoreCase(searchCriteria.getName());
        } else if (searchCriteria.getAge() != null) {
            result = repository.findByAgeEquals(searchCriteria.getAge());
        } else if (searchCriteria.getGrade() != null) {
            result = repository.findByGradeEquals(searchCriteria.getGrade());
        } else if (searchCriteria.getEntryYear() != null) {
            result = repository.findByEntryYearEquals(searchCriteria.getEntryYear());
        }
        return result;
    }

    public Student saveOrUpdate(Student newStudent, Long id) {
        if (null != id) {
            Student student = repository.getOne(id);
            if (student != null) {
                student.setEntryYear(newStudent.getEntryYear());
                student.setAge(newStudent.getAge());
                student.setBirthDate(newStudent.getBirthDate());
                student.setGrade(newStudent.getGrade());
                student.setName(newStudent.getName());
                return repository.save(student);
            }
        }
        return repository.save(newStudent);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    public void loadInitialDataFromFile(String fileName) {
        try {
            List<Student> students = dataLoader.loadStudentRecords(fileName);
            students.stream().peek(s -> System.out.println("saving " + s)).forEach(s -> repository.save(s));
        } catch (Exception ex) {
            logger.error("loading data was failur: " + ex.getMessage());
        }
    }

    private Date toSqlDate(String rawDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return Date.valueOf(LocalDate.parse(rawDate, formatter));
    }
}
