package dev.spring.petclinic.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "pets")
public class Pet extends BaseEntity {


    private String name;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;


    /*
    @ManyToOne : 여러 pet은 하나의 type을 가짐
    nullable = false : Pet의 입장에서 반드시 type을 가져야함
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private PetType type;


    /*
    @ManyToOne : 여러 pet은 하나의 owner를 가질 수 있음
    nullable = false : Pet의 입장에서는 반드시 Owner를 가져야 함
     */
    @ManyToOne(fetch = FetchType.LAZY)  //  Owner와 다대일 관계 (N:1)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;


    /*
    @OneToMany : 하나의 pet은 여러 visits을 가짐
    Pet이 null or Empty임에 따라 삭제되어야 함
     */
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits;

    @Override
    public String toString() {
        return name;
    }


}
