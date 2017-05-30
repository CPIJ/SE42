package webAuction.auction.service.registration;

import webAuction.auction.domain.User;

import java.util.List;

public interface RegistrationService {
    User registerUser(String email);

    User getUser(String email);

    List<User> getUsers();
}
