package dev.spring.petclinic.controller;

import dev.spring.petclinic.model.Vet;
import dev.spring.petclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/vets")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping
    public String listVets(Model model) {
        List<Vet> vets = vetService.findAllVets();
        model.addAttribute("listVets", vets);
        return "vets/vetList";
    }

    @GetMapping("/vets.xml")
    @ResponseBody
    public Resource getVetsXml() {
        return new ClassPathResource("static/resources/vets.xml");
    }

    @GetMapping("/vets.json")
    @ResponseBody
    public Resource getVetsJson() {
        return new ClassPathResource("static/resources/vets.json");
    }
}