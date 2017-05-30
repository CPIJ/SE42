package webAuction.auction.service.auction;

import webAuction.auction.domain.Bid;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;
import webAuction.nl.fontys.util.Money;

import java.util.List;

public interface AuctionService {
    Item getItem(Long id);

    List<Item> findItemByDescription(String description);

    Bid newBid(Item item, User buyer, Money amount);
}
