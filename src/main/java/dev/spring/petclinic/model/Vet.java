package dev.spring.petclinic.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vet {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Specialty> specialties;
}