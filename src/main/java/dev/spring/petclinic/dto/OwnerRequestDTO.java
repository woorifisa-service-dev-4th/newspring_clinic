package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerRequestDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<Pet> pets;


    public Owner toEntity() {
        Owner.OwnerBuilder<?, ?> builder = Owner.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .address(this.address)
                .city(this.city)
                .telephone(this.telephone);

        if (this.pets != null) {
            this.pets.forEach(builder::pet); // 개별 추가
        }

        return builder.build();
    }

}
