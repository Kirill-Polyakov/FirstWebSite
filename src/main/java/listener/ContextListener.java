package listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.ResultDao;
import dao.TestDao;
import dao.UserDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.ResultService;
import services.UserService;
import services.Validation;
import services.tests.AddTestService;
import services.tests.TestService;

import java.io.File;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        File userFile = new File("C:\\Users\\Kirill\\IdeaProjects\\FirstWebSite\\src\\main\\resources\\data\\users\\users.json");

        UserDao userDao = new UserDao(objectMapper, userFile);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserService userService = new UserService(userDao, encoder);
        Validation validation = new Validation(userDao);

        TestDao testDao = new TestDao(objectMapper);
        AddTestService addTestService = new AddTestService();
        TestService testService = new TestService(testDao, addTestService);

        File resultFile = new File("C:\\Users\\Kirill\\IdeaProjects\\FirstWebSite\\src\\main\\resources\\data\\results\\results.json");
        ResultDao resultDao = new ResultDao(objectMapper, resultFile);
        ResultService resultService = new ResultService(resultDao, testService);

        servletContext.setAttribute("resultService", resultService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("validation", validation);
        servletContext.setAttribute("testService", testService);

    }
}
