package ua.lviv.lgs.servlet;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.dto.BucketDto;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/buckets")
public class BucketsController extends HttpServlet {
    private BucketService bucketService = BucketServiceImpl.getBucketService();
    private ProductService productService = ProductServiceImpl.getProductService();

    private Logger LOGGER = Logger.getLogger(BucketsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Bucket> buckets = new ArrayList<>();
            buckets = bucketService.readAll();
            Map<Integer, Product> idToProduct = productService.readAllMap();
            List<BucketDto> dtos = map(buckets, idToProduct);

            String json = new Gson().toJson(dtos);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public List<BucketDto> map(List<Bucket> buckets, Map<Integer, Product> idToProduct){

        return  buckets.stream().map(bucket->{
            BucketDto bucketDto = new BucketDto();
            bucketDto.bucketId = bucket.getId();
            bucketDto.purchaseDate = bucket.getPurchaseDate();

            Product product = idToProduct.get(bucket.getProductId());
            bucketDto.name = product.getName();
            bucketDto.description = product.getDescription();
            bucketDto.price = product.getCost();

            return bucketDto;
        }).collect(Collectors.toList());

    }
}
