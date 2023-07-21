package com.recados.ApiMuralRecados.models;

import com.recados.ApiMuralRecados.dtos.CreateUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
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
