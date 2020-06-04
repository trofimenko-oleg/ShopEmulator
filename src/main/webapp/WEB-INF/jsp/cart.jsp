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
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript" src="static/js/functions.js"></script>

<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        Shopping Bag
    </div>
    <c:forEach items="${drinks}" var="drink">
        <jsp:useBean id="drink" scope="page" type="com.myshop.domain.Drink"/>
    <!-- Товар #${drink.id} -->
    <div class="item">
        <div class="buttons">
            <span class="delete-btn"></span>
        </div>

        <div class="description productName">
            <span>${drink.name}</span>
        </div>

        <div class="description productPrice">
            <span>${drink.purchasePrice}</span>
        </div>

        <div class="quantity" name = "test">
            <button class="minus-btn edit-count" type="button" name="button">
                <img src="static/img/minus-5-16.png" alt="" />
            </button>
            <input class = "myInput" type="number" max="99", min="0" name="name" value="1">
            <button class="plus-btn edit-count" type="button" name="button" >
                <img src="static/img/plus-5-16.png" alt="" />
            </button>
        </div>

        <div class="description fullPrice">
            <span></span>
        </div>
    </div>
    </c:forEach>

    <div class="bottom"><span id = "finalCount"></span></div>
    <div class="bottom"><span id = "finalPrice"></span></div>

</div>
</body>
</html>
