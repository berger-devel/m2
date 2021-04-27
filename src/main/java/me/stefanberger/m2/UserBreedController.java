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
 * REST endpoint to maintain assignments of favorite breeds to users
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

    @PutMapping
    public Iterable<User> assignBreedToUser(@RequestBody List<List<Long>> mappings) {
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
