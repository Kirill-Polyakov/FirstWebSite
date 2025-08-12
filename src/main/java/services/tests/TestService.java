package services.tests;

import dao.TestDao;
import entities.tests.Test;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TestService {
    private final TestDao testDao;
    private final AddTestService addTestService;

    public List<Test> getAllTests() {
        return testDao.findAllTests();
    }

    public List<Test> getTestsByCategory(String categoryName) {
        List<Test> allTests = getAllTests();
        return allTests.stream().filter(test -> test.getCategoryName().equals(categoryName)).toList();
    }

    public Optional<Test> getTestById(String id) {
        List<Test> allTests = getAllTests();
        return allTests.stream().filter(test -> test.getId().equals(id)).findFirst();
    }

    public void addTest(HttpServletRequest req) {
        addTestService.addTest(testDao, getLastTestId(), req);
    }

    public void deleteTest(String testId) {
        getTestById(testId).ifPresent(testDao::deleteTest);
    }

    public void updateTest(HttpServletRequest req) {
        String testId = req.getParameter("testId");
        addTestService.addTest(testDao, testId, req);
    }

    private String getLastTestId() {
        List<Test> findAllTests = getAllTests();
        List<Integer> testsId = new ArrayList<>();

        if (!findAllTests.isEmpty()) {
            findAllTests.forEach(test -> testsId.add(Integer.parseInt(test.getId())));
        }
        if (!testsId.isEmpty()) {
            int max = Collections.max(testsId) + 1;
            return Integer.toString(max);
        } else {
            return "0";
        }
    }

}
