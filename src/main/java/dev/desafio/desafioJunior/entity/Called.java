package dev.desafio.desafioJunior.entity;


import dev.desafio.desafioJunior.enums.PriorityEnum;
import dev.desafio.desafioJunior.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "calleds")
public class Called {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private StatusEnum status;
    private PriorityEnum priority;
    private LocalDateTime openingDate;
    private LocalDateTime lastUpdated;
    private Client responsibleClient;

}
