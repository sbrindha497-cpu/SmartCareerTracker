package com.careertracker.dao;
import com.careertracker.entity.Interview;
import com.careertracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class InterviewDAO {

    public void save(Interview obj) {
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

    public List<Interview> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Interview", Interview.class).list();
        }
    }

    public Interview getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Interview.class, id);
        }
    }

    public void update(Interview obj) {
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
            Interview obj = session.get(Interview.class, id);
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

    public long countAttended() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select count(i.interviewId) from Interview i where i.result <> 'PENDING'", Long.class).uniqueResult();
        }
    }
}
