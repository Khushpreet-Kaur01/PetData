package ca.tetervak.petdataservice.data.jpa;

import javax.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    private String name = "";

    @Column(name = "pet_kind")
    private String petKind = "";

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public Pet(String name, String petKind, Integer age, User owner) {
        this.name = name;
        this.petKind = petKind;
        this.age = age;
        this.owner = owner;
    }

    public Pet() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
