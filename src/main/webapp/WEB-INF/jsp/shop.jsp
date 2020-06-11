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
<form:form method="post"  modelAttribute="order" name = "mainForm" action="cart">
    <table class = "shop" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th class="name">Name</th>
            <th class="price">Price</th>
            <th class="volume">Volume</th>
            <th class="left">Left, pc</th>
            <th class="count">Add to cart</th>
        </tr>
        </thead>
        <c:forEach items="${order.orderItems}" var="item" varStatus="status">
            <jsp:useBean id="item" type="com.myshop.service.to.ShortenedOrderItem"/>
            <tr class = "shop_item">
                <td class="name">${item.drink.name}</td>
                <td class="price">${item.priceWithoutDiscount}</td>
                <td class="volume">${item.drink.volume}</td>
                <td class="left" name = "quantityInStore">${item.drink.quantity}</td>
                <td class="count"><input type="number" class="count" min="0" max="99" step="1" name = "orderItems[${status.index}].quantity" value="${item.quantity}"></td>
            </tr>
        </c:forEach>
        <input type="submit" value="Go to cart" class ="submit_button">
    </table>

</form:form>
</body>
</html>
