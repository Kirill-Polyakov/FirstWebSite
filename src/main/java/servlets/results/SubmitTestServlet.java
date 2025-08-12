package servlets.results;

import entities.results.Result;
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
import java.util.Optional;

@WebServlet(urlPatterns = "/secure/tests/id/submit")
public class SubmitTestServlet extends HttpServlet {
    private ResultService resultService;
    private TestService testService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        resultService = (ResultService) config.getServletContext().getAttribute("resultService");
        testService = (TestService) config.getServletContext().getAttribute("testService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resultId = (String) req.getSession().getAttribute("resultId");
        Optional<Result> resultById = resultService.getResultById(resultId);

        if (resultById.isPresent()) {
            Result testResult = resultById.get();
            Test test = testService.getTestById(testResult.getTestId()).get();
            req.setAttribute("test", test);
            req.setAttribute("testResult", testResult);
        }
        req.getRequestDispatcher("/secure/result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result testResult = resultService.buildResult(req);
        resultService.save(testResult);
        String resultId = testResult.getId();
        req.getSession().setAttribute("resultId", resultId);
        resp.sendRedirect("/secure/tests/id/submit");
    }
}
