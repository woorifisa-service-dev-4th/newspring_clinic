package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    /**
     * 모든 PetType을 조회하는 메서드
     * Pet 테이블에서 PetType 목록을 가져옴
     */
    @Query("SELECT DISTINCT p.type FROM Pet p")
    List<PetType> findAllPetTypes();


}
