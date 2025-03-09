package dev.spring.petclinic.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "owners")
public class Owner extends BaseEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    /*
     @OneToMany : Owner는 여러 마리의 Pet을 가질 수 있음
     Owner가 save() 될 때 관련된 Pet도 함께 저장됨
     만약 Pets 필드가 null or empty 라면, Hibernate는 기존 pets와의 연결이 끊어졌다고 판단하고 orphanRemoval=true 규칙에 따라 삭제 시도함.
     */

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true) // ✅ 연관 관계 설정
    private List<Pet> pets = new ArrayList<>();

}
