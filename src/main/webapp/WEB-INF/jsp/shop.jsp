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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
<form:form method="post" modelAttribute="order" name = "mainForm" action="cart">
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Volume</th>
            <th>Left, pc</th>
            <th></th>
            <th>
                <input type="submit" value="Go to cart" />
            </th>
        </tr>
        </thead>
        <c:forEach items="${order.orderItems}" var="item" varStatus="status">
            <jsp:useBean id="item" type="com.myshop.service.to.ShortenedOrderItem"/>
            <tr class = "item">
                <td>${item.drink.name}</td>
                <td>${item.priceWithoutDiscount}</td>
                <td>${item.drink.volume}</td>
                <td name = "quantityInStore">${item.drink.quantity}</td>
                <td><input type="text" name = "orderItems[${status.index}].quantity" value="${item.quantity}"></td>
<%--                <td><input type="number" min="0" max="99" path="order[${status.index}].quantity" name ="order[${status.index}].quantity" value="${item.quantity}"></td>--%>
            </tr>
        </c:forEach>
        <tr>
        </tr>
    </table>
    <input type="submit" value="Go to cart">
</form:form>
</body>
</html>
