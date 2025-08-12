<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${test.testName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<form action="/logout" method="post">
    <div style="text-align: right">
        <button class="button" type="submit">Выйти</button>
    </div>
</form>

<h4 style="text-align: center">Выберете один или несколько вариантов ответов:</h4><br>

<form action="/secure/tests/id/submit" method="post">
    <input type="hidden" name="testId" value="${selectedTest.id}">
    <c:forEach var="question" items="${selectedTest.questions}">
        <fieldset>
            <fieldset>
                <input type="hidden" name="questionId[]" value="${question.id}">
                <div style="text-align: center">${question.id}. ${question.question}</div>
            </fieldset>
            <c:forEach var="answer" items="${question.answers}">
                <fieldset>
                    <div style="text-align: center">
                        <label>
                            <input type="checkbox" name="checkbox[${question.id}]" value="${answer.id}"/>
                                ${answer.answer}
                        </label>
                    </div>
                </fieldset>
            </c:forEach>
        </fieldset>
        <br><br>
    </c:forEach>

    <button class="button" type="submit">Ответить</button>
</form>

</body>
</html>
