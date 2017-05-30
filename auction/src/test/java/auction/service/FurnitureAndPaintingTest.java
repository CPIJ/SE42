package auction.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import auction.service.auction.AuctionService;
import auction.service.auction.DefaultAuctionService;
import auction.service.registration.DefaultRegistrationService;
import auction.service.registration.RegistrationService;
import auction.service.seller.DefaultSellerService;
import auction.service.seller.SellerService;
import auction.domain.*;
import java.util.Iterator;

import auction.util.DatabaseCleaner;
import nl.fontys.util.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FurnitureAndPaintingTest {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private AuctionService auctionService;
    private RegistrationService registrationService;
    private SellerService sellerService;

    public FurnitureAndPaintingTest() {
    }

    @Before
    public void setUp() throws Exception {
        registrationService = new DefaultRegistrationService();
        auctionService = new DefaultAuctionService();
        sellerService = new DefaultSellerService();
        new DatabaseCleaner(entityManager).clean();
    }

    @Test
    public void newFurniture() {
        String omsch = "omsch1";
        String iemand1 = "iemand1@def";
        User u1 = registrationService.registerUser(iemand1);
        User u2 = registrationService.registerUser("iemand2@def");
        Category cat = new Category("cat2");

        Item furniture1 = sellerService.offerFurniture(u1, cat, "broodkast", "ijzer");
        assertEquals("seller of item correct", furniture1.getSeller(), u1);

        User foundUser = registrationService.getUser(iemand1);
        Iterator<Item> it = foundUser.getOfferedItems();
        Item firstItem = it.next();
        //        int xxx = 22;
        assertEquals("item added in offeredItems", furniture1, firstItem);
        Item item2 = sellerService.offerPainting(u1, cat, omsch, "Nachtwacht", "Rembrandt");
        it = registrationService.getUser(iemand1).getOfferedItems();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());

        //de volgende code verwijderen als Item abstract is
        Item item3 = sellerService.offerItem(u1, new Category("boek"), "The science of Discworld");
        it = registrationService.getUser(iemand1).getOfferedItems();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());

        assertSame(Bid.DEFAULT, furniture1.getHighestBid());
        Bid bid = auctionService.newBid(furniture1, u2, new Money(150000, Money.EURO));
        assertNotNull(furniture1.getHighestBid());

        Item foundFurniture = auctionService.getItem(furniture1.getId());
        assertEquals(foundFurniture.getHighestBid().getAmount(), bid.getAmount());
        assertTrue(foundFurniture.getClass() == Furniture.class);
    }
}
