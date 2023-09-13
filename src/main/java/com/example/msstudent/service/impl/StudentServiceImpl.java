package com.example.msstudent.service.impl;

import com.example.msstudent.model.Student;
import com.example.msstudent.repository.StudentRepository;
import com.example.msstudent.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

private StudentRepository studentRepository;

    @Override
    public Mono<Object> setStudent(Student student) {
        return studentRepository.findById(student.getId())
                .flatMap(existingStudent -> Mono.error(new RuntimeException("El ID ya existe, no se puede realizar la grabación.")))
                .onErrorResume(error -> Mono.just(error.getMessage()))
                .switchIfEmpty(studentRepository.save(student).thenReturn("Estudiante grabado exitosamente"));
    }

    @Override
    public Flux<Student> studentList() {
        return studentRepository.findAllByEstado(true);

    }
}