package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> currentSession = new ThreadLocal<Session>();
    private static ThreadLocal<Transaction> currentTransaction = new ThreadLocal<Transaction>();

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static Session openSession() throws Exception {
        if (sessionFactory == null)
            throw new Exception("Could not load hibernate.cfg.xml");

        try {
            Session session = currentSession.get();
            if (session == null) {
                session = sessionFactory.openSession();
                currentSession.set(session);
            }
            return session;
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public static void closeSession() throws Exception {
        try {
            Session session = currentSession.get();
            if (session != null) {
                session.close();
                currentSession.set(null);
            }
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public static void beginTransaction() throws Exception {
        try {
            Transaction transaction = currentTransaction.get();
            if (transaction == null) {
                Session session = openSession();
                transaction = session.beginTransaction();
                currentTransaction.set(transaction);
            }
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public static void commit() throws Exception {
        try {
            Transaction transaction = currentTransaction.get();
            if (transaction != null) {
                transaction.commit();
                currentTransaction.set(null);
            }
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

    public static void rollback() throws Exception {
        try {
            Transaction transaction = currentTransaction.get();
            if (transaction != null) {
                transaction.rollback();
                currentTransaction.set(null);
            }
        } catch (HibernateException e) {
            throw new Exception(e);
        }
    }

}
