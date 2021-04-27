package me.stefanberger.m2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints to maintain assignments of breeds to users
 */
@RestController
@RequestMapping("/breed")
public class BreedController {
    private BreedRepository breedRepository;

    @Autowired
    public BreedController(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    /**
     * Retrieve all breeds for a given pet
     * @param pet the pet to retrieve the breeds for
     * @return an {@code Iterable<Breed>} for the breeds of this pet
     */
    @GetMapping("/{pet}")
    public Iterable<Breed> getAllByPet(@PathVariable Pet pet) {
        return breedRepository.findByPet(pet);
    }
}
