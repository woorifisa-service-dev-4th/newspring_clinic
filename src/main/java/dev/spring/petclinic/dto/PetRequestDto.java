package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.PetType;
import lombok.*;

import java.time.LocalDate;




@Data
@Builder
@Setter
@Getter
public class PetRequestDto {
    private String name;
    private LocalDate birthDate;
    private PetType type;
}
