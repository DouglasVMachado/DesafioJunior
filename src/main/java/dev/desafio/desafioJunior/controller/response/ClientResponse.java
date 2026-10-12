package dev.desafio.desafioJunior.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClientResponse(Long id,
                             String name,
                             String email,
                             String telephone,
                             LocalDateTime register) {
}
