package webAuction;

import webAuction.auction.service.auction.FakeAuctionService;
import webAuction.auction.service.registration.FakeRegistrationService;
import webAuction.auction.service.seller.FakeSellerService;
import webAuction.web.AuctionWebService;
import webAuction.web.RegistrationWebService;

import javax.xml.ws.Endpoint;

public class RunWebService {
    private static final String baseUrl = "http://localhost:5000/WebAuction/";

    public static void main(String[] args) {
         Endpoint.publish(baseUrl + "Registration", new RegistrationWebService(new FakeRegistrationService()));
         Endpoint.publish(baseUrl + "Auction", new AuctionWebService(new FakeAuctionService(), new FakeSellerService()));
    }
}
