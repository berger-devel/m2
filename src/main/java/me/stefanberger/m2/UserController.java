package me.stefanberger.m2;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * REST endpoints to maintain users
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieve all users from the database
     * @return an {@code Iterable<User>} for all present users
     */
    @GetMapping
    public Iterable<User> getAll(@RequestParam(required = false) String name) {
        if(name == null){
            return userRepository.findAll();
        }
        return userRepository.findByName(name);
    }

    /**
     * Retrieve a single user
     * @param id the ID of the user to retrieve
     * @return the {@code User} corresponding to the given ID
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Create a new user in the database
     * @param user The user to create. The `pet` property is mandatory.
     * @return the newly created {@code User}
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        Preconditions.checkArgument(user.getPet() != null, "Missing argument: pet");
        return userRepository.save(user);
    }

    /**
     * Create a new or update an existing {@code User} with the given ID
     * @param id the ID of the user to update
     * @param user the {@code User} to update
     * @return the newly created or updated {@code User}
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        Preconditions.checkArgument(user.getPet() != null, "Missing argument: pet");
        user.setId(id);
        return userRepository.save(user);
    }

    /**
     * Delete a user
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
