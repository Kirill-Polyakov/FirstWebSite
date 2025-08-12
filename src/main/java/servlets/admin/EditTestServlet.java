package servlets.admin;

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

@WebServlet(urlPatterns = "/secure/admin/edit")
public class EditTestServlet extends HttpServlet {
    private TestService testService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        testService = (TestService) config.getServletContext().getAttribute("testService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String testId = req.getParameter("testId");
        Optional<Test> testById = testService.getTestById(testId);
        testById.ifPresent(test -> req.setAttribute("test", test));
        req.getRequestDispatcher("/secure/admin/change-test.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testService.updateTest(req);
        resp.sendRedirect(req.getContextPath() + "/secure/admin/menu");
    }
}
