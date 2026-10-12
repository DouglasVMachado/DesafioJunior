package dev.desafio.desafioJunior.controller;


import dev.desafio.desafioJunior.controller.request.ClientRequest;
import dev.desafio.desafioJunior.controller.response.ClientResponse;
import dev.desafio.desafioJunior.entity.Client;
import dev.desafio.desafioJunior.mapper.ClientMapper;
import dev.desafio.desafioJunior.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest clientRequest){
        Client newClient = ClientMapper.toClient(clientRequest);
        Client savedClient = clientService.create(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toClientResponse(savedClient));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(){
        List<ClientResponse> clientResponses = clientService.findAll()
                .stream()
                .map(ClientMapper::toClientResponse)
                .toList();
        return ResponseEntity.ok().body(clientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id){
        return clientService.findById(id)
                .map(client -> ResponseEntity.ok(ClientMapper.toClientResponse(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Client> optClient = clientService.findById(id);
        if(optClient.isPresent()){
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
