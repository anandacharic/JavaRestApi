package com.udemyrest.restapi.rest;

import com.udemyrest.restapi.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> studentList;
    @PostConstruct
    public void generateTheStudentList(){
        studentList=new ArrayList<>();
        studentList.add(new Student("Robin","Hillyard"));
        studentList.add(new Student("William","Jack"));
        studentList.add(new Student("Jackson","Sqaure"));
    }
    @GetMapping("/student")
    public List<Student> getStudent(){
        return studentList;
    }
    @GetMapping("/student/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentId>=studentList.size() || studentId<0)
            throw new StudentNotFoundException("Student Id was not found"+studentId);
        return studentList.get(studentId);
    }
}
