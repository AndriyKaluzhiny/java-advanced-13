package ua.lviv.lgs.servlet;

import com.google.gson.Gson;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.service.ProductService;
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

@WebServlet("/products")
public class Products extends HttpServlet {

    private ProductService productService = ProductServiceImpl.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ArrayList<Product> productList = (ArrayList<Product>) productService.readAll();
            String json = new Gson().toJson(productList);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
