<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${param.selectedCategory}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<form action="/logout" method="post">
    <div style="text-align: right">
        <button class="button" type="submit">Выйти</button>
    </div>
</form>

<form action="/secure/results" method="get">
    <div style="text-align: right">
        <button class="button">
            Мои результаты
        </button>
    </div>
</form>

<div style="text-align: center">
    <h1>
        ${param.selectedCategory}
    </h1>
</div>

<c:forEach var="tests" items="${testsByCategory}">
    <form action="/secure/tests/id" method="get">
        <input type="hidden" name="testsByCategory" value="${tests.id}"/>
        <button class="button" type="submit">
                ${tests.testName}
        </button>
    </form>
</c:forEach>

</body>
</html>
