package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.Visit;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitDTO {

    private Long id;
    private String date;
    private String description;
    private Long petId;

    // Entity -> DTO 변환
    public static VisitDTO fromEntity(Visit visit) {
        return VisitDTO.builder()
                .id(visit.getId())
                .date(visit.getDate().toString())
                .description(visit.getDescription().toString())
                .petId(visit.getPet().getId())
                .build();
    }
    // DTO -> Entity 변환
    public Visit toEntity (Pet pet) {
        return Visit.builder()
                .id(this.id)
                .date(getParsedDate())
                .description(this.description)
                .pet(pet)
                .build();
    }

    // LocalDate 변환 로직
    public LocalDate getParsedDate() {
        return LocalDate.parse(this.date);
    }




}
