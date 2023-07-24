package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dtos.CreateUser;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.UserList;
import com.recados.ApiMuralRecados.models.User;
import com.recados.ApiMuralRecados.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid CreateUser userDto) {
        if(userRepository.existsByEmail(userDto.email())) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário já cadastrado."));
        }
        var user = new User(userDto);
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserList>> listUsers() {
        var data = userRepository.findAll().stream().map(UserList::new).toList();

        return ResponseEntity.ok(data);
    }

}
