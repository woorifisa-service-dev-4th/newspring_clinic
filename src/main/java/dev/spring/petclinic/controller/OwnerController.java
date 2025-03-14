package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDto;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
@Tag(name = "Owner", description = "Owner API for Pet Clinic")
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * 검색 기능 & 전체 목록 조회
     * @param lastName
     * @return
     */
    @GetMapping
    @Operation(summary = "owner 목록 조회", description = "모든 owner 목록을 조회한다.")
    public ResponseEntity<List<OwnerDto>> listOwners(@RequestParam(name = "lastName", required = false) String lastName) {
        List<Owner> owners = ownerService.searchOwners(lastName);
        List<OwnerDto> data = owners.stream()
                .map(OwnerDto::new)
                .toList();

        return ResponseEntity.ok(data);
    }

    /**
     * Owner 저장
     * @param ownerRequestDTO
     * @return
     */
    @PostMapping("/new")
    @Operation(summary = "owner 등록", description = "새로운 owner를 등록한다.")
    public ResponseEntity<Owner> addOwner(@RequestBody OwnerRequestDto ownerRequestDTO) {
        Owner savedOwner = ownerService.addOwner(ownerRequestDTO);
        return ResponseEntity.ok(savedOwner);
    }


    /**
     * Owner 수정 저장
     * @param ownerId
     * @param ownerRequestDTO
     * @return
     */
    @PutMapping("/{owenrId}/edit")
    @Operation(summary = "owner 수정", description = "owner를 수정한다.")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long ownerId, @RequestBody OwnerRequestDto ownerRequestDTO) {
        Owner savedOwner = ownerService.saveOwner(ownerId, ownerRequestDTO);
        return ResponseEntity.ok(savedOwner);
    }

    /**
     * 특정 Owner 상세 정보 조회 (GET)
     * @param ownerId
     * @return
     */
    @GetMapping("/{ownerId}")
    @Operation(summary = "상세 owner 조회", description = "특정 owner의 상세 정보를 조회한다.")
    public ResponseEntity<Owner> showOwnerDetails(@PathVariable Long ownerId) {
        Owner owner = ownerService.findById(ownerId);
        return ResponseEntity.ok(owner);
    }
}
