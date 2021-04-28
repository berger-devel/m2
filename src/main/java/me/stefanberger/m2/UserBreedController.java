package me.stefanberger.m2;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * REST endpoints to maintain assignments of favorite breeds to users
 */
@RestController
@RequestMapping("/userBreed")
public class UserBreedController {
    private UserRepository userRepository;
    private BreedRepository breedRepository;

    @Autowired
    public UserBreedController(UserRepository userRepository, BreedRepository breedRepository) {
        this.userRepository = userRepository;
        this.breedRepository = breedRepository;
    }

    /**
     * Assign to the user IDs the breed IDs.
     * @param mappings User Id - Breed Id pairs. The Collection must consist of Lists with exactly 2 elements each,
     *                 specifically a user Id and a breed Id.
     * @return An {@code Iterable<User>} of the {@code User}s which were assigned breeds
     */
    @PutMapping
    public Iterable<User> assignBreedToUser(@RequestBody Collection<List<Long>> mappings) {
        Preconditions.checkArgument(mappings != null && mappings.size() != 0);
        mappings.forEach(assignment -> Preconditions.checkArgument(assignment.size() == 2, "Invalid User ID <=> Breed ID mapping: " + assignment));
        Map<Long, User> userIdToUser = loadIdToObjectMap(mappings.stream().map(mapping -> mapping.get(0)).collect(Collectors.toSet()), userRepository, User.class);
        Map<Long, Breed> breedIdToBreed = loadIdToObjectMap(mappings.stream().map(mapping -> mapping.get(1)).collect(Collectors.toSet()), breedRepository, Breed.class);
        userIdToUser.values().forEach(User::clearFavoriteBreeds);
        mappings.forEach(mapping -> {
            User user = userIdToUser.get(mapping.get(0));
            Breed breed = breedIdToBreed.get(mapping.get(1));

            Preconditions.checkArgument(user.getPet().equals(breed.getPet()), "Can't assign breed " + breed.getName() + " to " + user.getPet() + " person");

            user.addFavoriteBreed(breedIdToBreed.get(mapping.get(1)));
        });

        return userRepository.saveAll(userIdToUser.values());
    }

    /**
     * Load the domain objects and return a map from their IDs to the domain objects
     * @param ids the IDs of the records to load
     * @param repository the repository to load the records from
     * @param clazz the class of the domain object
     * @param <T> the type parameter for the domain object, specified by the clazz parameter
     * @return a map from domain object IDs to domain objects
     */
    private <T extends DomainObject> Map<Long, T> loadIdToObjectMap(Set<Long> ids, CrudRepository<T, Long> repository, Class<T> clazz) {
        Set<T> foundRecords = Sets.newHashSet(repository.findAllById(ids));

        if (foundRecords.size() == ids.size()) {
            return foundRecords.stream().collect(Collectors.toMap(DomainObject::getId, record -> record));
        } else {
            ids.removeAll(foundRecords.stream().map(DomainObject::getId).collect(Collectors.toList()));
            throw new IllegalArgumentException(clazz.getSimpleName() + " IDs " + ids + " not found");
        }
    }
}
