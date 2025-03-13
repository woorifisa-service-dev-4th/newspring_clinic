package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Visit;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class VisitResponseDTO {
    private Long id;
    private Long petId;
    private String description;
    private LocalDate date;

    public VisitResponseDTO(Visit visit) {
        this.id = visit.getId();
        this.petId = visit.getPet().getId();
        this.description = visit.getDescription();
        this.date = visit.getDate();
    }
}
