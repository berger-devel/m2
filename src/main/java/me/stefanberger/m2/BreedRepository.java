package me.stefanberger.m2;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Repository for breed records
 */
public interface BreedRepository extends CrudRepository<Breed, Long> {
    /**
     * Retrieve all breeds of a pet
     * @param pet the pet to retrieve breeds for
     * @return the breeds of the given pet
     */
    Collection<Breed> findByPet(Pet pet);
}
