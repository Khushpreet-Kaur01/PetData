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

        List<Pet> patriciaPets = new ArrayList<>(2);
        PetOwner patricia = new PetOwner("Patricia", "Johnson",patriciaPets);
        patriciaPets.add(new Pet("Charlie", "dog", 3, patricia));
        patriciaPets.add(new Pet("Fluffy", "cat", 2, patricia));
        petOwnerRepository.save(patricia);

        List<Pet> lisaPets = new ArrayList<>(1);
        PetOwner lisa = new PetOwner("Lisa", "Anderson", lisaPets);
        lisaPets.add(new Pet("Oscar", "cat", 6, lisa));
        petOwnerRepository.save(lisa);

        PetOwner kimberly = new PetOwner("Kimberly", "Green", new ArrayList<>(0));
        petOwnerRepository.save(kimberly);

        List<Pet> brendaPets = new ArrayList<>(4);
        PetOwner brenda = new PetOwner("Brenda", "Clark", brendaPets);
        brendaPets.add(new Pet("Max", "parrot", 10, brenda));
        brendaPets.add(new Pet("Riley", "dog", 5, brenda));
        brendaPets.add(new Pet("Sam", "rabbit", 2, brenda));
        brendaPets.add(new Pet("Milo", "hamster", 1, brenda));

        petOwnerRepository.save(brenda);

        petOwnerRepository.flush();
    }
}
