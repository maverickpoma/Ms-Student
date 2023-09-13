package com.example.msstudent.serviceTest;

import com.example.msstudent.model.Student;
import com.example.msstudent.repository.StudentRepository;
import com.example.msstudent.service.StudentService;
import com.example.msstudent.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceImplTests {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;


    @Test
    void setStudent_WhenStudentDoesNotExist_ShouldSaveStudent() {
        // Arrange
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository);

        Student newStudent = new Student(1L, "John", "Doe", true, 20);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Mono.empty());
        Mockito.when(studentRepository.save(newStudent)).thenReturn(Mono.just(newStudent));

        // Act
        Mono<Object> result = studentService.setStudent(newStudent);

        // Assert
        StepVerifier.create(result)
                .expectNext("Estudiante grabado exitosamente")
                .verifyComplete();
    }

    @Test
    void setStudent_WhenStudentExists_ShouldReturnErrorMessage() {
        // Arrange
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository);

        Student existingStudent = new Student(1L, "John", "Doe", true, 20);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Mono.just(existingStudent));

        // Act
        Mono<Object> result = studentService.setStudent(existingStudent);

        // Assert
        StepVerifier.create(result)
                .expectErrorMessage("El ID ya existe, no se puede realizar la grabación.")
                .verify();
    }
    @Test
    void testStudentList() {
        // Define el comportamiento del repositorio simulado
        when(studentRepository.findAllByEstado(true)).thenReturn(Flux.just(new Student(), new Student()));

        // Llama al método que deseas probar
        Flux<Student> result = studentService.studentList();

        // Verifica el resultado
        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }
}