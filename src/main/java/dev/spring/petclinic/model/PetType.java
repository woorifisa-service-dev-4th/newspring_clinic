package dev.spring.petclinic.model;

import dev.spring.petclinic.model.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "types")  // ✅ DB의 "types" 테이블과 매핑
public class PetType extends BaseEntity {

    private String name;  // ✅ "dog", "cat" 등 유형을 저장하는 필드


    @Override
    public String toString() {
        return name;
    }

}
