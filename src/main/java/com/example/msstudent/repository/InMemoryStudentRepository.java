package com.example.msstudent.repository;


import com.example.msstudent.model.Student;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStudentRepository implements StudentRepository {
    private final Map<Long, Student> students = new ConcurrentHashMap<>();

    @Override
    public Flux<Student> findAll() {
        Collection<Student> studentList = students.values();
        return Flux.fromIterable(studentList);
    }

    @Override
    public Mono<Student> findById(Long id) {
        return Mono.justOrEmpty(students.get(id));
    }

    @Override
    public Mono<Student> save(Student student) {
        students.put(student.getId(), student);
        return Mono.just(student);
    }

    @Override
    public Flux<Student> findAllByEstado(boolean estado) {
        return Flux.fromIterable(students.values())
                .filter(student -> student.getEstado() == estado);
    }
}