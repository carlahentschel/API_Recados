package com.recados.ApiMuralRecados.dtos;

import java.time.LocalDate;

public record UpdateTask(
        String title,
        String description,
        LocalDate date,
        Boolean favorite,
        Boolean finished
) {
}
