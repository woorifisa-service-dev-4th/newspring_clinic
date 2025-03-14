package dev.spring.petclinic.service;



import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.dto.PetRequestDto;
import dev.spring.petclinic.repository.OwnerRepository;
import dev.spring.petclinic.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Pet savePet(Long ownerId, PetRequestDto petRequestDto) {
        Owner owner = ownerRepository.findById(ownerId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + ownerId));

        Pet pet = new Pet();
        pet.setOwner(owner);
        pet.setName(petRequestDto.getName());
        pet.setBirthDate(petRequestDto.getBirthDate());
        pet.setType(petRequestDto.getType());

        return petRepository.save(pet);
    }

    @Transactional
    public Pet updatePet(Long petId, PetRequestDto petRequestDto) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));

        if (petRequestDto.getName() != null) {
            pet.setName(petRequestDto.getName());
        }
        if (petRequestDto.getBirthDate() != null) {
            pet.setBirthDate(petRequestDto.getBirthDate());
        }
        if (petRequestDto.getType() != null) {
            pet.setType(petRequestDto.getType());
        }

        return petRepository.save(pet);
    }

    public Pet findById(Long petId) {
        return petRepository.findById(petId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));
    }

}

