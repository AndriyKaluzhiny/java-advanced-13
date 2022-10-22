package ua.lviv.lgs.servlet;

import org.apache.log4j.Logger;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.service.BucketService;
import ua.lviv.lgs.service.impl.BucketServiceImpl;

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

@WebServlet("/bucket")
public class BucketController extends HttpServlet {

    private BucketService bucketService = BucketServiceImpl.getBucketService();

    private Logger LOGGER = Logger.getLogger(BucketsController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String id = req.getParameter("productId");
        HttpSession session = req.getSession(true);
        Integer userId = (Integer) session.getAttribute("userId");
        Bucket bucket = new Bucket(Integer.parseInt(id), userId, new Date());
        bucketService.create(bucket);

        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");
        } catch (SQLException e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
        }
    }
}
