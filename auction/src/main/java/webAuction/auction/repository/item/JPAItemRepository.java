package webAuction.auction.repository.item;

import webAuction.auction.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class JPAItemRepository implements ItemRepository {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("auctionPU");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(Item item) {
        entityManager.getTransaction().begin();

        entityManager.persist(item);

        entityManager.getTransaction().commit();
    }

    @Override
    public void edit(Item item) {

    }

    @Override
    public Item find(Long id) {
        entityManager.getTransaction().begin();

        Item item = entityManager.find(Item.class, id);

        entityManager.getTransaction().commit();

        return item;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByDescription(String description) {
        List<Item> items = new ArrayList<>();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT i FROM Item i WHERE i.description = :description")
                        .setParameter("description", description);

        for (Object object : query.getResultList()) {
            items.add((Item) object);
        }

        entityManager.getTransaction().commit();

        return items;
    }

    @Override
    public void remove(Item item) {
        if (item != null) {
            entityManager.getTransaction().begin();

            entityManager.remove(item);

            entityManager.getTransaction().commit();
        }
    }
}
