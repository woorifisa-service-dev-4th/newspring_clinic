package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPetIdAndPetOwnerId(Long petId, Long ownerId);
    Optional<Visit> findByIdAndPetIdAndPetOwnerId(Long id, Long petId, Long ownerId);

}
