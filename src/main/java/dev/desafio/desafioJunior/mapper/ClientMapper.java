package dev.desafio.desafioJunior.mapper;

import dev.desafio.desafioJunior.controller.request.ClientRequest;
import dev.desafio.desafioJunior.controller.response.ClientResponse;
import dev.desafio.desafioJunior.entity.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientMapper {

    public Client toClient(ClientRequest clientRequest){
        return Client.builder()
                .name(clientRequest.name())
                .email(clientRequest.email())
                .telephone(clientRequest.telephone())
                .build();
    }

    public ClientResponse toClientResponse(Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .telephone(client.getTelephone())
                .build();
    }

}
