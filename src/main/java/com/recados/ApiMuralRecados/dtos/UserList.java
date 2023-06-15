package com.recados.ApiMuralRecados.dtos;

import com.recados.ApiMuralRecados.models.User;
import com.recados.ApiMuralRecados.models.UserTask;

import java.util.List;
import java.util.UUID;

public record UserList(
        UUID id,
        String name,
        String email,
        String password,
        List<UserTask> tasks

) {

    public UserList(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getTasks());

    }
}
