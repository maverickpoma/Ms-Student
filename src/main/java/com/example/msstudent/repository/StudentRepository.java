package com.example.msstudent.repository;

import com.example.msstudent.model.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public interface StudentRepository{

        Flux<Student> findAll();

        Mono<Student> findById(Long id);

        Mono<Student> save(Student student);

        Flux<Student> findAllByEstado(boolean estado);
}

