package ca.tetervak.petdataservice.data.jpa;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitializer {

    private final PetOwnerRepository petOwnerRepository;

    public DataInitializer(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @PostConstruct
    public void init(){

        PetOwner patricia = new PetOwner("Patricia", "Johnson");
        patricia.addPet(new Pet("Charlie", "dog", 3));
        patricia.addPet(new Pet("Fluffy", "cat", 2));
        petOwnerRepository.save(patricia);

        PetOwner lisa = new PetOwner("Lisa", "Anderson");
        lisa.addPet(new Pet("Oscar", "cat", 6));
        petOwnerRepository.save(lisa);

        PetOwner kimberly = new PetOwner("Kimberly", "Green");
        petOwnerRepository.save(kimberly);

        PetOwner brenda = new PetOwner("Brenda", "Clark");
        brenda.addPet(new Pet("Max", "parrot", 10));
        brenda.addPet(new Pet("Riley", "dog", 5));
        brenda.addPet(new Pet("Sam", "rabbit", 2));
        brenda.addPet(new Pet("Milo", "hamster", 1));

        petOwnerRepository.save(brenda);

        petOwnerRepository.flush();
    }
}
