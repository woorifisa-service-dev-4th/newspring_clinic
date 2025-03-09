package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // lastName + 대소문자 구분 없이 검색
    List<Owner> findByLastNameContainingIgnoreCase(String lastName);
}
