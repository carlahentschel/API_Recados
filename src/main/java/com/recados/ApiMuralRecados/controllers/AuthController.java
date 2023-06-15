package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dataBase.DataBase;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.RequestLogin;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid RequestLogin login) {
        try {
            var user = DataBase.getEmail(login.email());
            if(user.getPassword().equals(login.password())) {
                var token = user.generateToken();
                return ResponseEntity.ok().body(token);
            }
            return ResponseEntity.badRequest().body(new ErrorData("E-mail ou senha inválidos."));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ErrorData("Login inválido."));
        }
    }
}