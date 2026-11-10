package dev.desafio.desafioJunior.mapper;

import dev.desafio.desafioJunior.controller.request.CalledRequest;
import dev.desafio.desafioJunior.controller.response.CalledResponse;
import dev.desafio.desafioJunior.entity.Called;
import dev.desafio.desafioJunior.entity.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CalledMapper {

    public Called toCalled(CalledRequest calledRequest, Client client){
        return Called.builder()
                .title(calledRequest.title())
                .description(calledRequest.description())
                .status(calledRequest.status())
                .priority(calledRequest.priority())
                .responsibleClient(client)
                .build();
    }

    public CalledResponse toCalledResponse(Called called){
        return CalledResponse.builder()
                .id(called.getId())
                .title(called.getTitle())
                .description(called.getDescription())
                .status(called.getStatus())
                .priority(called.getPriority())
                .openingDate(called.getOpeningDate())
                .lastUpdated(called.getLastUpdated())
                .responsibleClient(called.getResponsibleClient())
                .build();
    }

}
