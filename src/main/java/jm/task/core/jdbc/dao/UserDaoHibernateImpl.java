package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users(id INT AUTO_INCREMENT,name VARCHAR(45) NOT NULL,lastName VARCHAR(45) NOT NULL,age INT(3) NOT NULL,PRIMARY KEY (id))", User.class)
                    .executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS Users", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.load(User.class, id));
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> result = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            System.out.println(result);
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE Users", User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException e){
                }
            }
            ex.printStackTrace();
        }
    }
}
