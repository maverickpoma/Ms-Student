package com.example.msstudent.controller;

import com.example.msstudent.model.Student;
import com.example.msstudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

        private final StudentService studentService;
        @Autowired
        public StudentController(StudentService studentService)
        {
            this.studentService = studentService;
        }

        @PostMapping
        public Mono<Object> setStudent(@Valid @RequestBody Student student) {
            return studentService.setStudent(student);

        }

        @GetMapping
        public Flux<Student> studentList() {

            return studentService.studentList();
        }



    }
