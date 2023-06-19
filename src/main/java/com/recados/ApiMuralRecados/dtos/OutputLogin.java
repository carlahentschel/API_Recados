package com.recados.ApiMuralRecados.dtos;

import java.util.UUID;

public record OutputLogin(
        UUID id,
        String name,

        String token
) {
}
