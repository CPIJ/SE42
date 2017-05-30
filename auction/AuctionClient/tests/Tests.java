import org.junit.Before;
import org.junit.Test;
import webAuction.AuctionWebService;
import webAuction.AuctionWebServiceService;
import webAuction.Item;
import webAuction.auction.util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    private AuctionWebService auctionWebService = new AuctionWebServiceService().getPort(AuctionWebService.class);

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        new DatabaseCleaner(entityManager).clean();
    }

    @Test
    public void FindItem_NoItemsInDb_ListHasZeroItems() {
        List<Item> items = auctionWebService.findItemsByDescription("description");
        assertEquals(0, items.size());
    }
}