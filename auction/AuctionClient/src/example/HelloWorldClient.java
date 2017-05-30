package example;

import webAuction.AuctionWebService;
import webAuction.AuctionWebServiceService;

public class HelloWorldClient {
  public static void main(String[] argv) {
    AuctionWebService service = new AuctionWebServiceService().getPort(AuctionWebService.class);
  }
}

