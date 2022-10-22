package ua.lviv.lgs.filter;

import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/createProduct.jsp")
public class createProductFilter implements Filter {

    private FilterService filterService = FilterService.getFilterService();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        filterService.doFilterValidation(request, response, chain, Arrays.asList(UserRole.ADMINISTRATOR));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
