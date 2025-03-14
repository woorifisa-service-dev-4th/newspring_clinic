package dev.spring.petclinic.service;

import dev.spring.petclinic.dto.VisitRequestDTO;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.repository.PetRepository;
import dev.spring.petclinic.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitService {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    @Transactional
    public Visit save(Long ownerId, Long petId, VisitRequestDTO visitRequestDTO) {
        Optional<Pet> pet = petRepository.findByIdAndOwnerId(petId, ownerId);
        if (pet.isPresent()) {
            Visit visit = visitRequestDTO.toEntity();
            visit.setPet(pet.get());
            return visitRepository.save(visit);
        }
        throw new IllegalArgumentException("Pet not found for the given owner.");
    }

    public List<Visit> getAllVisits(Long ownerId, Long petId) {
        return visitRepository.findByPetIdAndPetOwnerId(petId, ownerId);
    }

    public Visit getVisitById(Long ownerId, Long petId, Long visitId) {
        return visitRepository.findByIdAndPetIdAndPetOwnerId(visitId, petId, ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found."));
    }
}
