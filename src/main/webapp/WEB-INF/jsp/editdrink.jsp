<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 20.06.2020
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Edit drink</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/style.css">
    <base href="${pageContext.request.contextPath}/"/>


</head>
<body>

    <div class="main">
        <form method="post"
                <c:if test = "${drink['class'].simpleName == 'NonAlcoholicDrink'}"> action = "drink/saveNonAlcoholic" </c:if>
                <c:if test = "${drink['class'].simpleName == 'AlcoholicDrink'}"> action = "drink/saveAlcoholic" </c:if>
        >
            <input type="hidden" name="id" value="${drink.id}">
            <div class = "name-outer">
                <div class = "first name-inner">Name</div>
                <div class = "second name-inner"><input type="text" name = "name" value="${drink.name}"></div>
            </div>
            <div class = "price-outer">
                <div class = "first price-inner">Price</div>
                <div class = "second price-inner"><input type="text" name = "purchasePrice" value="${drink.purchasePrice}"></div>
            </div>
            <div class = "volume-outer">
                <div class = "first volume-inner">Volume</div>
                <div class = "second volume-inner"><input type="number" name = "volume" min = "0", max="100", step="0.05" value="${drink.volume}"></div>
            </div>
            <div class = "quantity-outer">
                <div class = "first quantity-inner">Quantity</div>
                <div class = "second quantity-inner"><input type="number" name = "quantity" min = "0", step="1" value="${drink.quantity}"></div>
            </div>
            <div class = "type-outer">
                <div class = "first type-inner">Type</div>
                <div class = "second type-inner"><input type="text" name = "group" value="${drink.group}"></div>
            </div>
            <c:if test = "${drink['class'].simpleName == 'NonAlcoholicDrink'}">
            <div class = "composition-outer">
                <div class = "first composition-inner">Composition</div>
                <div class = "second composition-inner"><textarea name = "composition" rows = "10", cols="45">${drink.composition}</textarea></div>
            </div>
            </c:if>
            <c:if test = "${drink['class'].simpleName == 'AlcoholicDrink'}">
            <div class = "abv-outer">
                <div class = "first abv-inner">ABV</div>
                <div class = "second abv-inner"><input type="number" name = "volume" min = "0", max="100", step="0.1" value="${drink.ABV}"></div>
            </div>
            </c:if>
            <button type="submit">Save</button>
        </form>
    </div>
</body>
</html>
