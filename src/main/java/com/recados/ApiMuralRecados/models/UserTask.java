package com.recados.ApiMuralRecados.models;

import com.recados.ApiMuralRecados.dtos.CreateTask;
import com.recados.ApiMuralRecados.dtos.UpdateTask;
import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class UserTask {
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private boolean favorite;
    private boolean finished;

    public UserTask(CreateTask newTask) {
        title = newTask.title();
        description = newTask.description();
        date = newTask.date();
        id = UUID.randomUUID();
        favorite = false;
        finished = false;
    }

    public void updateTask(UpdateTask data) {
        if(data.title() != null) {
            title = data.title();
        }
        if(data.description() != null) {
            description = data.description();
        }
        if(data.date() != null) {
            date = data.date();
        }
        if(data.favorite() != null) {
            favorite = data.favorite();
        }
        if(data.finished() != null) {
            finished = data.finished();
        }

    }


}
