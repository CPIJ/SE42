package auction.service.seller;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

public interface SellerService {
    Item offerItem(User seller, Category cat, String description);

    boolean revokeItem(Item item);

    Item offerFurniture(User user, Category category, String type, String material);

    Item offerPainting(User user, Category category, String description, String name, String painter);
}
