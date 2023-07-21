package com.recados.ApiMuralRecados.models;

import com.recados.ApiMuralRecados.dtos.CreateUser;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class User {
    private String name;
    private UUID id;
    private String email;
    private String password;
    private List<UserTask> tasks;
    private String tokenLogin;

    public User(CreateUser userDto) {
        name = userDto.name();
        email = userDto.email();
        password = userDto.password();
        id = UUID.randomUUID();
        tasks = new ArrayList<>();
    }

    public boolean isAuthenticated(String token) {
        return tokenLogin != null && tokenLogin.equals(token);
    }

    public String generateToken() {
        tokenLogin = UUID.randomUUID().toString();
        return tokenLogin;
    }


}
