package auction.service;

import static org.junit.Assert.*;

import auction.service.auction.DefaultAuctionService;
import auction.service.registration.DefaultRegistrationService;
import auction.service.seller.DefaultSellerService;
import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;



import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

public class DefaultSellerServiceTest {

    private DefaultAuctionService defaultAuctionService;
    private DefaultRegistrationService defaultRegistrationService;
    private DefaultSellerService defaultSellerService;

    @Before
    public void setUp() throws Exception {
        defaultRegistrationService = new DefaultRegistrationService();
        defaultAuctionService = new DefaultAuctionService();
        defaultSellerService = new DefaultSellerService();
    }

    /**
     * Test of offerItem method, of class DefaultSellerService.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = defaultRegistrationService.registerUser("xx@nl");
        Category cat = new Category("cat1");
        Item item1 = defaultSellerService.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class DefaultSellerService.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = defaultRegistrationService.registerUser("sel@nl");
        User buyer = defaultRegistrationService.registerUser("buy@nl");
        Category cat = new Category("cat1");
        
            // revoke before bidding
        Item item1 = defaultSellerService.offerItem(seller, cat, omsch);
        boolean res = defaultSellerService.revokeItem(item1);
        assertTrue(res);
        int count = defaultAuctionService.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = defaultSellerService.offerItem(seller, cat, omsch2);
        defaultAuctionService.newBid(item2, buyer, new Money(100, "Euro"));
        boolean res2 = defaultSellerService.revokeItem(item2);
        assertFalse(res2);
        int count2 = defaultAuctionService.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
        
        
        
        
    }

}
