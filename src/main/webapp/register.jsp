<%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 29.04.2023
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
</head>
<body style="background-image: url('img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<%if (session.getAttribute("user") != null) {
  response.sendRedirect("/home");
}%>

<br><h2 style="color: saddlebrown"><b>Register:</b></h2><br>
<form action="/register" method="post">
 Name: <input name="name" type="text"><br><br>
 Surname: <input name="surname" type="text"> <br><br>
 Email: <input name="email" type="text"> <br><br>
 Password: <input name="password" type="password"><br><br>
  <select name="type">
    <option value="ADMIN"> ADMIN</option>
    <option value="USER">USER</option>
  </select>
  <input type="submit" value="register">
</form>
<a href="/"> Back </a>
</body>
</html>
