package util;

import domain.Circle;
import domain.Shape;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
            Shape.class,
            Circle.class
    };

    public static void clean(EntityManager em) throws SQLException {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType, em);
        }
        em.getTransaction().commit();
        em.close();
    }

    private static void deleteEntities(Class<?> entityType, EntityManager em) {
        em.createQuery("delete from " + getEntityName(entityType, em)).executeUpdate();
    }

    private static String getEntityName(Class<?> clazz, EntityManager em) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
