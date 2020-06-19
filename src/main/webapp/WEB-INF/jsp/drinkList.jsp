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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <title>Shop</title>
    <spring:url value="resources/css/style.css" var="mainCss"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<script type="text/javascript" src="static/js/shop.js"></script>

<%--<form:form method="post"  modelAttribute="drinksList" name = "mainForm">--%>
    <table class = "shop" border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr class="shop_top">
            <th class="name"><fmt:message key="product.name"/></th>
            <th class="price"><fmt:message key="product.price"/></th>
            <th class="volume"><fmt:message key="product.volume"/></th>
            <th class="left"><fmt:message key="product.quantity"/></th>
            <th class="count">Edit</th>
            <th class="count">Delete</th>
        </tr>
        </thead>
        <c:forEach items="${drinksList}" var="item" varStatus="status">
            <jsp:useBean id="item" type="com.myshop.domain.Drink"/>
            <tr class = "shop_item">
                <td class="name">${item.name}</td>
                <td class="price">${item.purchasePrice}</td>
                <td class="volume">${item.volume}</td>
                <td class="left" name = "quantityInStore">${item.quantity}</td>
                <td class="left"><a href="drink/edit/${item.id}" class="edit-icon"></a></td>
                <td class="left"><a href="drink/remove/${item.id}" class="delete-btn"></a></td>
            </tr>
        </c:forEach>
    </table>
<%--</form:form>--%>

</body>
</html>
