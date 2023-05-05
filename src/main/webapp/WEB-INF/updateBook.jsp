<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="com.example.mylibrary.manager.AuthorManager" %>
<%@ page import="com.example.mylibrary.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 29.04.2023
  Time: 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateEmployee</title>
</head>
<%List<User> users = (List<User>) request.getAttribute("users");
  List<Author> authors = (List<Author>) request.getAttribute("authorList");%>

<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<%Book book = (Book) request.getAttribute("book");
  AuthorManager authorManager = new AuthorManager();%>
<a href="/books">Back</a>
<h2>Update Book</h2>
<form action="/updateBook" method="post" enctype="multipart/form-data">
  <input name="id" type="hidden" value="<%=book.getId()%>">
  title: <input type="text" name="title" value="<%=book.getTitle()%>"><br><br>
  description:<input type="text" name="description" value="<%=book.getDescription()%>"><br><br>
  price:<input type="text" name="price" value="<%=book.getPrice()%>"><br><br>
author: <select name="authorId"> <br><br>
  <%for (Author author : authors) {%>
    <option  value="<%=author.getId()%>"><%=author.getSurname()%></option>
  <%}%></select>
  user: <select name="userId"> <br> <br>
<%for (User user : users) {%>
    <option value="<%=user.getId()%>"> <%=user.getName()%> <%=user.getId()%></option>
  <%}%>
</select>
  <input type="file" name="picture"> <br><br>
  <input type="submit" value="update">
</form>
</body>
</html>
