/*
 * @(#)PersistenceUtil.java    1.0 05/03/2011
 *
 * Copyright 2011 Desoft S.A Sancti-Spíritus.
 * Reservados todos los derechos.
 */

package util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * <code>PersistenceUtil</code> ...
 *
 * @version    1.0 05/03/2011
 * @author     Oliber M Bdez
 */
public class PersistenceUtil {

    private static final EntityManagerFactory entityManagerFactory;
    private static final ThreadLocal threadEntityManager =
                                                    new ThreadLocal();
    private static final ThreadLocal threadEntityTransaction =
                                                    new ThreadLocal();


    static {
        try {
            entityManagerFactory =
                    Persistence.createEntityManagerFactory("gerniPU");
        } catch (Throwable ex) {
            ex.printStackTrace(System.out);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        EntityManager em = (EntityManager) threadEntityManager.get();
        // Abre un nuevo EntityManager, si el hilo local
        // no tiene ninguno.
        try {
            if (em == null) {
                em = entityManagerFactory.createEntityManager();
                threadEntityManager.set(em);
            }
        } catch (PersistenceException ex) {
            //throw new InfrastructureException(ex);
        }
        return em;
    }

    public static void closeSession() {
        try {
            EntityManager em = (EntityManager) threadEntityManager.get();
            threadEntityManager.set(null);
            if (em != null && em.isOpen())
                em.close();
        } catch (PersistenceException ex) {
            //throw new InfrastructureException(ex);
        }
    }

    public static void beginTransaction() {
        EntityTransaction tx =
                (EntityTransaction) threadEntityTransaction.get();
        try {
            if (tx == null) {
                tx = getEntityManager().getTransaction();
                tx.begin();
                threadEntityTransaction.set(tx);
            }
        } catch (PersistenceException ex) {
            //throw new InfrastructureException(ex);
        }
    }

    public static void commitTransaction() {
        EntityTransaction tx =
                (EntityTransaction) threadEntityTransaction.get();
        try {
        if (tx != null && tx.isActive())
            tx.commit();
            threadEntityTransaction.set(null);
        } catch (PersistenceException ex) {
            rollbackTransaction();
            //throw new InfrastructureException(ex);
        }
    }

    public static void rollbackTransaction() {
        EntityTransaction tx =
                    (EntityTransaction) threadEntityTransaction.get();
        try {
            threadEntityTransaction.set(null);
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } catch (PersistenceException ex) {
            //throw new InfrastructureException(ex);
        } finally {
            closeSession();
        }
    }

}
