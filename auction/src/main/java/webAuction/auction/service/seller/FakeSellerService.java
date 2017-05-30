package webAuction.auction.service.seller;

import webAuction.auction.domain.Category;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeSellerService implements SellerService {

    @Override
    public Item offerItem(User seller, Category cat, String description) {
        return new Item(seller, cat, description);
    }

    @Override
    public boolean revokeItem(Item item) {
        return item.getHighestBid() == null;
    }
}
