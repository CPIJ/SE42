package auction.service.auction;

import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuctionService implements AuctionService {

   /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     *         geretourneerd
     */
    @Override
    public Item getItem(Long id) {
        // TODO
        return null;
    }

  
   /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    @Override
    public List<Item> findItemByDescription(String description) {
        // TODO
        return new ArrayList<Item>();
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
        // TODO 
        return null;
    }
}
