package me.stefanberger.m2;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for user records
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findByName(String name);
}
