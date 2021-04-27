package me.stefanberger.m2;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Repository for Breed records
 */
public interface BreedRepository extends CrudRepository<Breed, Long> {
    Collection<Breed> findByPet(Pet pet);
}
