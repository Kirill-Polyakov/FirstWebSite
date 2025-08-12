<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Удаление теста</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<div class="admin-panel">
    <h2>Список тестов:</h2>
    <table border="1" cellpadding="5">
        <thead>
        <tr>
            <th>ID теста</th>
            <th>Категория</th>
            <th>Название теста</th>
            <th>Корректировка</th>
            <th>Удаление</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${allTests}" varStatus="loop">
            <tr>
                <td>${test.id}</td>
                <td>${test.categoryName}</td>
                <td>${test.testName}</td>
                <td>
                    <form action="/secure/admin/edit" method="get">
                        <input type="hidden" name="testId" value="${test.id}">
                        <button type="submit" style="color: #0062ff" name="edit-button">Редактировать тест</button>
                    </form>
                </td>
                <td>
                    <form action="/secure/admin/menu" method="post">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="testId" value="${test.id}">
                        <button type="submit" style="color: red" name="remove-button">Удалить тест</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>