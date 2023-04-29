<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<%User user = (User) session.getAttribute("user");%>
<span style="color: saddlebrown"> <h1>Welcome <%=user.getName()%></h1></span> <a href="logout" style="color: darkred">Logout</a> <br>
<br>
<a href="/books"><h2>Books</h2></a> |
<a href="/authors"><h2>Authors</h2></a>
</body>
</html>
