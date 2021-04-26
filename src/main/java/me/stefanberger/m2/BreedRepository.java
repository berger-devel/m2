package me.stefanberger.m2;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Breed records
 */
public interface BreedRepository extends CrudRepository<Breed, Long> {
}
