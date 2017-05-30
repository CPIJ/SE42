package auction.service.auction;

import auction.repository.item.ItemRepository;
import auction.repository.item.JPAItemRepository;
import auction.repository.user.UserRepository;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;

import java.util.List;

public class DefaultAuctionService implements AuctionService {

    private ItemRepository itemRepository = new JPAItemRepository();

    /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     *         geretourneerd
     */
    @Override
    public Item getItem(Long id) {
        if (id == null) return null;
        return itemRepository.find(id);
    }

  
   /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    @Override
    public List<Item> findItemByDescription(String description) {
        return itemRepository.findByDescription(description);
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     *         amount niet hoger was dan het laatste bod, dan null
     */
    @Override
    public Bid newBid(Item item, User buyer, Money amount) {
        Bid bid = item.newBid(buyer, amount);

        itemRepository.edit(item);

        return bid;
    }
}
