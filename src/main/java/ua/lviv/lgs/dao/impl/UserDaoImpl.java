package ua.lviv.lgs.dao.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

import static ua.lviv.lgs.utils.ConnectionUtils.openConnection;

public class UserDaoImpl implements UserDao {
    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private EntityManager entityManager = FactoryManager.getEntityManager();

    @Override
    public List<User> readAll() throws SQLException {
        try {
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public User read(int id) throws SQLException {
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
           LOGGER.error(e);
        }

        return user;
    }

    @Override
    public User create(User user) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(user.toString());
        }

        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        try {
            // TODO
        } catch (Exception e) {
           LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
        // TODO
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public User readByEmail(String email) {
        User user = null;
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> from = criteria.from(User.class);
            criteria.select(from);
            criteria.where(builder.equal(from.get("email"), email));
            TypedQuery<User> typed = entityManager.createQuery(criteria);
            user = typed.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
