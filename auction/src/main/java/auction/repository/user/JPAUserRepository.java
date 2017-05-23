package auction.repository.user;

import auction.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        List<User> users;

        Query query = entityManager.createQuery("SELECT u FROM User u");

        users = query.getResultList();

        return users;
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Geen resultaten");
            return null;
        }
    }

    @Override
    public void remove(User user) {

    }
}
