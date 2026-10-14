package com.careertracker.dao;
import com.careertracker.entity.Student;
import com.careertracker.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentDAO {

    public void save(Student obj) {
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

    public List<Student> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    public Student getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    public void update(Student obj) {
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
            Student obj = session.get(Student.class, id);
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
}
