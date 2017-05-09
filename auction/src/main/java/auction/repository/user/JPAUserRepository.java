package auction.repository.user;

import auction.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class JPAUserRepository implements UserRepository {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(User user) {
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    @Override
    public void edit(User user) {

    }

    @Override
    public List<User> findAll() {
        entityManager.getTransaction().begin();

        List<User> users;

        Query query = entityManager.createQuery("SELECT u FROM User u");

        users = query.getResultList();

        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User findByEmail(String email) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void remove(User user) {

    }
}
