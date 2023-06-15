package com.recados.ApiMuralRecados.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateUser(
        @NotNull
        @NotBlank
        @Length(min = 3, max = 30)
        String name,
        @NotNull
        @NotBlank
        @Email
        String email,

        @NotNull
        @NotBlank
        String password) {

}
