<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Меню</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<form action="/secure/admin/menu" method="get">
    <div style="text-align: left">
        <button class="button" type="submit">Админ панель</button>
    </div>
</form>

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
        Выберете рубрику, по которой хотите пройти тестирование:
    </h1>
</div>

<c:forEach var="category" items="${categories}">
    <form action="/secure/tests" method="get">
        <input type="hidden" name="selectedCategory" value="${category}"/>
        <button class="button" type="submit">
                ${category}
        </button>
    </form>
</c:forEach>

</body>
</html>
