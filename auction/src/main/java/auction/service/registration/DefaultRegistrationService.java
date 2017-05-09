package auction.service.registration;

import auction.domain.User;
import auction.repository.user.JPAUserRepository;
import auction.repository.user.UserRepository;

import java.util.List;

public class DefaultRegistrationService implements RegistrationService {

    private UserRepository userRepository;

    public DefaultRegistrationService() {
        userRepository = new JPAUserRepository();
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    @Override
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
    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
