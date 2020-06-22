<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 02.06.2020
  Time: 17:21
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <jsp:include page="fragments/resources.jsp"/>
    <script type="text/javascript" src="static/js/shop.js"></script>
    <script type="text/javascript" src="static/js/localechange.js"></script>
    <title><spring:message code="page.shop"/></title>
     <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">

</head>
<body>
<div id="locale-change">
    <select id="locales">
        <option value=""></option>
        <option value="en">en</option>
        <option value="ru">ru</option>
        <option value="uk">ua</option>
    </select>
</div>
<jsp:include page="fragments/strangetop.jsp"/>



<form:form method="post"  modelAttribute="order" name = "mainForm" action="cart">
    <input type="submit" value="<spring:message code="shop.gotoCart"/>" class="submit_button">
    <table class = "shop" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr class="shop_top">
            <th class="name"><spring:message code="product.name"/></th>
            <th class="price"><spring:message code="product.price"/></th>
            <th class="volume"><spring:message code="product.volume"/></th>
            <th class="left"><spring:message code="product.quantity"/></th>
            <th class="count"><spring:message code="product.toCart"/></th>
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
    </table>
    <input type="submit" value="<spring:message code="shop.gotoCart"/>" class="submit_button">
</form:form>

</body>
</html>
