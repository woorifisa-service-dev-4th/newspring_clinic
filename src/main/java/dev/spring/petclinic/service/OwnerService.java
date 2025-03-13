package dev.spring.petclinic.service;

import dev.spring.petclinic.dto.OwnerRequestDTO;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;


    /**
     * 새로운 Owner 객체 생성
     *
     * @return 기본값이 설정된 Owner 객체
     */
    public Owner createNewOwner() {
        return new Owner(); // 기본 생성자 호출
    }

    @Transactional
    public Owner saveOwner(OwnerRequestDTO ownerRequestDTO) {
        return ownerRepository.save(ownerRequestDTO.toEntity());
    }
    /**
     * Owner 저장 또는 수정
     *
     * @param owner 저장할 Owner 객체
     * @return 저장된 Owner 객체
     */
    @Transactional
    public Owner saveOwner(Long id, OwnerRequestDTO ownerRequestDTO) {
        // 기존 Owner 수정 시
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner가 존재하지 않습니다"));

        // 기존 Owner의 pets 리스트 유지
        if (ownerRequestDTO.getPets() == null || ownerRequestDTO.getPets().isEmpty()) {
            ownerRequestDTO.setPets(existingOwner.getPets());
        }


        return ownerRepository.save(ownerRequestDTO.toEntity());
    }

    /**
     * 특정 ID를 가진 Owner 조회
     *
     * @param id 조회할 Owner의 ID
     * @return 조회된 Owner 객체
     * @throws RuntimeException Owner가 존재하지 않을 경우 예외 발생
     */
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner가 존재하지 않습니다"));
    }

    /**
     * Owner 검색 기능
     * - lastName이 주어지면 해당 성(lastName)이 포함된 Owner 목록 반환
     * - lastName이 없으면 전체 Owner 목록 반환
     *
     * @param lastName 검색할 성 (null 가능)
     * @return 검색된 Owner 목록
     */
    public List<Owner> searchOwners(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            return ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        }
        return ownerRepository.findAll();
    }

    /**
     * Owner 삭제
     *
     * @param id 삭제할 Owner의 ID
     */
    @Transactional
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
