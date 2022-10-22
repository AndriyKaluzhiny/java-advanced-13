package ua.lviv.lgs.servlet;

import com.google.gson.Gson;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.dto.UserLogin;
import ua.lviv.lgs.service.UserService;
import ua.lviv.lgs.service.impl.UserServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@MultipartConfig
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = UserServiceImpl.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.readByEmail(email);
		System.out.println(123321);

		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", user.getId());
			session.setAttribute("role", user.getRole());


			UserLogin userLogin = new UserLogin();
			userLogin.destinationUrl = "cabinet.jsp";
			userLogin.userEmail = user.getEmail();
			String json = new Gson().toJson(userLogin);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}

	}	

}
