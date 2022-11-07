package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.sql.*;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private EntityManager entityManager = FactoryManager.getEntityManager();

    @Override
    @SuppressWarnings("unchecked")
    public List<Bucket> readAll() {
        Query query = null;
        try {
            query = entityManager.createQuery("SELECT e FROM Bucket e");
        } catch (Exception e) {
//            LOGGER.error(e);
            System.out.println(e);
        }

        return query.getResultList();
    }

    @Override
    public Bucket read(int id) throws SQLException {
        Bucket bucket = null;
        try {
            bucket = entityManager.find(Bucket.class, id);
        } catch (Exception e) {
//            LOGGER.error(e);
        }
        return bucket;
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        try {
           entityManager.getTransaction().begin();
           entityManager.persist(bucket);
           entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
//        LOGGER.error(new Exception("There is no update in Bucket class"));
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        Bucket bucket = read(id);
        entityManager.getTransaction().begin();
        entityManager.remove(bucket);
        entityManager.getTransaction().commit();
    }
}
