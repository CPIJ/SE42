package auction.service;

import static org.junit.Assert.*;

import auction.repository.item.JPAItemRepository;
import auction.service.auction.DefaultAuctionService;
import auction.service.registration.DefaultRegistrationService;
import auction.service.registration.RegistrationService;
import auction.service.seller.DefaultSellerService;
import auction.util.DatabaseCleaner;
import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class DefaultAuctionServiceTest {

    private DefaultAuctionService defaultAuctionService;
    private RegistrationService defaultRegistrationService;
    private DefaultSellerService defaultSellerService;
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Before
    public void setUp() throws Exception {
        defaultRegistrationService = new DefaultRegistrationService();
        defaultAuctionService = new DefaultAuctionService();
        defaultSellerService = new DefaultSellerService();
        DatabaseCleaner databaseCleaner = new DatabaseCleaner(entityManager);
        databaseCleaner.clean();
    }

    @Test
    public void getItem() {
        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = defaultRegistrationService.registerUser(email);
        Category cat = new Category("cat2");
        Item item1 = defaultSellerService.offerItem(seller1, cat, omsch);
        Item item2 = defaultAuctionService.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = defaultRegistrationService.registerUser(email3);
        User seller4 = defaultRegistrationService.registerUser(email4);
        Category cat = new Category("cat3");
        Item item1 = defaultSellerService.offerItem(seller3, cat, omsch);
        Item item2 = defaultSellerService.offerItem(seller4, cat, omsch);

        ArrayList<Item> res = (ArrayList<Item>) defaultAuctionService.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = (ArrayList<Item>) defaultAuctionService.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = defaultRegistrationService.registerUser(email);
        User buyer = defaultRegistrationService.registerUser(emailb);
        User buyer2 = defaultRegistrationService.registerUser(emailb2);
        // eerste bod
        Category cat = new Category("cat9");
        Item item1 = defaultSellerService.offerItem(seller, cat, omsch);
        Bid new1 = defaultAuctionService.newBid(item1, buyer, new Money(10, "eur"));
        assertEquals(emailb, new1.getBuyer().getEmail());

        // lager bod
        Bid new2 = defaultAuctionService.newBid(item1, buyer2, new Money(9, "eur"));
        assertNull(new2);

        // hoger bod
        Bid new3 = defaultAuctionService.newBid(item1, buyer2, new Money(11, "eur"));
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}
