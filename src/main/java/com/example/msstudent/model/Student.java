package com.example.msstudent.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @NotNull(message= "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede contener letras")
    @NotNull(message= "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido solo puede contener letras")
    @NotNull(message= "El apellido no puede ser nulo")
    private String apellido;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;


    @NotNull(message = "La edad no puede estar vacio")
    private Integer edad;



}
