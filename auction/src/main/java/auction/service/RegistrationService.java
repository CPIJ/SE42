package auction.service;

import java.util.*;
import auction.domain.User;
import auction.repository.CollectionUserRepository;
import auction.repository.UserRepository;

public class RegistrationService {

    private UserRepository userRepository;

    public RegistrationService() {
        userRepository = new CollectionUserRepository();
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public User registerUser(String email) {
        if (!email.contains("@")) {
            return null;
        }
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        user = new User(email);
        userRepository.create(user);
        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
