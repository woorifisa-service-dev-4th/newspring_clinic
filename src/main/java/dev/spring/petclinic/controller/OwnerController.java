package dev.spring.petclinic.controller;

import dev.spring.petclinic.dto.OwnerDto;
import dev.spring.petclinic.dto.OwnerRequestDTO;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;


    /**
     * 검색 기능 & 전체 목록 조회
     * @param lastName
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OwnerDto>> listOwners(@RequestParam(name = "lastName", required = false) String lastName) {
        List<Owner> owners = ownerService.searchOwners(lastName);
        List<OwnerDto> data = owners.stream()
                .map(OwnerDto::new)
                .toList();

        return ResponseEntity.ok(data);
    }

    /**
     *  Owner 저장
     * @param ownerRequestDTO
     * @return
     */
    @PostMapping("/new")
    public ResponseEntity<Owner> addOwner(@RequestBody OwnerRequestDTO ownerRequestDTO) {
        Owner savedOwner = ownerService.addOwner(ownerRequestDTO);
        return ResponseEntity.ok(savedOwner);
    }


    /**
     * Owner 수정 저장
     * @param id
     * @param ownerRequestDTO
     * @return
     */
    @PutMapping("/{id}/edit")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody OwnerRequestDTO ownerRequestDTO) {
        Owner savedOwner = ownerService.saveOwner(id, ownerRequestDTO);
        return ResponseEntity.ok(savedOwner);
    }

    /**
     * 특정 Owner 상세 정보 조회 (GET)
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Owner> showOwnerDetails(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        return ResponseEntity.ok(owner);
    }
}
