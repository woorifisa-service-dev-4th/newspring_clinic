package dev.spring.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Vet {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Specialty> specialties;
}