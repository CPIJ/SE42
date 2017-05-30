package webAuction.auction.service.auction;

import webAuction.auction.domain.Bid;
import webAuction.auction.domain.Category;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;
import webAuction.nl.fontys.util.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FakeAuctionService implements AuctionService{

    private User user = new User("fake@email.nl");
    private Category  category = new Category("fake");
    private List<Item> items = new ArrayList<>();


    @Override
    public Item getItem(Long id) {
        user.setId(id);

        Item item = new Item(user, category, "fake");

        items.add(item);

        return item;
    }

    @Override
    public List<Item> findItemByDescription(String description) {
        return new ArrayList<Item>() {{
            if (!Objects.equals(description, "")) {
                add(new Item(user, category, description));
            }
        }};
    }

    @Override
    public Bid newBid(Item item, User buyer, Money amount) {
        return new Bid(buyer, amount);
    }
}
