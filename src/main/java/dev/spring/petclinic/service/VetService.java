package dev.spring.petclinic.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.spring.petclinic.model.Vet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VetService {

    private final ObjectMapper objectMapper;
    private List<Vet> vets;

    @Value("classpath:static/resources/vets.json")
    private org.springframework.core.io.Resource vetsJson;

    @PostConstruct
    public void init() throws IOException {
        Map<String, List<Vet>> vetMap = objectMapper.readValue(vetsJson.getInputStream(), new TypeReference<>() {});
        this.vets = vetMap.get("vetList");
    }

    public List<Vet> findAllVets() {
        return vets;
    }


}