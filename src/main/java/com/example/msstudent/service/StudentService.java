package com.example.msstudent.service;

import com.example.msstudent.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface StudentService {

    Mono<Object> setStudent(Student student);
    Flux<Student> studentList();
}
