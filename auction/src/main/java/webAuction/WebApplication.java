package webAuction;

import webAuction.web.AuctionWebService;
import webAuction.web.RegistrationWebService;

import javax.xml.ws.Endpoint;

public class WebApplication {
    private static final String baseUrl = "http://localhost:5000/WebAuction/";

    public static void main(String[] args) {
         Endpoint.publish(baseUrl + "Registration", new RegistrationWebService());
         Endpoint.publish(baseUrl + "Auction", new AuctionWebService());
    }
}
