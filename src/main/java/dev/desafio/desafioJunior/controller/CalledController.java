package dev.desafio.desafioJunior.controller;

import dev.desafio.desafioJunior.controller.request.CalledClientRequest;
import dev.desafio.desafioJunior.controller.request.CalledPriorityRequest;
import dev.desafio.desafioJunior.controller.request.CalledRequest;
import dev.desafio.desafioJunior.controller.request.CalledStatusRequest;
import dev.desafio.desafioJunior.controller.response.CalledResponse;
import dev.desafio.desafioJunior.entity.Called;
import dev.desafio.desafioJunior.enums.PriorityEnum;
import dev.desafio.desafioJunior.enums.StatusEnum;
import dev.desafio.desafioJunior.mapper.CalledMapper;
import dev.desafio.desafioJunior.service.CalledService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calleds")
public class CalledController {

    private final CalledService calledService;

    public CalledController(CalledService calledService) {
        this.calledService = calledService;
    }

    @PostMapping
    public ResponseEntity<CalledResponse> save(@RequestBody CalledRequest calledRequest){
        Called newCalled = calledService.validationCreate(calledRequest);
        newCalled.setOpeningDate(LocalDateTime.now());
        newCalled.setStatus(StatusEnum.ABERTO);
        newCalled.setPriority(PriorityEnum.LOW);
        return ResponseEntity.status(HttpStatus.CREATED).body(CalledMapper.toCalledResponse(newCalled));
    }

    @GetMapping()
    public ResponseEntity<List<CalledResponse>> findAll(){
        List<CalledResponse> calledResponses = calledService.findAll()
                .stream()
                .map(CalledMapper::toCalledResponse)
                .toList();
        return ResponseEntity.ok().body(calledResponses);
    }

    @GetMapping("/status")
    public ResponseEntity<List<CalledResponse>> findAllStatus(@RequestBody CalledStatusRequest request){
        List<CalledResponse> calledResponses = calledService.findAll()
                .stream()
                .filter(called -> called.getStatus() == request.statusEnum())
                .map(CalledMapper::toCalledResponse)
                .toList();
        return ResponseEntity.ok().body(calledResponses);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<CalledResponse>> findAllPriority(@RequestBody CalledPriorityRequest request){
        List<CalledResponse> calledResponses = calledService.findAll()
                .stream()
                .filter(called -> called.getPriority() == request.priorityEnum())
                .map(CalledMapper::toCalledResponse)
                .toList();
        return ResponseEntity.ok().body(calledResponses);
    }

    @GetMapping("/client")
    public ResponseEntity<List<CalledResponse>> findAllClient(@RequestBody CalledClientRequest request){
        List<CalledResponse> calledResponses = calledService.findAll()
                .stream()
                .filter(called -> called.getResponsibleClient().getId() == request.clientId())
                .map(CalledMapper::toCalledResponse)
                .toList();
        return ResponseEntity.ok().body(calledResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalledResponse> findById(@PathVariable Long id){
        return calledService.findById(id)
                .map(called -> ResponseEntity.ok(CalledMapper.toCalledResponse(called)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Called> optCalled = calledService.findById(id);
        if(optCalled.isPresent()){
            calledService.validationDelete(id);
            calledService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalledResponse> update(@PathVariable Long id, @RequestBody CalledRequest calledRequest){
        Optional<Called> optCalled = calledService.findById(id);
        if (optCalled.isPresent()){
            Called newCalled = optCalled.get();
            calledService.validationUpdate(id);
            Called called = CalledMapper.toCalled(calledRequest, newCalled.getResponsibleClient());
            called.setLastUpdated(LocalDateTime.now());
            Called savedCalled = calledService.save(called);
            return ResponseEntity.ok().body(CalledMapper.toCalledResponse(called));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
