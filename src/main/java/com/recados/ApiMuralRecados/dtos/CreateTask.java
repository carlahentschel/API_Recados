package com.recados.ApiMuralRecados.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateTask(
        @NotBlank
        @NotNull
        String title,
        String description,
        LocalDate date
) {
}
