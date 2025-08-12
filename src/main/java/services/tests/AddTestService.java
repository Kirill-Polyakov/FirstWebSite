package services.tests;


import dao.TestDao;
import entities.tests.Answer;
import entities.tests.Question;
import entities.tests.Test;
import entities.users.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class AddTestService {

    public void addTest(TestDao testDao, String lastTestId, HttpServletRequest req) {
        String categoryName = req.getParameter("categoryName");
        String testName = req.getParameter("testName");
        String[] questions = req.getParameterValues("question[]");
        String[] answers = req.getParameterValues("answers[]");
        String[] isCorrect = req.getParameterValues("isCorrect[]");

        List<List<Answer>> answersLists = buildAnswersToList(answers, isCorrect);
        List<Question> questionList = buildQuestionsToList(questions, answersLists);
        User user = (User) req.getSession().getAttribute("user");

        Test newTest = Test.builder()
                .id(lastTestId)
                .testName(testName)
                .categoryName(categoryName)
                .createdBy(user.getId())
                .questions(questionList)
                .build();

        testDao.saveTest(newTest);
    }

    private List<Question> buildQuestionsToList(String[] questions, List<List<Answer>> answersLists) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            Question question = Question.builder()
                    .id(String.valueOf(i + 1))
                    .question(questions[i])
                    .answers(answersLists.get(i))
                    .build();
            questionList.add(question);
        }
        return questionList;
    }

    private List<List<Answer>> buildAnswersToList(String[] answers, String[] isCorrect) {
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < answers.length; i++) {
            int cyclicId = ((i % 4) + 1);
            Answer answer = Answer.builder()
                    .id(String.valueOf(cyclicId))
                    .answer(answers[i])
                    .isCorrect(Boolean.parseBoolean(isCorrect[i]))
                    .build();
            answerList.add(answer);
        }
        int size = 4;
        List<List<Answer>> fragmentedAnswersLists = new ArrayList<>();
        for (int i = 0; i < answerList.size(); i += size) {
            fragmentedAnswersLists.add(answerList.subList(i, Math.min(i + size, answerList.size())));
        }
        return fragmentedAnswersLists;
    }
}
