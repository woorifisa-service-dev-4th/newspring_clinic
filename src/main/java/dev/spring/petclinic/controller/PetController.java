package dev.spring.petclinic.controller;


import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import dev.spring.petclinic.dto.PetDTO;
import dev.spring.petclinic.service.PetService;
import dev.spring.petclinic.model.PetType;
import dev.spring.petclinic.repository.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
@RequiredArgsConstructor
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;

    // PetType 변환기 등록
    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // Model 설정 공통 메서드
    private void setUpPetFormModel(Model model, Owner owner, PetDTO petDTO, List<PetType> petTypes, boolean isNew) {
        model.addAllAttributes(Map.of(
                "owner", owner,
                "pet", petDTO,
                "types", petTypes,
                "isNew", isNew
        ));
    }


    // "Add new Pet" 클릭 시 새로운 Pet 추가 폼을 받아오는 GET 요청
    @GetMapping("/new")
    public String showAddPetForm(@PathVariable Long ownerId, Model model) {

        Owner owner = ownerService.findById(ownerId);
        PetDTO petDTO = petService.createdNewPetDTO(ownerId);
        List<PetType> petTypes = petService.getAllPetTypes();

        setUpPetFormModel(model, owner, petDTO, petTypes, true);


        return "pets/createOrUpdatePetForm";
    }



    // "Add Pet" 클릭 시 새로운 pet을 저장하는 POST 요청
    @PostMapping("/new")
    public String addPet(@PathVariable Long ownerId, @ModelAttribute("pet") PetDTO petDTO) {

        petService.savePet(ownerId, petDTO);

        return "redirect:/owners/" + ownerId;
    }



    // "Edit Pet" 클릭 시 pet 수정 폼을 전달하는 GET 요청
    @GetMapping("/{petId}/edit")
    public String showEditPetForm(@PathVariable Long ownerId, @PathVariable Long petId, Model model) {

        Owner owner = ownerService.findById(ownerId);
        PetDTO petDTO = petService.getPetDTO(petId);
        List<PetType> petTypes = petService.getAllPetTypes();
        setUpPetFormModel(model, owner, petDTO, petTypes, false);

        return "pets/createOrUpdatePetForm";
    }


    // "Edit Pet" 클릭 시 수정된 pet을 저장하는 POST 요청
    @PostMapping("/{petId}/edit")
    public String updatePet(@PathVariable Long ownerId, @PathVariable Long petId,
                            @ModelAttribute("pet") PetDTO petDTO) {

        petService.updatePet(petId, petDTO);

        return "redirect:/owners/" + ownerId;
    }
}
