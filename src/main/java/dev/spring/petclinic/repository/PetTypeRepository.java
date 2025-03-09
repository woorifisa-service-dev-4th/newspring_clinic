package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    Optional<PetType> findByName(String name);  // ✅ 이름으로 조회 가능
}
