package ua.lviv.lgs.servlet;

import org.apache.log4j.Logger;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
import ua.lviv.lgs.service.impl.ProductServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product")
public class Periodicals extends HttpServlet {

    private ProductService productService = ProductServiceImpl.getProductService();
//    private static Logger LOGGER = Logger.getLogger(Periodicals.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String productName = req.getParameter("pName");
        String productDescription = req.getParameter("pDescription");
        String productPrice = req.getParameter("pPrice");

        Product product = new Product(productName, productDescription, getValidatedPrice(productPrice));

        productService.create(product);
        } catch (SQLException e) {
            System.out.println(e);
        }

        resp.setContentType("text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("Success");

    }

    private double getValidatedPrice(String price) {
        if(price == null || price.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(price);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");

        try {
         Product product = productService.read(Integer.parseInt(productId));

         req.setAttribute("product", product);
         req.getRequestDispatcher("singleProduct.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
