package ua.lviv.lgs.dao.impl;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.utils.ConnectionUtils;
import ua.lviv.lgs.utils.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket(`userId`, `productId`,`purchaseDate`) values(" +
            "?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id=?";
    private static String DELETE_BY_ID = "delete from bucket where id=?";

    private PreparedStatement preparedStatement;
    private Connection connection;

//    private static Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    public BucketDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> buckets = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer userId = rs.getInt("userId");
                Integer productId = rs.getInt("productId");
                java.util.Date purchaseDate = rs.getDate("purchaseDate");

                Bucket bucket = new Bucket(id,userId, productId, purchaseDate);
                buckets.add(bucket);
            }
        } catch (SQLException e) {
//            LOGGER.error(e);
        }

        return buckets;
    }

    @Override
    public Bucket read(int id) throws SQLException {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.getResultSet();
        } catch (SQLException e) {
//            LOGGER.error(e);
        }
        return Mapper.bucketMapper(resultSet);
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getInt(1));
        } catch (SQLException e) {
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
        preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
