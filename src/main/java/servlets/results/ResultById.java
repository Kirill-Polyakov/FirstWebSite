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

@WebServlet(urlPatterns = "/secure/results/*")
public class ResultById extends HttpServlet {
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
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && !pathInfo.equals("/")) {
            String resultId = pathInfo.substring(1);

            Optional<Result> result = resultService.getResultById(resultId);

            if (result.isPresent()) {
                Result testResult = result.get();
                Test test = testService.getTestById(testResult.getTestId()).get();
                req.setAttribute("test", test);
                req.setAttribute("testResult", testResult);
                req.getRequestDispatcher("/secure/result.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Результат не найден");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный запрос");
        }
    }
}
