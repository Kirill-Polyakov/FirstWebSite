<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>

<div class="tab">
    <input checked id="tab-btn-1" name="tab-btn" type="radio" value="">
    <label for="tab-btn-1">Добавить новый тест</label>
    <input id="tab-btn-2" name="tab-btn" type="radio" value="">
    <label for="tab-btn-2">Редактирование тестов</label>
    <input id="tab-btn-3" name="tab-btn" type="radio" value="">
    <label for="tab-btn-3">Статистика по тестам</label>
    <div class="tab-content" id="content-1">
        <jsp:include page="add-test.jsp"/>
    </div>
    <div class="tab-content" id="content-2">
        <jsp:include page="edit-test.jsp"/>
    </div>
    <div class="tab-content" id="content-3">
        <jsp:include page="statistics.jsp"/>
    </div>
</div>
</body>
</html>
