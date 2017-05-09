package auction.service;

import static org.junit.Assert.*;

import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;



import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

public class SellerServiceTest {

    private AuctionService auctionService;
    private RegistrationService registrationService;
    private SellerService sellerService;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService();
        auctionService = new AuctionService();
        sellerService = new SellerService();
    }

    /**
     * Test of offerItem method, of class SellerService.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationService.registerUser("xx@nl");
        Category cat = new Category("cat1");
        Item item1 = sellerService.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerService.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = registrationService.registerUser("sel@nl");
        User buyer = registrationService.registerUser("buy@nl");
        Category cat = new Category("cat1");
        
            // revoke before bidding
        Item item1 = sellerService.offerItem(seller, cat, omsch);
        boolean res = sellerService.revokeItem(item1);
        assertTrue(res);
        int count = auctionService.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = sellerService.offerItem(seller, cat, omsch2);
        auctionService.newBid(item2, buyer, new Money(100, "Euro"));
        boolean res2 = sellerService.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionService.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
        
        
        
        
    }

}
