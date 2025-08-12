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
import java.util.Optional;

@WebServlet(urlPatterns = "/secure/tests/id")
public class TestServlet extends HttpServlet {
    private TestService testService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        testService = (TestService) getServletContext().getAttribute("testService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String testId = req.getParameter("testsByCategory");
        Optional<Test> testById = testService.getTestById(testId);
        if (testById.isPresent()) {
            Test selectedTest = testById.get();
            req.setAttribute("selectedTest", selectedTest);
            req.getRequestDispatcher("/secure/startTest.jsp").forward(req, resp);
        } else {
            resp.sendError(404);
        }
    }
}
