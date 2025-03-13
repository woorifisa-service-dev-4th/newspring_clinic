package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OwnerDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    public static OwnerDto from(Owner owner) {
        return OwnerDto.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .address(owner.getAddress())
                .city(owner.getCity())
                .telephone(owner.getTelephone())
                .build();
    }

    public OwnerDto(Owner owner) {
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.address = owner.getAddress();
        this.city = owner.getCity();
        this.telephone = owner.getTelephone();
    }
}
