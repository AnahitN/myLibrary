<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    List<Author> authors = (List<Author>) request.getAttribute("authorList");
%>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<a href="/books">Back</a>
<h2 style="color: saddlebrown">Create book</h2>
<form action="/createBook" method="post" enctype="multipart/form-data">
    title: <input type="text" name="title"><br><br>
    description: <input type="text" name="description"><br><br>
    price: <input type="text" name="price"><br><br>
    author:
    <select name="authorId">
        <%
            for (Author author : authors) {%>
        <option value="<%=author.getId()%>"><%=author.getSurname()%>
        </option>
        <%}%>
    </select><br><br>
    <select name="userId">
        <%for (User user : users) {%>
<option value="<%=user.getId()%>"> <%=user.getName()%> <%=user.getId()%></option>
           <%}%>
    </select>
    <input type="file" name="picture">
    <input type="submit" value="Create">
</form>

</body>
</html>
