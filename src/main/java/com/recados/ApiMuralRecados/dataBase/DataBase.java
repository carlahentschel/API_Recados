package com.recados.ApiMuralRecados.dataBase;

import com.recados.ApiMuralRecados.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase {

    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        if(user.getEmail() == null) {
            throw new RuntimeException("Usuário inválido.");
        }
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static boolean userExistByEmail(String email) {
        var usersFiltered = users.stream().filter(user -> user.getEmail().equals(email)).findAny();

        return usersFiltered.isPresent();
    }

    public static User getUserById(UUID id) {
        var userOptional = users.stream().filter(user -> user.getId().equals(id)).findAny();

        return userOptional.orElse(null);
    }

    public static User getEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow();
    }

}
