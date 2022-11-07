package ua.lviv.lgs.servlet;

import org.apache.log4j.Logger;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;
import ua.lviv.lgs.service.impl.ProductServiceImpl;
import ua.lviv.lgs.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketServiceImpl.getBucketService();
    private ProductService productService = ProductServiceImpl.getProductService();
    private UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String productId = req.getParameter("productId");

        HttpSession session = req.getSession(true);
        Integer userId = (Integer) session.getAttribute("userId");

        Product product = productService.read(Integer.parseInt(productId));
        User user = userService.read(userId);

        Bucket bucket = new Bucket();
        bucket.setId(bucketService.setId());
        bucket.setProduct(product);
        bucket.setUser(user);
        bucket.setPurchaseDate(new Date());

        bucketService.create(bucket);

        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String bucketId = req.getParameter("bucketId");
        bucketService.delete(Integer.parseInt(bucketId));

        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
