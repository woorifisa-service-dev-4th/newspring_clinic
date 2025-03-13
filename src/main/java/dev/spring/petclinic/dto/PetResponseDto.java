package dev.spring.petclinic.dto;

import java.time.LocalDate;

import dev.spring.petclinic.model.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
public class PetResponseDto {
	private Long id;
	private String name;
	private LocalDate birthDate;
	private PetType type;
}
