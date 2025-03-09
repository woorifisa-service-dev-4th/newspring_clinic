package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.dto.PetDTO;
import dev.spring.petclinic.model.PetType;
import dev.spring.petclinic.repository.PetRepository;
import dev.spring.petclinic.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final OwnerService ownerService;
    private final PetTypeRepository petTypeRepository;



    public PetDTO createdNewPetDTO(Long ownerId) {
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(ownerId);
        return petDTO;
    }


    public Pet savePet(Long ownerId, PetDTO petDTO) {
        Owner owner = ownerService.findById(ownerId);
        PetType petType = petTypeRepository.findByName(petDTO.getType())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 petType 입니다: " + petDTO.getType()));

        Pet pet = petDTO.toEntity(owner, petType); // 변환은 Service Layer에서 수행
        return petRepository.save(pet);
    }

    public Pet updatePet(Long petId, PetDTO petDTO) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));

        PetType petType = petTypeRepository.findByName(petDTO.getType())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 petType 입니다: " + petDTO.getType()));

        // 기존 Pet 데이터 업데이트
        pet.setName(petDTO.getName());
        pet.setBirthDate(LocalDate.parse(petDTO.getBirthDate()));
        pet.setType(petType);

        return petRepository.save(pet);
    }

    //  Pet 조회 후 DTO로 변환
    public PetDTO getPetDTO(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));

        return PetDTO.fromEntity(pet);
    }

    // PetType 목록 조회
    public List<PetType> getAllPetTypes() {
        return petTypeRepository.findAll();
    }


}
