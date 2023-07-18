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
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();

        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception){
        StudentErrorResponse studentErrorResponse=new StudentErrorResponse();

        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
