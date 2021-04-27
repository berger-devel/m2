package me.stefanberger.m2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    private UserController userController;

    @Autowired
    public UserControllerTest(UserController userController) {
        this.userController = userController;
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setPet(Pet.Dog);
        userController.addUser(user);
    }

    @Test
    void testAddUserMissingPet() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userController.addUser(user));
        assertEquals("Missing argument: ", exception.getMessage());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setPet(Pet.Dog);
        userController.updateUser(1L, user);
    }

    @Test
    void testUpdateUserMissingPet() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userController.updateUser(1L, user));
        assertEquals("Missing argument: pet", exception.getMessage());
    }
}