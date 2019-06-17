package com.dkasiian.model;

import com.dkasiian.model.entities.User;
import com.dkasiian.model.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static UserService userService;
    private static final String LANGUAGE = "en_US";

    @BeforeAll
    static void init(){
        userService = new UserService();
    }

    @Test
    void getNonExistenceUserById(){
        User user = userService.getById(1000, LANGUAGE);
        assertNull(user);
    }

    @Test
    void getExistenceUserById(){
        User user = userService.getById(6, LANGUAGE);
        assertEquals(user.getId(), 6);
    }

    @Test
    void isUserExistTrue(){
        boolean result = userService.isUserExist("dmytro");
        assertTrue(result);
    }

    @Test
    void isUserExistFalse(){
        boolean result = userService.isUserExist("dmytro999");
        assertFalse(result);
    }

    @Test
    void checkPasswordTrue(){
        boolean result = userService.checkPassword("dmytro", "dmytro");
        assertTrue(result);
    }

    @Test
    void checkPasswordFalse(){
        boolean result = userService.checkPassword("dmytro", "dmytro999");
        assertFalse(result);
    }
}
