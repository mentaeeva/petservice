package com.example.assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Shelter {
    private List<Pet> pets;

    public Shelter() {
        this.pets = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }

    public List<Pet> searchPetsByType(String type) {
        return pets.stream().filter(pet -> pet.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
    }




    @Override
    public String toString() {
        return "Shelter{pets=" + pets + "}";
    }
}
