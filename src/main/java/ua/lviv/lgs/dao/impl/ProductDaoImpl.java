package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
//    private static String READ_ALL = "select * from product";
//    private static String CREATE = "insert into product(`name`, `description`, `cost`) values(" +
//            "?,?,?)";
//    private static String READ_BY_ID = "select * from product where id=?";
//    private static String UPDATE = "update product set name=?, description=?, cost=? where id=?";
//    private static String DELETE_BY_ID = "delete * from product where id=?";
//
//    private PreparedStatement preparedStatement;
//    private Connection connection;

//    private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    private EntityManager entityManager = FactoryManager.getEntityManager();

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> readAll() {
        Query query = null;
        try {
            query = entityManager.createQuery("SELECT e FROM Product e");
        } catch (Exception e) {
            System.out.println(e);
        }

        return query.getResultList();
    }

    @Override
    public Product read(int id) throws SQLException {
        Product product = null;
        try {
          product = entityManager.find(Product.class, id);
        } catch (Exception e) {
            System.out.println(e);
        }

        return product;
    }

    @Override
    public Product create(Product product) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(product.toString());
            System.out.println(e);
        }

        return product;
    }

    @Override
    public Product update(Product product) throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
