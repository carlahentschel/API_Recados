package com.recados.ApiMuralRecados.models;

import com.recados.ApiMuralRecados.dtos.CreateTask;
import com.recados.ApiMuralRecados.dtos.UpdateTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tasks")
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private boolean favorite;
    private boolean finished;
    @Column(name = "id_user")
    private UUID idUser;

    public UserTask(CreateTask newTask, UUID idUser) {
        title = newTask.title();
        description = newTask.description();
        date = newTask.date();
        favorite = false;
        finished = false;
        this.idUser = idUser;
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
