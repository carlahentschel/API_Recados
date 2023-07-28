package com.recados.ApiMuralRecados.repositories;

import com.recados.ApiMuralRecados.models.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;

public interface UserTaskRepository extends JpaRepository<UserTask, UUID>, JpaSpecificationExecutor<UserTask> {

}
