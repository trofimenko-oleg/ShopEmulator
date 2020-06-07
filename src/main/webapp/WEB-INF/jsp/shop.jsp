<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 02.06.2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Shop</title>
    <spring:url value="resources/css/style.css" var="mainCss"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<script type="text/javascript" src="static/js/shop.js"></script>
<hr>
<form method="post" name = "mainForm" action="cart">
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Volume</th>
            <th>Left, pc</th>
            <th></th>
            <th>
                <form method="post" action="cart">
                <input type="submit" value="Go to cart" />
                </form>
            </th>
        </tr>
        </thead>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" scope="page" type="com.myshop.service.to.ShortenedOrderItem"/>
            <tr class = "item">
                <td>${item.drink.name}</td>
                <td>${item.priceWithoutDiscount}</td>
                <td>${item.drink.volume}</td>
                <td name = "quantityInStore">${item.drink.quantity}</td>
                <td><input type="number"  name ="desiredQuantity" value="${item.quantity}"></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="submit" value="Go to cart"></td>
        </tr>
    </table>
</form>
</body>
</html>
