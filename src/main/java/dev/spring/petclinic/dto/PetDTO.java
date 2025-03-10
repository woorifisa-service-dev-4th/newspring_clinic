package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;
import lombok.*;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {
    private Long id;
    private String name;
    private String birthDate;
    private PetType type;
    private Long ownerId;

    // Entity → DTO 변환
    public static PetDTO fromEntity(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate().toString())
                .type(pet.getType()) // PetType 객체 직접 할당
                .ownerId(pet.getOwner().getId())
                .build();
    }

    // DTO → Entity 변환
    public Pet toEntity(Owner owner) {
        return Pet.builder()
                .id(this.id)
                .name(this.name)
                .birthDate(getParsedBirthDate())
                .type(this.type)
                .owner(owner)
                .build();
    }

    // LocalDate 변환 로직
    public LocalDate getParsedBirthDate() {
        return LocalDate.parse(this.birthDate);
    }

}
