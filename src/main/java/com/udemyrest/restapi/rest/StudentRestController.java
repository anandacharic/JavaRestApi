package com.udemyrest.restapi.rest;

import com.udemyrest.restapi.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    @GetMapping("/student")
    public List<Student> getStudent(){
        List<Student> studentList=new ArrayList<>();
        studentList.add(new Student("Robin","Hillyard"));
        studentList.add(new Student("William","Jack"));
        studentList.add(new Student("Jackson","Sqaure"));
        return studentList;
    }
}
