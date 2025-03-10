package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.dto.PetDTO;
import dev.spring.petclinic.model.PetType;
import dev.spring.petclinic.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final OwnerService ownerService;

    /**
     * 새로운 PetDTO 객체를 생성하여 기본값 설정
     *
     * @param ownerId Pet을 소유할 Owner의 ID
     * @return 생성된 PetDTO 객체
     */
    public PetDTO createNewPetDTO(Long ownerId) {
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(ownerId);
        return petDTO;
    }

    /**
     * PetDTO를 Entity로 변환한 후 저장
     *
     * @param ownerId Pet을 소유할 Owner의 ID
     * @param petDTO 저장할 PetDTO 객체
     * @return 저장된 Pet Entity 객체
     */
    public Pet savePet(Long ownerId, PetDTO petDTO) {
        Owner owner = ownerService.findById(ownerId);
        Pet pet = petDTO.toEntity(owner); //  PetDTO가 직접 변환 수행
        return petRepository.save(pet);
    }

    /**
     * 기존 Pet 정보 업데이트
     *
     * @param petId 수정할 Pet의 ID
     * @param petDTO 수정할 데이터가 담긴 PetDTO 객체
     * @return 수정된 Pet Entity 객체
     */
    public Pet updatePet(Long petId, PetDTO petDTO) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));

        // PetTypeFormatter 덕분에 직접 할당 가능
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getParsedBirthDate());
        pet.setType(petDTO.getType());

        return petRepository.save(pet);
    }

    /**
     * 특정 Pet을 조회한 후 DTO로 변환하여 반환
     *
     * @param petId 조회할 Pet의 ID
     * @return 조회된 PetDTO 객체
     */
    public PetDTO getPetDTO(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));

        return PetDTO.fromEntity(pet);
    }

    public Pet findById(Long petId) {
        return petRepository.findById(petId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 Pet ID: " + petId));
    }


    /**
     * 모든 PetType을 조회하여 반환
     *
     * @return PetType 리스트
     */
    public List<PetType> getAllPetTypes() {
        return petRepository.findAllPetTypes();
    }
}

