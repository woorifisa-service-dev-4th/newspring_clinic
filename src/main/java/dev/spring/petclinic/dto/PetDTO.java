package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    private Long id;
    private String name;
    private String birthDate;
    private String type;
    private Long ownerId;

    // Entity → DTO 변환
    public static PetDTO fromEntity(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate().toString())
                .type(pet.getType().getName())
                .ownerId(pet.getOwner().getId())
                .build();
    }

    //  DTO → Entity 변환
    public Pet toEntity(Owner owner, PetType petType) {
        return Pet.builder()
                .id(this.id)
                .name(this.name)
                .birthDate(LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .type(petType)
                .owner(owner)
                .build();
    }

}
