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

    /**
     * Get the ID of the breed
     * @return the ID of the breed
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the breed
     * @return the name of the breed
     */
    public String getName() {
        return name;
    }

    /**
     * Get the pet that's of the breed
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }
}
