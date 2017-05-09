package auction.service.seller;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

public interface SellerService {
    Item offerItem(User seller, Category cat, String description);

    boolean revokeItem(Item item);
}
