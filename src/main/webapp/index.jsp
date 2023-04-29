<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Main Page</title>
</head>
<body style="background-image: url('img/books-and-plants-beige-aesthetic-desktop-collage-qv7xdjmtlwzuvh66.jpg')">
<%if (session.getAttribute("user") != null) {
  response.sendRedirect("home");
}
String msg = (String) session.getAttribute("msg");
%>
<%if (msg != null){%>
<span style="background-color: lightpink; color: darkred"> <%=msg%> </span>
<%session.removeAttribute("msg");
}%>
<br>
<h2 style="color: mistyrose">Log in:</h2>
<form action="/login" method="post">
  email: <input name="email" type="text"> <br><br>
 password: <input name="password" type="password"><br>
  <input type="submit" value="Login">
</form>
<a href="/register.jsp"> Register</a>
</body>
</html>