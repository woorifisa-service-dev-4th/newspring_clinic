package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    //  Owner 저장
    public Owner saveOwner(Owner owner) {
        if (owner.getId() != null) { // 기존 Owner 수정 시
            Owner existingOwner = ownerRepository.findById(owner.getId())
                    .orElseThrow(() -> new RuntimeException("Owner가 존재하지 않습니다"));

            // 기존 pets 리스트 유지 (새로운 owner에 덮어쓰기)
            if (owner.getPets() == null || owner.getPets().isEmpty()) {
                owner.setPets(existingOwner.getPets());
            }
        }

        return ownerRepository.save(owner); // Hibernate가 기존 pets와 비교 가능하게 만듦
    }

    // 🔹 특정 Owner 조회
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new RuntimeException("Owner가 존재하지 않습니다"));
    }

    // 🔹 검색 기능
    public List<Owner> searchOwners(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        }
        return ownerRepository.findAll();
    }
}
