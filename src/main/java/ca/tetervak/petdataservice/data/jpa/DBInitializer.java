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
        User patricia = new User("Patricia", "Johnson",patriciaPets);
        patriciaPets.add(new Pet("Charlie", "dog", 3, patricia));
        patriciaPets.add(new Pet("Fluffy", "cat", 2, patricia));
        userRepository.save(patricia);

        List<Pet> lisaPets = new ArrayList<>(1);
        User lisa = new User("Lisa", "Anderson", lisaPets);
        lisaPets.add(new Pet("Oscar", "cat", 6, lisa));
        userRepository.save(lisa);

        User kimberly = new User("Kimberly", "Green", new ArrayList<>(0));
        userRepository.save(kimberly);

        List<Pet> brendaPets = new ArrayList<>(4);
        User brenda = new User("Brenda", "Clark", brendaPets);
        brendaPets.add(new Pet("Max", "parrot", 10, brenda));
        brendaPets.add(new Pet("Riley", "dog", 5, brenda));
        brendaPets.add(new Pet("Sam", "rabbit", 2, brenda));
        brendaPets.add(new Pet("Milo", "hamster", 1, brenda));

        userRepository.save(brenda);

        userRepository.flush();
    }
}
