package me.stefanberger.m2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user of the application
 */
@Entity
@Table(name = "user")
public class User implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Pet pet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_breed",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "breed_id", referencedColumnName = "id")})
    private Set<Breed> favoriteBreeds = new HashSet<>();

    /**
     * Set the ID for the user
     * @param id the ID for the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the user
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Get the pet of the user
     * @return the pet of the user
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Set the pet of the user
     * @param pet the pet for the user
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Add a favorite breed of the user
     * @param breed the breed to add as a favorite
     */
    public void addFavoriteBreed(Breed breed) {
        favoriteBreeds.add(breed);
    }

    /**
     * Empty the set of favorite breeds of the user
     */
    public void clearFavoriteBreeds() {
        favoriteBreeds.clear();
    }

    /**
     * Get the favorite breeds of the user
     * @return the favorite breeds of the user
     */
    public Set<Breed> getFavoriteBreeds() {
        return new HashSet<>(favoriteBreeds);
    }
}
