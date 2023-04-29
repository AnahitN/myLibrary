<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 28.04.2023
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<%List<Book> books = (List<Book>) request.getAttribute("books");
    User user = (User) session.getAttribute("user");
%>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<a href="/">Back</a>
<h2 style="color: saddlebrown">Books</h2> <a href="/createBook">CreateBook</a>
<table border="1" style="background-color: bisque">
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>author</th>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <th>action</th>
        <%}%>
    </tr>
    <%if (books != null && !books.isEmpty()){%>
    <%for (Book book : books) {%>
    <tr>
        <td><%=book.getId()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getAuthor().getSurname()%></td>
        <%if (user.getUserType() == UserType.ADMIN){%>
        <td> <a href="/removeBook?id=<%=book.getId()%>">Delete</a> /
        <a href="/updateBook?id=<%=book.getId()%>"> Update</a>
        </td>
        <%}%>
    </tr>
    <%}%>
    <%}%>
</table>
<form action="/search" method="post">
    <input type="search" value="search">
    <input type="submit" value="submit">
</form>
</body>
</html>
