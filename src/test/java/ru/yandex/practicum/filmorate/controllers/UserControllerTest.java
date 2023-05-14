package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;
    private User user;
    private User user2;

    @BeforeEach
    public void beforeEach() {
        user = new User(0, "email1@gmail.com", "Login1", "Name1", LocalDate.of(1994, 11, 02));
        user2 = new User(2, "email2@gmail.com", "Login2", "Name2", LocalDate.of(1994, 11, 02));

    }

    @Test
    public void addUser() {
        userController.addUser(user);
        assertEquals(userController.getAllUsers().get(0), user);
        assertEquals(userController.getAllUsers().size(), 1);
    }

    @Test
    public void updateUser() {
        userController.addUser(user);
        userController.addUser(user2);
        user2 = new User(2, "email2@gmail.com", "Login2", "Name2", LocalDate.of(1993, 11, 02));
        userController.updateUser(user2);
        assertEquals(userController.getAllUsers().get(1), user2);
    }
}