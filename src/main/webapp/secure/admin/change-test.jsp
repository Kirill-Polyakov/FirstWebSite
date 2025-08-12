<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>${test.testName}</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<form id="addTestForm" action="/secure/admin/edit" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="hidden" name="testId" value="${test.id}">
    Название категории:<br>
    <label>
        <textarea name="categoryName" rows="3" cols="50">${test.categoryName}</textarea><br>
    </label><br>
    Название теста:<br>
    <label>
        <textarea name="testName" rows="3" cols="50">${test.testName}</textarea><br>
    </label><br>

    <div class="questions-container">
        <div class="question-block">
            <c:forEach var="question" items="${test.questions}">
                Вопрос № ${question.id}:<br>
                <textarea name="question[]" rows="3" cols="50">${question.question}</textarea><br>
                <c:forEach var="answer" items="${question.answers}">
                    <div>
                        <input type="text" name="answers[]" value="${answer.answer}">

                        <input type="hidden" name="isCorrect[]" value="${answer.isCorrect}"/>

                        <input type="checkbox" onclick="toggleCorrect(this)" ${answer.isCorrect ? 'checked' : ''}>Правильный ответ?
                    </div>
                </c:forEach>
            </c:forEach>
        </div>
    </div>

    <button type="submit" class="button">Сохранить</button>
</form>

<script>
    function toggleCorrect(checkbox) {
        const hiddenField = checkbox.parentElement.querySelector('input[name^="isCorrect"]');
        if (checkbox.checked) {
            hiddenField.value = 'true';
        } else {
            hiddenField.value = 'false';
        }
    }
</script>

</body>
</html>
