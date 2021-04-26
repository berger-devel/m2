package me.stefanberger.m2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserBreedControllerTest {
    private UserBreedController userBreedController;

    @Autowired
    UserBreedControllerTest(UserBreedController userBreedController) {
        this.userBreedController = userBreedController;
    }

    @Test
    void testAssignBreedToUser() {
        List<List<Long>> mappings = Arrays.asList(Arrays.asList(1L, 6L), Arrays.asList(1L, 7L));
        userBreedController.assignBreedToUser(mappings);
    }

    @Test
    void testAssignBreedToUserInvalidMapping() {
        List<List<Long>> mappings = Collections.singletonList(Arrays.asList(1L, 2L, 3L));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userBreedController.assignBreedToUser(mappings));
        assertEquals("Invalid User ID <=> Breed ID mapping: [1, 2, 3]", exception.getMessage());
    }

    @Test
    void testAssignBreedToUserInvalidUserId() {
        List<List<Long>> mappings = Collections.singletonList(Arrays.asList(2L, 1L));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userBreedController.assignBreedToUser(mappings));
        assertEquals("User IDs [2] not found", exception.getMessage());
    }

    @Test
    void testAssignBreedToUserInvalidBreedId() {
        List<List<Long>> mappings = Collections.singletonList(Arrays.asList(1L, 11L));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userBreedController.assignBreedToUser(mappings));
        assertEquals("Breed IDs [11] not found", exception.getMessage());
    }

    @Test
    void testAssignBreedToUserWrongPet() {
        List<List<Long>> mappings = Collections.singletonList(Arrays.asList(1L, 1L));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userBreedController.assignBreedToUser(mappings));
        assertEquals("Can't assign breed Persian to Dog person", exception.getMessage());
    }

    @Test
    void testRemoveBreedFromUser() {
        userBreedController.removeBreedsFromUser(1L);
    }

    @Test
    void testRemoveBreedFromUserInvalidUserId() {
        assertThrows(NoSuchElementException.class, () -> userBreedController.removeBreedsFromUser(2L));
    }
}
