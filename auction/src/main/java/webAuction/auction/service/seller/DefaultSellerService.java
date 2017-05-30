package webAuction.auction.service.seller;

import webAuction.auction.domain.Category;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;
import webAuction.auction.repository.item.ItemRepository;
import webAuction.auction.repository.item.JPAItemRepository;

public class DefaultSellerService implements SellerService {

    private ItemRepository itemRepository = new JPAItemRepository();

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    @Override
    public Item offerItem(User seller, Category cat, String description) {
        Item item = new Item(seller, cat, description);
        itemRepository.create(item);
        return item;
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    @Override
    public boolean revokeItem(Item item) {
        if (item.getHighestBid() == null) {
            itemRepository.remove(item);
            return true;
        } else {
            return false;
        }
    }
}
