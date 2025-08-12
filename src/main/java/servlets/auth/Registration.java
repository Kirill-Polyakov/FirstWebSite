package servlets.auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;
import services.Validation;

import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {

    private UserService userService;
    private Validation validation;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
        validation = (Validation) config.getServletContext().getAttribute("validation");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (validation.validateRegister(login, password)) {
            userService.saveUsers(login, password);
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/registration");
        }

    }
}
