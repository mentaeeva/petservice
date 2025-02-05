package com.example.petservice.controller;

import com.example.petservice.model.Pet;
import com.example.petservice.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Получение всех питомцев
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Получение питомцев по типу
    @GetMapping("/type/{type}")
    public List<Pet> getPetsByType(@PathVariable String type) {
        return petRepository.findByType(type);
    }

    // Добавление нового питомца
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    // Обновление информации о питомце
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setName(petDetails.getName());
            pet.setType(petDetails.getType());
            pet.setAge(petDetails.getAge());
            return ResponseEntity.ok(petRepository.save(pet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление питомца
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
