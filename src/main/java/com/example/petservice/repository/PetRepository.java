package com.example.petservice.repository;

import com.example.petservice.model.Pet;  // ✅ Используем правильный класс Pet
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {  // ✅ Используем Pet из model
    List<Pet> findByType(String type);
}
