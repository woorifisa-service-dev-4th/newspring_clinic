package dev.spring.petclinic.service;

import dev.spring.petclinic.dto.OwnerRequestDto;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> searchOwners(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return ownerRepository.findAll();
        }
        return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Transactional
    public Owner addOwner(OwnerRequestDto ownerRequestDTO) {
        return ownerRepository.save(ownerRequestDTO.toEntity());
    }

    @Transactional
    public Owner saveOwner(Long id, OwnerRequestDto ownerRequestDTO) {
        Optional<Owner> existingOwner = ownerRepository.findById(id);
        if (existingOwner.isPresent()) {
            return ownerRepository.save(ownerRequestDTO.toEntity(id));
        }
        return null;
    }

    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }
}
