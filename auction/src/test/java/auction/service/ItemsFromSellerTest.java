package auction.service;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import auction.service.auction.AuctionService;
import auction.service.auction.DefaultAuctionService;
import auction.service.registration.DefaultRegistrationService;
import auction.service.registration.RegistrationService;
import auction.service.seller.DefaultSellerService;
import auction.service.seller.SellerService;
import auction.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ItemsFromSellerTest {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private AuctionService auctionService;
    private RegistrationService registrationService;
    private SellerService sellerService;

    @Before
    public void setUp() throws Exception {
        registrationService = new DefaultRegistrationService();
        auctionService = new DefaultAuctionService();
        sellerService = new DefaultSellerService();
        new DatabaseCleaner(entityManager).clean();
    }

    @Test
    //   @Ignore
    public void numberOfOfferedItems() {

        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        User user1 = registrationService.registerUser(email);
        assertEquals(0, user1.numberOfOfferdItems());

        Category cat = new Category("cat2");
        Item item1 = sellerService.offerItem(user1, cat, omsch1);

        // test number of items belonging to user1
        // assertEquals(0, user1.numberOfOfferdItems());
        // Deze moet falen, als er een item toegevoegd wil je deze kunnen opvragen.
        assertEquals(1, user1.numberOfOfferdItems());

        assertEquals(1, item1.getSeller().numberOfOfferdItems());

        User user2 = registrationService.getUser(email);
        assertEquals(1, user2.numberOfOfferdItems());
        Item item2 = sellerService.offerItem(user2, cat, omsch2);
        assertEquals(2, user2.numberOfOfferdItems());

        User user3 = registrationService.getUser(email);
        assertEquals(2, user3.numberOfOfferdItems());

        User userWithItem = item2.getSeller();
        assertEquals(2, userWithItem.numberOfOfferdItems());
//        assertEquals(3, userWithItem.numberOfOfferdItems());
        /*
         *  expected: which one of te above two assertions do you expect to be true?
         *  QUESTION:
         *    Explain the result in terms of entity manager and persistance context.
         */

//        assertNotSame(user3, userWithItem);
        // Dit zijn dezelfde ids. dus ze worden als hetzelfde gezien.
        assertEquals(user3, userWithItem);
    }

    @Test
    public void getItemsFromSeller() {
        String email = "ifu1@nl";
        String omsch1 = "omsch_ifu1";
        String omsch2 = "omsch_ifu2";

        Category cat = new Category("cat2");

        User user10 = registrationService.registerUser(email);
        Item item10 = sellerService.offerItem(user10, cat, omsch1);
        Iterator<Item> it = user10.getOfferedItems();
        // testing number of items of java object
        assertTrue(it.hasNext());

        // now testing number of items for same user fetched from db.
        User user11 = registrationService.getUser(email);
        Iterator<Item> it11 = user11.getOfferedItems();
        assertTrue(it11.hasNext());
        it11.next();
        assertFalse(it11.hasNext());

        // Explain difference in above two tests for te iterator of 'same' user

        User user20 = registrationService.getUser(email);
        Item item20 = sellerService.offerItem(user20, cat, omsch2);
        Iterator<Item> it20 = user20.getOfferedItems();
        assertTrue(it20.hasNext());
        it20.next();
        assertTrue(it20.hasNext());


        User user30 = item20.getSeller();
        Iterator<Item> it30 = user30.getOfferedItems();
        assertTrue(it30.hasNext());
        it30.next();
        assertTrue(it30.hasNext());
    }
}
