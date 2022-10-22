package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static String READ_ALL = "select * from product";
    private static String CREATE = "insert into product(`name`, `description`, `cost`) values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from product where id=?";
    private static String UPDATE = "update product set name=?, description=?, cost=? where id=?";
    private static String DELETE_BY_ID = "delete * from product where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

//    private static Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

    public ProductDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Double cost = rs.getDouble("cost");

                Product product = new Product(id,name, description, cost);
                productRecords.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return productRecords;
    }

    @Override
    public Product read(int id) throws SQLException {
        Product product = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            ResultSet rs = preparedStatement.getResultSet();
            rs.next();
            Integer productId = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Double cost = rs.getDouble("cost");

            product = new Product(productId, name, description, cost);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }

    @Override
    public Product create(Product product) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(CREATE, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getCost());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
