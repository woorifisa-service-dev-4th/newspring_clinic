package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.Visit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRequestDTO {
    private Long petId;
    private String description;
    private LocalDateTime date;

    public Visit toEntity(Pet pet) {
        Visit visit = new Visit();
        visit.setPet(pet);
        visit.setDescription(description);
        visit.setDate(date.toLocalDate());
        return visit;
    }
}
