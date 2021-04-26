package me.stefanberger.m2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/breed")
public class BreedController {
    private BreedRepository breedRepository;

    @Autowired
    public BreedController(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    @GetMapping
    public Iterable<Breed> getAll() {
        return breedRepository.findAll();
    }
}
