package auction.repository.user;

import auction.domain.User;

import java.util.List;

public class JPAUserRepository implements UserRepository {
    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void edit(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void remove(User user) {

    }
}
