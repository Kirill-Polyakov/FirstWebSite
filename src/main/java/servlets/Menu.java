package servlets;

import entities.tests.Test;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.tests.TestService;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/secure/menu")
public class Menu extends HttpServlet {
    private TestService testService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        testService = (TestService) config.getServletContext().getAttribute("testService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Test> allTests = testService.getAllTests();
        Set<String> categoriesTests = allTests.stream().map(Test::getCategoryName).collect(Collectors.toSet());
        req.setAttribute("categories", categoriesTests);
        req.getRequestDispatcher("/secure/menu.jsp").forward(req, resp);
    }
}
