package dev.spring.petclinic.service;

import dev.spring.petclinic.dto.VisitRequestDTO;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.repository.PetRepository;
import dev.spring.petclinic.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    /**
     *  진료 기록 저장
     */
    @Transactional
    public Visit save(VisitRequestDTO visitRequestDTO) {
        Pet pet = petRepository.findById(visitRequestDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("해당 Pet이 존재하지 않습니다: " + visitRequestDTO.getPetId()));

        Visit visit = visitRequestDTO.toEntity(pet);
        return visitRepository.save(visit);
    }

    /**
     * 모든 진료 기록 조회
     */
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    /**
     *  특정 ID의 진료 기록 조회
     */
    public Visit getVisitById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 방문 기록이 존재하지 않습니다: " + id));
    }

    /**
     * 진료 기록 업데이트
     */
    @Transactional
    public Visit updateVisit(Long id, VisitRequestDTO visitRequestDTO) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 방문 기록이 존재하지 않습니다: " + id));

        Pet pet = petRepository.findById(visitRequestDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("해당 Pet이 존재하지 않습니다: " + visitRequestDTO.getPetId()));

        visit.updateFromRequestDTO(visitRequestDTO, pet);
        return visitRepository.save(visit);
    }

    /**
     *  진료 기록 삭제
     */
    @Transactional
    public void deleteVisit(Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 방문 기록이 존재하지 않습니다: " + id));
        visitRepository.delete(visit);
    }
}
