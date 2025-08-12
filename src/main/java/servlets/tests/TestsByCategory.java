package servlets.tests;

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

@WebServlet(urlPatterns = "/secure/tests")
public class TestsByCategory extends HttpServlet {
    private TestService testService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        testService = (TestService) config.getServletContext().getAttribute("testService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("selectedCategory");
        List<Test> testsByCategory = testService.getTestsByCategory(category);
        if (testsByCategory != null) {
            req.setAttribute("testsByCategory", testsByCategory);
            req.getRequestDispatcher("/secure/tests.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
