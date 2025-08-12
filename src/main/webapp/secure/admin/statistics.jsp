<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Статистика</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="admin-panel">
    <h2>Список тестов:</h2>
    <table border="1" cellpadding="5">
        <thead>
        <tr>
            <th>ID теста</th>
            <th>Название теста</th>
            <th>Количество прохождений</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${allTests}" varStatus="loop">
            <tr>
                <td>${test.id}</td>
                <td>${test.testName}</td>
                <td><c:out value="${statisticByCompletedTests[test.id]}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
