package dev.spring.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.Visit;
import dev.spring.petclinic.service.PetService;
import dev.spring.petclinic.service.VisitService;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

	private final VisitService visitService;
	private final PetService petService;

	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}

	// 새로운 방문(Visit) 추가 폼
	@GetMapping("/new")
	public String showVisitForm(@PathVariable Long petId, Model model) {
		Pet pet = petService.findById(petId);
		Visit visit = new Visit();

		model.addAttribute("visit", visit);
		model.addAttribute("pet", pet);
		model.addAttribute("owner", pet.getOwner());
		//model.addAttribute("isNew", true);

		return "pets/createOrUpdateVisitForm"; // pets visit 폼 렌더링
	}

	// 새로운 방문(Visit) 저장
	@PostMapping("/new")
	public String saveVisit(@PathVariable Long petId, @ModelAttribute Visit visit) {
		Pet pet = petService.findById(petId);
		visit.setPet(pet);
		visitService.save(visit);
		return "redirect:/owners/" + pet.getOwner().getId();
	}
}

