package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
//    SessionFactory factory = (SessionFactory) new Configuration().configure()
//            .addAnnotatedClass(User.class).buildSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS ITEM(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name varchar(50), " +
                    "lastName varchar(50), age int)";
            Query qr = session.createSQLQuery(query).addEntity(User.class);
            qr.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String query = "DROP TABLE IF EXISTS ITEM";
            session.createSQLQuery(query).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            User us = new User(name, lastName, (byte) age);
            session.save(us);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            User ur = session.get(User.class, id);
            session.delete(ur);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> us = new ArrayList<>();
        Session session = getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            us = session.createQuery("from User", User.class).getResultList();
            for (User a : us)
                System.out.println(a);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return us;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
