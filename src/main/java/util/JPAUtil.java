package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // This string must match the <persistence-unit name="..."> in persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "pack_track_unity";

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}