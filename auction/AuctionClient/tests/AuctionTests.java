import org.junit.Test;
import webAuction.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuctionTests {

    private AuctionWebService auctionWebService = new AuctionWebServiceService().getPort(AuctionWebService.class);

    @Test
    public void FindItem_NoItemsInDb_ListHasZeroItems() {
        List<Item> items = auctionWebService.findItemsByDescription("");
        assertEquals(0, items.size());
    }

    @Test
    public void FindItem_ItemInDb_ReturnsListContainingCorrectItem() {
        User seller = new User();
        seller.setEmail("fake@fake.nl");

        Category category = new Category();
        category.setDescription("Test");

        auctionWebService.offerItem(seller, category, "item1");

        List<Item> items = auctionWebService.findItemsByDescription("item1");

        assertEquals("item1", items.get(0).getDescription());
    }

    @Test
    public void NewBid() {
        Item item = new Item();
        item.setDescription("Mooi item");

        User user = new User();
        user.setEmail("fake@fakemail.nl");

        Money money = new Money();

        Bid bid = auctionWebService.newBid(item, user, money);

        assertEquals("fake@fakemail.nl",bid.getBuyer().getEmail());
    }

    @Test
    public void RevokeItem() {
        User user = new User();
        user.setEmail("fake@fake.nl");

        Category category = new Category();
        category.setDescription("fakeCategory");

        String description = "fakeItem";

        Item item = auctionWebService.offerItem(user, category, description);

        assertTrue(auctionWebService.revokeItem(item));
    }
}