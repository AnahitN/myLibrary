<%@ page import="com.example.mylibrary.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 29.04.2023
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Author</title>
</head>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<%Author author = (Author) request.getAttribute("author");%>
<a href="/authors">Back</a>
<h2 style="color: saddlebrown">Update Author</h2>
<form action="/updateAuthor" method="post">
  <input name="id" type="hidden" value="<%=author.getId()%>">
  name: <input type="text" name="name" value="<%=author.getName()%>"><br><br>
  surname:<input type="text" name="surname" value="<%=author.getSurname()%>"><br><br>
  email:<input type="email" name="email" value="<%=author.getEmail()%>"><br><br>
  age:<input type="text" name="age" value="<%=author.getAge()%>"><br><br>
  <input type="submit" value="update">
</form>
</body>
</html>
