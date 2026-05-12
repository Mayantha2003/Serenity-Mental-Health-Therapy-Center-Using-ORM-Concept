package lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.serenitymentalhealththerapycenter.config.SessionFactoryConfig;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealththerapycenter.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private Session getSession() {
        return SessionFactoryConfig.getInstance().getSession();
    }

    @Override
    public boolean save(User user) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(User user) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> findById(String id) throws Exception {
        try (Session session = getSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        try (Session session = getSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        }
    }

    @Override
    public List<String> getAllIds() throws Exception {
        try (Session session = getSession()) {
            Query<String> query = session.createQuery(
                    "SELECT u.userId FROM User u", String.class);
            return query.getResultList();
        }
    }

    @Override
    public String getLastId() throws Exception {
        try (Session session = getSession()) {
            return session.createQuery(
                            "SELECT u.userId FROM User u ORDER BY u.userId DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }


    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        try (Session session = getSession()) {
            Query<User> query = session.createQuery(
                            "FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws Exception {
        try (Session session = getSession()) {
            Query<User> query = session.createQuery(
                            "FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    @Override
    public boolean existsByUsername(String username) throws Exception {
        try (Session session = getSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    @Override
    public boolean existsByEmail(String email) throws Exception {
        try (Session session = getSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    @Override
    public void updateLastLogin(String userId) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createMutationQuery(
                            "UPDATE User u SET u.lastLogin = :now WHERE u.userId = :id")
                    .setParameter("now", LocalDateTime.now())
                    .setParameter("id", userId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void updatePassword(String userId, String newPasswordHash) throws Exception {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createMutationQuery(
                            "UPDATE User u SET u.passwordHash = :hash WHERE u.userId = :id")
                    .setParameter("hash", newPasswordHash)
                    .setParameter("id", userId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}