package webAuction.auction.service.seller;

import webAuction.auction.domain.Category;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;

public interface SellerService {
    Item offerItem(User seller, Category cat, String description);

    boolean revokeItem(Item item);
}
