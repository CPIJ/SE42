package auction.service;

import auction.domain.Bid;
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
import nl.fontys.util.Money;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class BidTest {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    private final AuctionService auctionService = new DefaultAuctionService();
    private final SellerService sellerService = new DefaultSellerService();
    private final RegistrationService registrationService = new DefaultRegistrationService();

    @Before
    public void clear() throws SQLException {
        new DatabaseCleaner(entityManager).clean();
    }

    @Test
    public void NoBidGiven_SetsDefaultBid() {
        User buyer = registrationService.registerUser("BUY@hotmail.com");
        User seller = registrationService.registerUser("SELL@hotmail.com");

        Category category = new Category("test");

        Item item = sellerService.offerItem(seller, category, "test");



//        Item item = new Item(user, category, "test");
//
//        auctionService.newBid(item, user, new Money(0, Money.EURO));
//
//        assertEquals(Bid.DEFAULT, item.getHighest());
    }

}
