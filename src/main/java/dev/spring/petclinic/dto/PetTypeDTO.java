package dev.spring.petclinic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetTypeDTO {
    private Long id;  // ID 추가 (PetType의 기본키)
    private String name;  // PetType의 name 값만 저장


}
