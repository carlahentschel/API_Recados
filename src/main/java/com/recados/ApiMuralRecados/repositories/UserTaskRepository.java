package com.recados.ApiMuralRecados.repositories;

import com.recados.ApiMuralRecados.models.User;
import com.recados.ApiMuralRecados.models.UserTask;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface UserTaskRepository extends JpaRepository<UserTask, UUID>, JpaSpecificationExecutor<UserTask> {
    //List<UserTask> findAllByUserId(UUID idUser);
    //List<UserTask> findAllByUserId(UUID idUser, Specification<UserTask> specification );

}
