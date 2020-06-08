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
            <a href="shop">shop/</a>
            <a href="cart">/shop/</a>


            <form method="post" action="shop/cart">
                <input type="submit" value="shop/cart" />
            </form>
</body>
</html>
