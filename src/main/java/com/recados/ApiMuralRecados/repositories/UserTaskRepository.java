package com.recados.ApiMuralRecados.repositories;

import com.recados.ApiMuralRecados.models.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserTaskRepository extends JpaRepository<UserTask, UUID> {

//    List<UserTask> getAllTasksForUser(UUID idUser);
}
