package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.VisitRequestDTO;
import dev.spring.petclinic.dto.VisitResponseDTO;
import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    /**
     * 새 진료 추가
     */
    @PostMapping("/add")
    public ResponseEntity<VisitResponseDTO> createVisit(@RequestBody VisitRequestDTO visitRequestDTO) {
        Visit savedVisit = visitService.save(visitRequestDTO);
        return ResponseEntity.ok(new VisitResponseDTO(savedVisit));
    }

    /**
     * 모든 진료 기록 조회
     */
    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAllVisits() {
        List<VisitResponseDTO> visits = visitService.getAllVisits()
                .stream()
                .map(VisitResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    /**
     * 특정 ID의 진료 기록 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<VisitResponseDTO> getVisitById(@PathVariable Long id) {
        Visit visit = visitService.getVisitById(id);
        return ResponseEntity.ok(new VisitResponseDTO(visit));
    }


}
