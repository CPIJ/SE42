package auction.service;

import java.util.List;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import auction.domain.User;

public class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService();
    }

    @Test
    public void registerUser() {
        User user1 = registrationService.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = registrationService.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = registrationService.registerUser("xxx2@yyy2");
        assertSame(user2bis, user2);
        //geen @ in het adres
        assertNull(registrationService.registerUser("abc"));
    }

    @Test
    public void getUser() {
        User user1 = registrationService.registerUser("xxx5@yyy5");
        User userGet = registrationService.getUser("xxx5@yyy5");
        assertSame(userGet, user1);
        assertNull(registrationService.getUser("aaa4@bb5"));
        registrationService.registerUser("abc");
        assertNull(registrationService.getUser("abc"));
    }

    @Test
    public void getUsers() {
        List<User> users = registrationService.getUsers();
        assertEquals(0, users.size());

        User user1 = registrationService.registerUser("xxx8@yyy");
        users = registrationService.getUsers();
        assertEquals(1, users.size());
        assertSame(users.get(0), user1);


        User user2 = registrationService.registerUser("xxx9@yyy");
        users = registrationService.getUsers();
        assertEquals(2, users.size());

        registrationService.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = registrationService.getUsers();
        assertEquals(2, users.size());
    }
}
