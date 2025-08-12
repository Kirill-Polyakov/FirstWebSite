package servlets.admin;

import entities.tests.Test;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ResultService;
import services.tests.TestService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = ("/secure/admin/menu"))
public class AdminMenuServlet extends HttpServlet {
    private TestService testService;
    private ResultService resultService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        testService = (TestService) config.getServletContext().getAttribute("testService");
        resultService = (ResultService) config.getServletContext().getAttribute("resultService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Test> allTests = testService.getAllTests();
        req.setAttribute("allTests", allTests);
        Map<String, Long> statisticByCompletedTests = resultService.getStatisticByCompletedTests();
        req.setAttribute("statisticByCompletedTests", statisticByCompletedTests);
        req.getRequestDispatcher("/secure/admin/admin-panel.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String testId = req.getParameter("testId");
        testService.deleteTest(testId);
        resp.sendRedirect("/secure/admin/menu");
    }
}
