package restful.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EM {

    static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("sqlserver");
    private static final EntityManager entityManager = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        synchronized (EntityManager.class) {
            if (entityManager.getTransaction().isActive() &&
                    entityManager.getTransaction().getRollbackOnly())
                entityManager.getTransaction().rollback();
            if (!entityManager.getTransaction().isActive())
                entityManager.getTransaction().begin();
        }
        return entityManager;
    }

    public static Boolean test(){
        return EM.getEntityManager().isOpen();
    }
    public static void main(String[] args) {
        System.out.println(EM.getEntityManager().isOpen());
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
