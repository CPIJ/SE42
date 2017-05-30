package example;

import org.junit.Before;
import org.junit.Test;
import webAuction.RegistrationWebService;
import webAuction.RegistrationWebServiceService;
import webAuction.User;
import webAuction.auction.util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class Tests {

    private RegistrationWebService registrationService = new RegistrationWebServiceService().getPort(RegistrationWebService.class);

    @Before
    public void setUp() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        new DatabaseCleaner(entityManager).clean();
    }

    @Test
    public void GetUser_NonExistentEmail_UserIsNull() {
        User user = registrationService.getUser("email@adres.nl");
        assertNull(user);
    }
}