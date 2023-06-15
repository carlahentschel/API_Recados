package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dataBase.DataBase;
import com.recados.ApiMuralRecados.dtos.CreateUser;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.UserList;
import com.recados.ApiMuralRecados.models.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid CreateUser userDto) {
        if(DataBase.userExistByEmail(userDto.email())) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário já cadastrado."));
        }
        var user = new User(userDto);
        DataBase.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserList>> listUsers() {
        var data = DataBase.getUsers().stream().map(UserList::new).toList();

        return ResponseEntity.ok(data);
    }


}
