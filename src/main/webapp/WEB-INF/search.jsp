<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Tornado
  Date: 29.04.2023
  Time: 5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<%List<Book> books = (List<Book>) request.getAttribute("books");
    for (Book book : books) {%>
<body>
<ul>
    <li><%=book.getTitle()%></li>
</ul>
<%}%>

</body>
</html>
