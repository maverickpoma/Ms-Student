package com.example.msstudent.controllerTest;
import com.example.msstudent.model.Student;
import com.example.msstudent.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTests {


    @Autowired
    private WebTestClient webClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        // Configura el comportamiento del servicio simulado
        Mockito.when(studentService.setStudent(Mockito.any())).thenReturn(Mono.just("Estudiante grabado exitosamente"));
        Mockito.when(studentService.studentList()).thenReturn(Flux.just(new Student()));
    }

    @Test
    void setStudent_WhenValidInput_ShouldReturnSuccessMessage() {
        // Arrange
        Student newStudent = new Student(1L, "John", "Doe", true, 20);
        Mockito.when(studentService.setStudent(newStudent)).thenReturn(Mono.just("Estudiante grabado exitosamente"));

        // Act and Assert
        webClient.post().uri("/api/v1/student")
                .bodyValue(newStudent)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Estudiante grabado exitosamente");
    }

    @Test
    void studentList_ShouldReturnListOfStudents() {
        // Arrange
        Student student1 = new Student(1L, "John", "Doe", true, 20);
        Student student2 = new Student(2L, "Jane", "Doe", true, 22);
        Mockito.when(studentService.studentList()).thenReturn(Flux.just(student1, student2));

        // Act and Assert
        webClient.get().uri("/api/v1/student")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Student.class)
                .consumeWith(response -> {
                    List<Student> students = response.getResponseBody();
                    assertThat(students).containsExactlyInAnyOrderElementsOf(List.of(student1, student2));
                });
    }

    @Test
    void testStudentList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}