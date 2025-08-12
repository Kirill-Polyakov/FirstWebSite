<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Авторизация</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-register.css">
</head>
<body>
<form action="/login" method="post">
  <h4>Авторизация</h4>
  <label>
    <input class="name" type="text" name="login" placeholder="Введите логин" required/>
  </label>
  <label>
    <input class="pw" type="password" name="password" placeholder="Введите пароль" required/>
  </label>
  <button class="button" type="submit">Войти</button>
</form>
</body>
</html>
