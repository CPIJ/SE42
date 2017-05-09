package auction.service;

import java.util.List;
import static org.junit.Assert.*;


import auction.service.registration.DefaultRegistrationService;
import auction.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import auction.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DefaultRegistrationServiceTest {

    private DefaultRegistrationService defaultRegistrationService;
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Before
    public void setUp() throws Exception {
        defaultRegistrationService = new DefaultRegistrationService();

        // Toegevoegd om te zorgen dat de database leeg is voordat iedere test wordt uitgevoerd.
        DatabaseCleaner cleaner = new DatabaseCleaner(entityManager);
        cleaner.clean();
    }

    //region original
    //@Test
    public void registerUser() {
        // User mag geregistreerd worden, er is een @ aanwezig.
        User user1 = defaultRegistrationService.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));

        // User mag geregistreerd worden, er is een @ aanwezig.
        User user2 = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));

        // Emailadres bestaat al, de user met dit emailadres wordt opgehaald.
        // Aangezien de entityManager nog niet herladen is zijn ook de geheugenadressen hetzelfde als
        // ze opgehaald worden uit de db.
        User user2bis = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertSame(user2bis, user2);

        // Een emailadres zonder
        assertNull(defaultRegistrationService.registerUser("abc"));
    }

    //@Test
    public void getUser() {
        User user1 = defaultRegistrationService.registerUser("xxx5@yyy5");
        User userGet = defaultRegistrationService.getUser("xxx5@yyy5");

        assertSame(userGet, user1);

        assertNull(defaultRegistrationService.getUser("aaa4@bb5"));

        defaultRegistrationService.registerUser("abc");
        assertNull(defaultRegistrationService.getUser("abc"));
    }

   // @Test
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
    //endregion

    @Test
    public void RegisterUser_ValidEmailAddress_ReturnsUser() {
        User user1 = defaultRegistrationService.registerUser("xxx1@yyy");
        assertTrue(user1.getEmail().equals("xxx1@yyy"));
    }

    @Test
    public void RegisterUser_SameEmailadressUsed_ReturnsSameObject() {
        User user2 = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertTrue(user2.getEmail().equals("xxx2@yyy2"));

        User user2bis = defaultRegistrationService.registerUser("xxx2@yyy2");
        assertSame(user2bis, user2);
    }

    @Test
    public void RegisterUser_InvalidEmailAddress_ReturnsNull() {
        assertNull(defaultRegistrationService.registerUser("abc"));
    }

    @Test
    public void GetUser_GivenSameEmail_ReturnsSameUser() {
        User user1 = defaultRegistrationService.registerUser("xxx5@yyy5");
        User userGet = defaultRegistrationService.getUser("xxx5@yyy5");

        assertSame(userGet, user1);
    }

    @Test
    public void GetUser_NonExistingEmail_ReturnsNull() {
        assertNull(defaultRegistrationService.getUser("aaa4@bb5"));
    }

    @Test
    public void GetUser_GivenInvalidEmail_ReturnsNull() {
        defaultRegistrationService.registerUser("abc");
        assertNull(defaultRegistrationService.getUser("abc"));
    }

    @Test
    public void GetUsers_NoUsersInDatabase_ReturnsEmptyList() {
        List<User> users = defaultRegistrationService.getUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void GetUsers_OneUserInDatabase_ReturnsListWithSameUser() {
        User user1 = defaultRegistrationService.registerUser("xxx8@yyy");
        List<User> users = defaultRegistrationService.getUsers();
        assertEquals(1, users.size());
        assertSame(users.get(0), user1);
    }

    @Test
    public void GetUsers_MultipleUsersInDatabase_ReturnsListWithUsers() {
        User user1 = defaultRegistrationService.registerUser("xxx8@yyy");
        List<User> users = defaultRegistrationService.getUsers();
        assertEquals(1, users.size());
        assertSame(users.get(0), user1);

        User user2 = defaultRegistrationService.registerUser("xxx9@yyy");
        users = defaultRegistrationService.getUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void GetUsers_RegisterWithInvalidEmail_ReturnsEmptyList() {
        defaultRegistrationService.registerUser("abc");
        List<User> users = defaultRegistrationService.getUsers();
        assertEquals(0, users.size());
    }
}
