package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Visit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class VisitRequestDTO {
    private Long petId;
    private String description;
    private LocalDate date;

    public Visit toEntity() {
        Visit visit = new Visit();
        visit.setDescription(description);
        visit.setDate(date);
        return visit;
    }
}
