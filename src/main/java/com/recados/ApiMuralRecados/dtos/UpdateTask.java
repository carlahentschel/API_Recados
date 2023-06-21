package com.recados.ApiMuralRecados.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateTask(
        String title,
        String description,
        LocalDate date,
        Boolean favorite,
        Boolean finished,
        UUID idTask
) {
}
