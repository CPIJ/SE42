package example;

import webAuction.AuctionWebService;
import webAuction.AuctionWebServiceService;
import webAuction.RegistrationWebService;
import webAuction.RegistrationWebServiceService;

public class HelloWorldClient {
  public static void main(String[] argv) {
    RegistrationWebService service = new RegistrationWebServiceService().getPort(RegistrationWebService.class);
  }
}
