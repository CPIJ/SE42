package webAuction.auction.service.registration;

import webAuction.auction.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FakeRegistrationService implements RegistrationService {

    private List<User> users = new ArrayList<>();

    @Override
    public User registerUser(String email) {
        User user = new User(email);

        users.add(user);

        return user;
    }

    @Override
    public User getUser(String email) {
        try {
            return users.stream()
                    .filter(u -> Objects.equals(u.getEmail(), email))
                    .collect(Collectors.toList())
                    .get(0);
        }
        catch (Exception e) {
            // Returns null when no item is found.
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
