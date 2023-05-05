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
    String keyword = request.getParameter("keyword") == null ||
            request.getParameter("keyword").equals("null") ? "" : request.getParameter("keyword");
%>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<a href="/">Back</a>
<h2 style="color: saddlebrown">Books</h2> <a href="/createBook">CreateBook</a>
<form action="/books" method="get">
    <input type="text" name="keyword" value="<%=keyword%>">
    <input type="submit" value="search">
</form>
<table border="1" style="background-color: bisque">
    <tr>
        <th>image</th>
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
        <td><%if (book.getPicName() == null){%>
            <img src="/img/book.png" width="100">
            <%} else {%>
            <a href="/getImage?picName=<%=book.getPicName()%>"> <img src="/getImage?picName=<%=book.getPicName()%>" width="100"></a></td> <%}%>
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

</body>
</html>
