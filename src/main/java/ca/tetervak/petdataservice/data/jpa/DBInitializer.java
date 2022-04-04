package ca.tetervak.petdataservice.data.jpa;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBInitializer {

    private final UserRepository userRepository;

    public DBInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){

        List<Pet> patriciaPets = new ArrayList<>(2);
        patriciaPets.add(new Pet("Charlie", "dog", 3));
        patriciaPets.add(new Pet("Fluffy", "cat", 2));
        User patricia = new User(
                "Patricia", "Johnson", 2, patriciaPets);
        userRepository.save(patricia);

        List<Pet> lisaPets = new ArrayList<>(1);
        lisaPets.add(new Pet("Oscar", "cat", 6));
        User lisa = new User(
                "Lisa", "Anderson", 1, lisaPets);
        userRepository.save(lisa);

        User kimberly = new User(
                "Kimberly", "Green", 0, new ArrayList<>(0));
        userRepository.save(kimberly);

        List<Pet> brendaPets = new ArrayList<>(4);
        brendaPets.add(new Pet("Max", "parrot", 10));
        brendaPets.add(new Pet("Riley", "dog", 5));
        brendaPets.add(new Pet("Sam", "rabbit", 2));
        brendaPets.add(new Pet("Milo", "hamster", 1));
        User brenda = new User("Brenda", "Clark", 4, brendaPets);
        userRepository.save(brenda);

        userRepository.flush();
    }
}
