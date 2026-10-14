package com.careertracker.dao;
import com.careertracker.entity.Offer;
import com.careertracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class OfferDAO {

    public void save(Offer obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(obj);
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public List<Offer> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Offer", Offer.class).list();
        }
    }

    public Offer getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Offer.class, id);
        }
    }

    public void update(Offer obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(obj);
            tx.commit();
        } 
        catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } 
        finally {
            session.close();
        }
    }

    public boolean delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Offer obj = session.get(Offer.class, id);
            if (obj == null) {
                tx.rollback();
                return false;
            }
            session.remove(obj);
            tx.commit();
            return true;
        } 
        catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } 
        finally {
            session.close();
        }
    }

    public long count() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select count(o.offerId) from Offer o", Long.class)
                          .uniqueResult();
        }
    }

    public Offer getHighestPackageOffer() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Offer o order by o.packageLpa desc", Offer.class)
                          .setMaxResults(1)
                          .uniqueResult();
        }
    }
}
