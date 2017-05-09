package auction.service.auction;

import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import nl.fontys.util.Money;

import java.util.List;

public interface AuctionService {
    Item getItem(Long id);

    List<Item> findItemByDescription(String description);

    Bid newBid(Item item, User buyer, Money amount);
}
