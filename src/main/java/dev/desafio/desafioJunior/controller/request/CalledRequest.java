package dev.desafio.desafioJunior.controller.request;

import dev.desafio.desafioJunior.entity.Client;
import dev.desafio.desafioJunior.enums.PriorityEnum;
import dev.desafio.desafioJunior.enums.StatusEnum;

public record CalledRequest(String title,
                            String description,
                            StatusEnum status,
                            PriorityEnum priority,
                            Long idClient) {
}
