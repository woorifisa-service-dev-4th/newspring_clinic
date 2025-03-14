package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.VisitRequestDTO;
import dev.spring.petclinic.dto.VisitResponseDTO;
import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners/{ownerId}/pets/{petId}/visits")
@RequiredArgsConstructor
@Tag(name = "Visit", description = "Visit API for Pet Clinic")
public class VisitController {

    private final VisitService visitService;

    @Operation(summary = "새 진료 추가", description = "특정 반려동물에 대한 새로운 진료를 등록한다.")
    @PostMapping("/new")
    public ResponseEntity<VisitResponseDTO> createVisit(@PathVariable Long ownerId, @PathVariable Long petId,
                                                        @RequestBody VisitRequestDTO visitRequestDTO) {
        Visit savedVisit = visitService.save(ownerId, petId, visitRequestDTO);
        return ResponseEntity.ok(new VisitResponseDTO(savedVisit));
    }

    @Operation(summary = "모든 진료 기록 조회", description = "모든 진료 기록을 조회한다.")
    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAllVisits(@PathVariable Long ownerId, @PathVariable Long petId) {
        List<VisitResponseDTO> visits = visitService.getAllVisits(ownerId, petId)
                .stream()
                .map(VisitResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @Operation(summary = "특정 ID의 진료 기록 조회", description = "특정 ID의 진료 기록을 조회한다.")
    @GetMapping("/{visitId}")
    public ResponseEntity<VisitResponseDTO> getVisitById(@PathVariable Long ownerId, @PathVariable Long petId,
                                                         @PathVariable Long visitId) {
        Visit visit = visitService.getVisitById(ownerId, petId, visitId);
        return ResponseEntity.ok(new VisitResponseDTO(visit));
    }
}
