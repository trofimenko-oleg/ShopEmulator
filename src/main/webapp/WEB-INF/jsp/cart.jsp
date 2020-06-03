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
    <script src="static/js/counterscript.js" type="text/javascript"></script>
</head>
<body>
<c:set var = "totalCost" scope = "session" value = "${0}"/>


<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        Shopping Bag
    </div>
    <c:forEach items="${drinks}" var="drink">
        <jsp:useBean id="drink" scope="page" type="com.myshop.domain.Drink"/>
    <!-- Товар #1 -->
    <div class="item">
        <div class="buttons">
            <span class="delete-btn"></span>
        </div>

        <div class="description">
            <span>${drink.name}</span>
            <span>${drink.volume}</span>
            <span>${drink.purchasePrice}</span>
        </div>

        <div class="quantity">
            <button class="minus-btn" type="button" name="button">
                <img src="static/img/minus-5-16.png" alt="" />
            </button>
            <input class = "myInput" type="text" name="name" value="1">
            <button class="plus-btn" type="button" name="button">
                <img src="static/img/plus-5-16.png" alt="" />
            </button>
        </div>

        <div class="total-price">
            <script>
            function getInputValue(){
                // Selecting the input element and get its value
                var elem = document.getElementsByClassName("myInput");
                var sum = 0;
                for (var i = 0; i < elem.length; ++ i) {
                    sum += elem[i].getAttribute(getInputValue());
                }
                // Displaying the value
                alert(sum);
            }
            </script>
        </div>
    </div>
    </c:forEach>
</div>
</body>
</html>
