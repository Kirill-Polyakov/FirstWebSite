package servlets.results;

import entities.results.Result;
import entities.users.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ResultService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/secure/results")
public class ResultsServlet extends HttpServlet {
    private ResultService resultService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        resultService = (ResultService) config.getServletContext().getAttribute("resultService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Result> resultsByUser = resultService.getResultsByUser(user);
        req.setAttribute("resultsByUser", resultsByUser);
        req.getRequestDispatcher("/secure/history.jsp").forward(req, resp);
    }
}
