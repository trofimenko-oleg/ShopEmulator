<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 29.05.2020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
            <h1>Message: ${message}</h1>
            <a href="cart">cart</a>
            <a href="cart2">cart2</a>
            <form method="post" action="cart2">
                <input type="submit" value="Go to cart" />
            </form>
</body>
</html>
