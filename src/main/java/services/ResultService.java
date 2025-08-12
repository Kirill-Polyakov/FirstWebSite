package services;

import dao.ResultDao;
import entities.results.Result;
import entities.results.UserChoose;
import entities.tests.Answer;
import entities.tests.Question;
import entities.tests.Test;
import entities.users.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import services.tests.TestService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ResultService {
    private final ResultDao resultDao;
    private final TestService testService;

    public void save(Result result) {
        resultDao.saveResults(result);
    }

    public Optional<Result> getResultById(String resultId) {
        return resultDao.getAllResults().getResults().stream()
                .filter(result -> result.getId().equals(resultId))
                .findFirst();
    }

    public List<Result> getResultsByUser(User user) {
        return resultDao.getAllResults().getResults().stream()
                .filter(userId -> userId.getUserId().equals(user.getId()))
                .toList();
    }

    public Map<String, Long> getStatisticByCompletedTests() {
        return resultDao.getAllResults().getResults().stream()
                .collect(Collectors.groupingBy(Result::getTestId, Collectors.counting()));
    }

    public Result buildResult(HttpServletRequest req) {
        String testId = req.getParameter("testId");
        String[] questionsId = req.getParameterValues("questionId[]");
        User user = (User) req.getSession().getAttribute("user");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' T 'HH:mm:ss");
        String testCompletionDate = LocalDateTime.now().format(formatter);

        Map<String, List<String>> answersOnTest = new HashMap<>();
        for (String qId : questionsId) {
            String[] checkboxValues = req.getParameterValues("checkbox[" + qId + "]");
            if (checkboxValues == null) {
                answersOnTest.put(qId, new ArrayList<>());
            } else {
                answersOnTest.put(qId, List.of(checkboxValues));
            }
        }

        return Result.builder()
                .id(getResultId(resultDao))
                .userId(user.getId())
                .testId(testId)
                .date(testCompletionDate)
                .answers(verifyTest(testId, answersOnTest))
                .build();
    }

    private UserChoose verifyTest(String testId, Map<String, List<String>> answersOnTest) {
        UserChoose userChoose = new UserChoose();
        Map<String, Map<String, Boolean>> isCorrect = new HashMap<>();

        Optional<Test> testById = testService.getTestById(testId);

        if (testById.isPresent()) {
            Test test = testById.get();
            List<Question> questions = test.getQuestions();

            for (Question question : questions) {
                if (answersOnTest.containsKey(question.getId())) {
                    List<Answer> answers = question.getAnswers();
                    Map<String, Boolean> answersMap = new HashMap<>();
                    for (Answer answer : answers) {
                        List<String> chooseAnswersUser = answersOnTest.get(question.getId());
                        if (chooseAnswersUser.contains(answer.getId())) {
                            answersMap.put(answer.getId(), answer.getIsCorrect());
                        }
                    }
                    isCorrect.put(question.getId(), answersMap);
                }
            }
            userChoose.setIsCorrect(isCorrect);
        }

        return userChoose;
    }

    private String getResultId(ResultDao resultDao) {
        List<Result> resultList = resultDao.getAllResults().getResults();
        if (resultList != null && !resultList.isEmpty()) {
            List<Integer> listResultIds = resultList.stream()
                    .map(result -> Integer.valueOf(result.getId()))
                    .toList();
            int maxResultId = Collections.max(listResultIds) + 1;
            return Integer.toString(maxResultId);
        } else {
            return "1";
        }
    }
}
