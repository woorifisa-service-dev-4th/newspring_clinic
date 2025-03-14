package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByIdAndOwnerId(Long id, Long ownerId);

}
