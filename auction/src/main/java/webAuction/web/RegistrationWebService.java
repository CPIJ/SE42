package webAuction.web;

import webAuction.auction.domain.User;
import webAuction.auction.service.registration.RegistrationService;

import javax.jws.WebService;

@WebService
public class RegistrationWebService {

    private RegistrationService registrationService;

    public RegistrationWebService() {
    }

    public RegistrationWebService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public User register(String email) {
        return registrationService.registerUser(email);
    }

    public User getUser(String email) {
        return registrationService.getUser(email);
    }
}
