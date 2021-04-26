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

    @Enumerated(EnumType.STRING)
    private Pet pet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_breed",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "breed_id", referencedColumnName = "id")})
    private Set<Breed> favoriteBreeds = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addFavoriteBreed(Breed breed) {
        favoriteBreeds.add(breed);
    }

    public void removeFavoriteBreeds() {
        favoriteBreeds.clear();
    }

    public Set<Breed> getFavoriteBreeds() {
        return new HashSet<>(favoriteBreeds);
    }
}
