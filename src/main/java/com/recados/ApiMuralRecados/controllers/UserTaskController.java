package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dtos.CreateTask;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.OutputTask;
import com.recados.ApiMuralRecados.dtos.UpdateTask;
import com.recados.ApiMuralRecados.models.UserTask;
import com.recados.ApiMuralRecados.repositories.UserRepository;
import com.recados.ApiMuralRecados.repositories.UserTaskRepository;
import com.recados.ApiMuralRecados.repositories.specifications.UserTaskSpecification;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class UserTaskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createTasks(@RequestBody @Valid CreateTask newTask,
                                      @RequestHeader ("AuthToken") String token) {
        var optionalUser = userRepository.findById(newTask.userId());

        if(optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }

        var user = optionalUser.get();

        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var task = new UserTask(newTask, user.getId());
        userTaskRepository.save(task);
        return ResponseEntity.ok().body(new OutputTask(task));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity getAllTasks(@PathVariable UUID idUser,
                                      @RequestHeader ("AuthToken") String token,
                                      @RequestParam(required = false) String title,
                                      @RequestParam(required = false) Boolean finished,
                                      @RequestParam(required = false) Boolean favorite) {

        var optionalUser = userRepository.findById(idUser);
        if(optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        var user = optionalUser.get();
        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var specification = UserTaskSpecification.filterByTitleAndFinishedAndFavorite(idUser, title, finished, favorite);

        var data = userTaskRepository.findAll(specification).stream().map(OutputTask::new).toList();

        return ResponseEntity.ok().body(data);

    }

    @DeleteMapping("/{idUser}/{idTask}")
    public ResponseEntity deleteTask(@PathVariable UUID idUser,
                                     @PathVariable UUID idTask,
                                     @RequestHeader ("AuthToken") String token) {
        var optionalUser = userRepository.findById(idUser);
        if(optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        var user = optionalUser.get();
        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var taskOptional = user.getTasks().stream().filter(u -> u.getId().equals(idTask)).findAny();
        if (taskOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não existe."));
        }

        userTaskRepository.delete(taskOptional.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idUser}/{idTask}")
    @Transactional
    public ResponseEntity updateTask(@PathVariable UUID idUser,
                                     @PathVariable UUID idTask,
                                     @RequestBody UpdateTask taskUpdated,
                                     @RequestHeader ("AuthToken") String token) {
        var optionalUser = userRepository.findById(idUser);
        if(optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        var user = optionalUser.get();
        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var taskOptional = user.getTasks().stream().filter(t -> t.getId().equals(idTask)).findAny();
        if(taskOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não encontrado."));
        }
        var task = taskOptional.get();
        task.updateTask(taskUpdated);
        userTaskRepository.save(task);

        return ResponseEntity.noContent().build();
    }

}
