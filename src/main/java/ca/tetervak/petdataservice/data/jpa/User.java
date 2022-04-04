package ca.tetervak.petdataservice.data.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet_owner")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @Column(name = "first_name")
    private String firstName = "";

    @Column(name = "last_name")
    private String lastName = "";

    @Column(name = "pet_count")
    private Integer petCount = 0;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public User() {
    }

    public User(
            String firstName,
            String lastName,
            Integer petCount,
            List<Pet> pets
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.petCount = petCount;
        this.pets = pets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPetCount() {
        return petCount;
    }

    public void setPetCount(Integer petCount) {
        this.petCount = petCount;
    }
}
