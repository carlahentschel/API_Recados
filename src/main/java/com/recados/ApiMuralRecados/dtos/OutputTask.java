package com.recados.ApiMuralRecados.dtos;

import com.recados.ApiMuralRecados.models.UserTask;

import java.time.LocalDate;
import java.util.UUID;

public record OutputTask(
        String title,
        String description,
        LocalDate date,
        Boolean favorite,
        Boolean finished,
        UUID idTask
) {
    public OutputTask(UserTask task) {
        this(task.getTitle(), task.getDescription(), task.getDate(), task.isFavorite(), task.isFinished(), task.getId());
    }

}
