package dev.spring.petclinic.controller;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    //  검색 기능 & 전체 목록 조회
    @GetMapping
    public String listOwners(@RequestParam(name="lastName", required = false) String lastName,
                             @RequestParam(defaultValue = "1") int page, Model model) {

        List<Owner> owners = ownerService.searchOwners(lastName);

        model.addAllAttributes(Map.of(
                "listOwners", owners,
                "lastName", lastName,
                "currentPage", page,
                "totalPages", 1
        ));

        return "owners/ownersList";
    }

    // Owner 검색 폼을 보여주는 GET 요청
    @GetMapping("/find")
    public String showFindOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    // Owner 추가 폼을 보여주는 GET 요청
    @GetMapping("/new")
    public String showAddOwnersForm(Model model) {
        Owner owner = ownerService.createNewOwner(); // 새로운 Owner 객체 생성 메서드 호출
        model.addAllAttributes(Map.of(
                "owner", owner,
                "isNew", true
        ));
        return "owners/createOrUpdateOwnerForm";
    }

    // Owner 저장 (POST)
    @PostMapping("/new")
    public String addOwner(@ModelAttribute("owner") Owner owner) {
        Owner savedOwner = ownerService.saveOwner(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    // Owner 수정 폼 요청
    @GetMapping("/{id}/edit")
    public String showEditOwnerForm(@PathVariable Long id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAllAttributes(Map.of(
                "owner", owner,
                "isNew", false
        ));
        return "owners/createOrUpdateOwnerForm";
    }

    // Owner 수정 저장 (POST)
    @PostMapping("/{id}/edit")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        Owner savedOwner = ownerService.saveOwner(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    //  특정 Owner 상세 정보 조회 (GET)
    @GetMapping("{id}")
    public String showOwnerDetails(@PathVariable Long id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return "owners/ownerDetails";
    }
}
