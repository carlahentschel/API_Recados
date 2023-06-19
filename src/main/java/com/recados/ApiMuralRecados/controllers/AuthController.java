package com.recados.ApiMuralRecados.controllers;

import com.recados.ApiMuralRecados.dataBase.DataBase;
import com.recados.ApiMuralRecados.dtos.ErrorData;
import com.recados.ApiMuralRecados.dtos.OutputLogin;
import com.recados.ApiMuralRecados.dtos.RequestLogin;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid RequestLogin login) {
        try {
            var user = DataBase.getEmail(login.email());
            if(user.getPassword().equals(login.password())) {
                var token = user.generateToken();
                return ResponseEntity.ok().body(new OutputLogin(user.getId(), user.getName(), token));
            }
            return ResponseEntity.badRequest().body(new ErrorData("E-mail ou senha inválidos."));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new ErrorData("Login inválido."));
        }
    }
}
