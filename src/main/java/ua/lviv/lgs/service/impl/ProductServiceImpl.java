package ua.lviv.lgs.service.impl;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.impl.ProductDaoImpl;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    private static ProductServiceImpl productServiceImpl;
//    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl() {
        try {
            productDao = new ProductDaoImpl();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    public static ProductService getProductService() {
        if (productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }

        return productServiceImpl;
    }


    @Override
    public List<Product> readAll() throws SQLException {
        return productDao.readAll();
    }

    @Override
    public Product read(int id) throws SQLException {
        return productDao.read(id);
    }

    @Override
    public Product create(Product product) throws SQLException {
        return productDao.create(product);
    }

    @Override
    public Product update(Product product) throws Exception {
        return productDao.update(product);
    }

    @Override
    public void delete(int product) throws SQLException {
        productDao.delete(product);
    }

    @Override
    public Map<Integer, Product> readAllMap() throws SQLException {
        return  readAll().stream().collect(Collectors.toMap(Product::getId, Function.identity()));
    }
}
