package com.careertracker.dao;
import com.careertracker.entity.JobApplication;
import com.careertracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class JobApplicationDAO {

    public void save(JobApplication obj) {
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

    public List<JobApplication> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from JobApplication", JobApplication.class).list();
        }
    }

    public JobApplication getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(JobApplication.class, id);
        }
    }

    public void update(JobApplication obj) {
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
            JobApplication obj = session.get(JobApplication.class, id);
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
            return session.createQuery("select count(a.applicationId) from JobApplication a", Long.class).uniqueResult();
        }
    }
}
