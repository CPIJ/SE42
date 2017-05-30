package auction.service.seller;

import auction.domain.*;
import auction.repository.item.ItemRepository;
import auction.repository.item.JPAItemRepository;

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
        return createItem(new Item(seller, cat, description));
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    @Override
    public boolean revokeItem(Item item) {
        if (item.getHighestBid().getAmount().getCents() == 0) {
            itemRepository.remove(item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Item offerFurniture(User seller, Category category, String description, String material) {
        return createItem(new Furniture(seller, category, description, material));
    }

    @Override
    public Item offerPainting(User seller, Category category, String description, String name, String painter) {
        return createItem(new Painting(seller, category, description, name, painter));
    }

    private Item createItem(Item item) {
        itemRepository.create(item);
        return item;
    }
}
