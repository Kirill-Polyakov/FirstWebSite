<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>История</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<c:if test="${empty resultsByUser}">
    <div style="text-align: center; color: red">
        <br> <br>
        <strong>История не найдена</strong>
    </div>
</c:if>
<div style="text-align: center">История тестирований:</div>
<c:forEach var="result" items="${resultsByUser}">
    <fieldset>
        <div>
            <strong>${result.date}</strong>
            <input type="hidden" name="resultIdByHistory" value="${result.id}">
            <a href="/secure/results/${result.id}" class="button">Подробности</a>
        </div>
    </fieldset>
</c:forEach>
</body>
</html>
