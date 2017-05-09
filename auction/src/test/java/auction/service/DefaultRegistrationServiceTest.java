package auction.service;

import java.util.List;
import static org.junit.Assert.*;


import auction.service.registration.DefaultRegistrationService;
import org.junit.Before;
import org.junit.Test;

import auction.domain.User;

public class DefaultRegistrationServiceTest {

    private DefaultRegistrationService defaultRegistrationService;

    @Before
    public void setUp() throws Exception {
        defaultRegistrationService = new DefaultRegistrationService();
    }

    @Test
    public void registerUser() {
        User user1 = defaultRegistrationService.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
        User user2 = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));
        User user2bis = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertSame(user2bis, user2);
        //geen @ in het adres
        assertNull(defaultRegistrationService.registerUser("abc"));
    }

    @Test
    public void getUser() {
        User user1 = defaultRegistrationService.registerUser("xxx5@yyy5");
        User userGet = defaultRegistrationService.getUser("xxx5@yyy5");
        assertSame(userGet, user1);
        assertNull(defaultRegistrationService.getUser("aaa4@bb5"));
        defaultRegistrationService.registerUser("abc");
        assertNull(defaultRegistrationService.getUser("abc"));
    }

    @Test
    public void getUsers() {
        List<User> users = defaultRegistrationService.getUsers();
        assertEquals(0, users.size());

        User user1 = defaultRegistrationService.registerUser("xxx8@yyy");
        users = defaultRegistrationService.getUsers();
        assertEquals(1, users.size());
        assertSame(users.get(0), user1);


        User user2 = defaultRegistrationService.registerUser("xxx9@yyy");
        users = defaultRegistrationService.getUsers();
        assertEquals(2, users.size());

        defaultRegistrationService.registerUser("abc");
        //geen nieuwe user toegevoegd, dus gedrag hetzelfde als hiervoor
        users = defaultRegistrationService.getUsers();
        assertEquals(2, users.size());
    }
}
