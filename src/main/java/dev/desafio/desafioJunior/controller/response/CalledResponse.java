package dev.desafio.desafioJunior.controller.response;

import dev.desafio.desafioJunior.entity.Client;
import dev.desafio.desafioJunior.enums.PriorityEnum;
import dev.desafio.desafioJunior.enums.StatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CalledResponse(Long id,
                             String title,
                             String description,
                             StatusEnum status,
                             PriorityEnum priority,
                             LocalDateTime openingDate,
                             LocalDateTime lastUpdated,
                             Client responsibleClient) {
}
