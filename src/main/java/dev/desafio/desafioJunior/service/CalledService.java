package dev.desafio.desafioJunior.service;

import dev.desafio.desafioJunior.controller.request.CalledRequest;
import dev.desafio.desafioJunior.entity.Called;
import dev.desafio.desafioJunior.entity.Client;
import dev.desafio.desafioJunior.enums.StatusEnum;
import dev.desafio.desafioJunior.mapper.CalledMapper;
import dev.desafio.desafioJunior.repository.CalledRespository;
import dev.desafio.desafioJunior.repository.ClientRepository;
import org.aspectj.weaver.ast.Call;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalledService {

    private final CalledRespository calledRespository;
    private final ClientRepository clientRepository;

    public CalledService(CalledRespository calledRespository, ClientRepository clientRepository) {
        this.calledRespository = calledRespository;
        this.clientRepository = clientRepository;
    }

    public Called save(Called called){
        return calledRespository.save(called);
    }

    public List<Called> findAll(){
        return calledRespository.findAll();
    }

    public Optional<Called> findById(Long id){
        return calledRespository.findById(id);
    }

    public void delete(Long id){
        calledRespository.deleteById(id);
    }

    public Called update(Long id, Called called){
        if(calledRespository.existsById(id)){
            called.setId(id);
            return calledRespository.save(called);
        }
        return null;
    }

    public Called validationCreate(CalledRequest calledRequest){

        Optional<Client> newClient = clientRepository.findById(calledRequest.idClient());

        if(newClient.isEmpty()){
            throw new IllegalArgumentException("Cliente nao existe, chamada negada");
        }

        if(calledRequest.description().isEmpty()){
            throw new IllegalArgumentException("Descrição não pode estar vazio.");
        }

        if (calledRequest.title().isEmpty()){
            throw new IllegalArgumentException("Titulo nao pode estar vazio.");
        }

        Client saveClient = newClient.get();

        Called newCalled = CalledMapper.toCalled(calledRequest, saveClient);

        return newCalled;

    }

    public Void validationUpdate(Long id){
        Optional<Called> called = calledRespository.findById(id);
        if(called.isEmpty()){
            throw new IllegalArgumentException("Nao existe esse chamado");
        }
        if(called.get().getStatus() == StatusEnum.RESOLVIDO){
            throw new IllegalArgumentException("Chamado ja foi resolvido, crie um novo chamado");
        }
        if(called.get().getStatus() == StatusEnum.CANCELADO){
            throw new IllegalArgumentException("Chamado cancelado, tente novamente");
        }
        return null;
    }


    public Void validationDelete(Long id){
        Optional<Called> called = calledRespository.findById(id);
        if(called.get().getStatus() == StatusEnum.RESOLVIDO){
            throw new IllegalArgumentException("Chamado ja foi resolvido nao pode ser cancelado.");
        }
        return null;
    }

}
