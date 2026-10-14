package dev.desafio.desafioJunior.repository;

import dev.desafio.desafioJunior.entity.Called;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalledRespository extends JpaRepository<Called, Long> {
}
