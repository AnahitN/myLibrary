<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 29.04.2023
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>
<%List<Author> authors = (List<Author>) request.getAttribute("authorList");%>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<a href="/books">Back</a>
<h2 style="color: saddlebrown">Create book</h2>
<form action="/createBook" method="post">
    title: <input type="text" name="title"><br><br>
    description: <input type="text" name="description"><br><br>
    price: <input type="text" name="price"><br><br>
    author:
    <select name="authorId">
        <%
            for (Author author : authors) {%>
        <option value="<%=author.getId()%>"> <%=author.getSurname()%> </option>
    <%}%>
    </select><br><br>
    <input type="submit" value="Create">
</form>

</body>
</html>
