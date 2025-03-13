package dev.spring.petclinic.model;

import dev.spring.petclinic.dto.VisitRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name ="visits")
public class Visit extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    private String description;

    @Column(name="visit_date")
    private LocalDate date;


    public void updateFromRequestDTO(VisitRequestDTO visitRequestDTO, Pet pet) {
        this.pet = pet;
        this.description = visitRequestDTO.getDescription();
        this.date = visitRequestDTO.getDate().toLocalDate();
    }
}
