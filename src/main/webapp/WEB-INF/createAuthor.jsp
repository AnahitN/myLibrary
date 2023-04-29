<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Author</title>
</head>
<body style="background-image: url('../img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<a href="/authors">Back</a>
<h2 style="color: saddlebrown">Create Author</h2>
<form action="/createAuthor" method="post">
    name: <input type="text" name="name"> <br><br>
    surname: <input type="text" name="surname"><br><br>
    email: <input type="email" name="email"><br><br>
    age:<input type="text" name="age"><br><br>
   <input type="submit" value="Create">
</form>
</body>
</html>
