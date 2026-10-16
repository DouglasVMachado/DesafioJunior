package dev.desafio.desafioJunior.service;

import dev.desafio.desafioJunior.entity.Called;
import dev.desafio.desafioJunior.repository.CalledRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalledService {

    private CalledRespository calledRespository;

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

}
