package ca.tetervak.petdataservice.data.jpa;

import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    private String name = "";

    @Column(name = "pet_kind")
    private String petKind = "";

    private Integer age;

    private String image = null;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PetOwner owner = null;

    @JsonGetter
    Integer getOwnerId(){
        return owner.getId();
    }

    public Pet(String name, String petKind, Integer age, String image) {
        this.name = name;
        this.petKind = petKind;
        this.age = age;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PetOwner getOwner() {
        return owner;
    }

    public void setOwner(PetOwner owner) {
        this.owner = owner;
    }
}
