package br.com.rogerio.gestaocursos.course;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Name is mandatory")
        String category,

        String shift) {
}
