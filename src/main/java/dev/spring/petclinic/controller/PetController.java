package dev.spring.petclinic.controller;


import dev.spring.petclinic.dto.PetResponseDto;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.service.OwnerService;
import dev.spring.petclinic.dto.PetRequestDto;
import dev.spring.petclinic.service.PetService;
import dev.spring.petclinic.model.PetType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "Pet API for Pet Clinic")
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;

    // 새로운 pet을 저장하는 POST
    @Operation(summary = "pet 등록", description = "새로운 pet을 등록한다.")
    @PostMapping("/{ownerId}/new")
    public ResponseEntity<?> postPet (@PathVariable Long ownerId, @RequestBody  PetRequestDto petRequestDto){

        Pet savedPet = petService.savePet(ownerId,petRequestDto);

        // pet 정보를 Dto로 반환
        PetResponseDto response = PetResponseDto.builder()
            .id(savedPet.getId())
            .name(savedPet.getName())
            .birthDate(savedPet.getBirthDate())
            .type(savedPet.getType())
            .build();

        return ResponseEntity.ok(response);
    }



    // 수정된 pet을 저장
    @Operation(summary = "pet 수정", description = "기존 pet을 수정한다.")
    @PutMapping("/{ownerId}/update/{petId}")
    public ResponseEntity<PetResponseDto> updatePet (@PathVariable Long ownerId,@PathVariable Long petId,@RequestBody PetRequestDto petRequestDto){

        Pet updatedPet = petService.updatePet(ownerId,petId,petRequestDto);

        // pet 정보를 Dto로 반환
        PetResponseDto response = PetResponseDto.builder()
            .id(updatedPet.getId())
            .name(updatedPet.getName())
            .birthDate(updatedPet.getBirthDate())
            .type(updatedPet.getType())
            .build();

        return ResponseEntity.ok(response);


    }
}
