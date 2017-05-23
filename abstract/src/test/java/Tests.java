import domain.Circle;
import domain.Shape;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class Tests {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("abstractPU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Test
    public void test() {
        entityManager.getTransaction().begin();

        Circle circle = new Circle(50,50,100);

        entityManager.persist(circle);

        entityManager.getTransaction().commit();
    }

    @Test
    public void makeShape() {
        entityManager.getTransaction().begin();

        Circle circle = new Circle(50,50,100);

        Shape shape = new Shape(10,10);

        entityManager.persist(shape);

        entityManager.persist(circle);

        entityManager.getTransaction().commit();
    }


    @Test
    public void clean() {
        try {
            DatabaseCleaner.clean(entityManager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
