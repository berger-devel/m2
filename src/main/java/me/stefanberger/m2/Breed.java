package me.stefanberger.m2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a breed of an animal (dog or cat)
 */
@Entity
@Table(name="breed")
public class Breed implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Pet pet;

    private String name;

    @ManyToMany(mappedBy = "favoriteBreeds")
    private Set<User> users = new HashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
