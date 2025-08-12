<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Результат теста</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="result-container">
    <div class="result-title">
        <h1>Результат тестирования:</h1>
    </div>

    <p><strong>Категория:</strong> ${test.categoryName}</p>
    <p><strong>Тест:</strong> ${test.testName}</p>
    <p><strong>Дата прохождения:</strong> ${testResult.date}</p>

    <c:if test="${not empty testResult.answers.isCorrect}">
        <table class="result-table">
            <thead>
            <tr>
                <th>Вопрос</th>
                <th>Выбранный ответ</th>
                <th>Правильно?</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${testResult.answers.isCorrect}" var="questionEntry">
                <c:forEach items="${questionEntry.value}" var="answerEntry">
                    <tr>
                        <td>${questionEntry.key}</td>
                        <td>${answerEntry.key}</td>
                        <td>${answerEntry.value ? 'Да' : 'Нет'}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>
