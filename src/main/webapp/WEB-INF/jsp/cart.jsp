<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 03.06.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <spring:url value="resources/css/style.css" var="mainCss"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <title>Cart</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript" src="static/js/functions.js"></script>

<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        Shopping Bag

    </div>
    <form method="post" modelAttribute="order" name = "submit" action="saveOrder">
    <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
        <jsp:useBean id="item" class="com.myshop.service.to.ShortenedOrderItem"/>
        <!-- Товар #<c:out value="${vs.index}"/>> -->


        <div class="item">
            <div class="buttons">
                <span class="delete-btn"></span>
            </div>

            <div class="description productName">
                <span>${item.drink.name}</span>
            </div>

            <div class="description productPrice">
                <span>${item.priceWithoutDiscount}</span>
            </div>
            <span class = "hidden" style="visibility:hidden">${item.priceWithDiscount}</span>
            <span class = "max_quantity" style="visibility:hidden">${item.drink.quantity}</span>
            <div class = "description priceWithDiscount" style="visibility:hidden"></div>

            <div class="quantity" name = "test">
                <button class="minus-btn edit-count" type="button" name="button">
                    <img src="static/img/minus-5-16.png" alt="" />
                </button>
<%--                <input class = "myInput" type="number" max="99" min="0" name="orderItems[${vs.index}].quantity" value="${item.quantity}">--%>
                <input class = "myInput" type="number" max="99" min="0" path="orderItems[${vs.index}].quantity" value="${item.quantity}">
                <button class="plus-btn edit-count" type="button" name="button" >
                    <img src="static/img/plus-5-16.png" alt="" />
                </button>
            </div>

            <div class="description fullPrice">
                <span></span>
            </div>
        </div>
    </c:forEach>
        <div class="bottom">
            <div class="bottom_block">
                <div id="finalCount"></div>
                <div id="finalPrice"></div>
            </div>
            <button type="submit" class="submit"> Confirm order </button>
        </div>
    </form>

</div>
</body>
</html>
