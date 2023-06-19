package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dataBase.DataBase;
import com.recados.ApiMuralRecados.dtos.CreateTask;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.OutputTask;
import com.recados.ApiMuralRecados.dtos.UpdateTask;
import com.recados.ApiMuralRecados.models.UserTask;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class UserTaskController {

    @PostMapping
    public ResponseEntity createTasks(@RequestBody @Valid CreateTask newTask,
                                      @RequestHeader ("AuthToken") String token) {
        var user = DataBase.getUserById(newTask.userId());

        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }
        user.getTasks().add(new UserTask(newTask));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity getAllTasks(@PathVariable UUID idUser,
                                      @RequestHeader ("AuthToken") String token,
                                      @RequestParam(required = false) String title,
                                      @RequestParam(required = false) Boolean finished,
                                      @RequestParam(required = false) Boolean favorite) {

        var user = DataBase.getUserById(idUser);
        if(user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var tasks = DataBase.getUserById(idUser).getTasks();

        if(title != null) {
            tasks = tasks.stream().filter(t -> t.getTitle().contains((title))).toList();
        }

        if(finished != null) {
            tasks = tasks.stream().filter(t -> t.isFinished() == finished).toList();
        }

        if(favorite != null) {
            tasks = tasks.stream().filter(t -> t.isFavorite() == favorite).toList();
        }

        return ResponseEntity.ok().body(tasks.stream().map(OutputTask::new).toList());
    }

    @DeleteMapping("/{idUser}/{idTask}")
    public ResponseEntity deleteTask(@PathVariable UUID idUser,
                                     @PathVariable UUID idTask,
                                     @RequestHeader ("AuthToken") String token) {
        var user = DataBase.getUserById(idUser);
        if(user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var taskOptional = user.getTasks().stream().filter(u -> u.getId().equals(idTask)).findAny();
        if (taskOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não localizado."));
        }

        user.getTasks().remove(taskOptional.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idUser}/{idTask}")
    public ResponseEntity updateTask(@PathVariable UUID idUser,
                                     @PathVariable UUID idTask,
                                     @RequestBody UpdateTask taskUpdated,
                                     @RequestHeader ("AuthToken") String token) {
        var user = DataBase.getUserById(idUser);
        if(user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }

        if(!user.isAuthenticated(token)) {
            return ResponseEntity.badRequest().body(new ErrorData("Token inválido."));
        }

        var taskOptional = user.getTasks().stream().filter(t -> t.getId().equals(idTask)).findAny();
        if(taskOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não encontrado."));
        }
        taskOptional.get().updateTask(taskUpdated);

          return ResponseEntity.noContent().build();
    }

}
