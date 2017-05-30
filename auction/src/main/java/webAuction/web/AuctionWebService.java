package webAuction.web;

import webAuction.auction.domain.Bid;
import webAuction.auction.domain.Category;
import webAuction.auction.domain.Item;
import webAuction.auction.domain.User;
import webAuction.auction.service.auction.AuctionService;
import webAuction.auction.service.auction.DefaultAuctionService;
import webAuction.auction.service.seller.DefaultSellerService;
import webAuction.auction.service.seller.SellerService;
import webAuction.nl.fontys.util.Money;

import javax.jws.WebService;
import java.util.List;

@WebService
public class AuctionWebService {

    private AuctionService auctionService = new DefaultAuctionService();
    private SellerService sellerService = new DefaultSellerService();

    public Item getItem(Long id) {
        return auctionService.getItem(id);
    }

    public List<Item> findItemsByDescription(String description) {
        return auctionService.findItemByDescription(description);
    }

    public Bid newBid(Item item, User buyer, Money amount) {
        return auctionService.newBid(item, buyer, amount);
    }

    public Item offerItem(User seller, Category category, String description) {
        return sellerService.offerItem(seller, category, description);
    }

    public boolean revokeItem(Item item) {
        return sellerService.revokeItem(item);
    }
}
