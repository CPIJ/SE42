package auction.service;

import static org.junit.Assert.*;

import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;

public class AuctionServiceTest {

    private AuctionService auctionService;
    private RegistrationService registrationService;
    private SellerService sellerService;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService();
        auctionService = new AuctionService();
        sellerService = new SellerService();
    }

    @Test
    public void getItem() {

        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = registrationService.registerUser(email);
        Category cat = new Category("cat2");
        Item item1 = sellerService.offerItem(seller1, cat, omsch);
        Item item2 = auctionService.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = registrationService.registerUser(email3);
        User seller4 = registrationService.registerUser(email4);
        Category cat = new Category("cat3");
        Item item1 = sellerService.offerItem(seller3, cat, omsch);
        Item item2 = sellerService.offerItem(seller4, cat, omsch);

        ArrayList<Item> res = (ArrayList<Item>) auctionService.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = (ArrayList<Item>) auctionService.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registrationService.registerUser(email);
        User buyer = registrationService.registerUser(emailb);
        User buyer2 = registrationService.registerUser(emailb2);
        // eerste bod
        Category cat = new Category("cat9");
        Item item1 = sellerService.offerItem(seller, cat, omsch);
        Bid new1 = auctionService.newBid(item1, buyer, new Money(10, "eur"));
        assertEquals(emailb, new1.getBuyer().getEmail());

        // lager bod
        Bid new2 = auctionService.newBid(item1, buyer2, new Money(9, "eur"));
        assertNull(new2);

        // hoger bod
        Bid new3 = auctionService.newBid(item1, buyer2, new Money(11, "eur"));
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}
