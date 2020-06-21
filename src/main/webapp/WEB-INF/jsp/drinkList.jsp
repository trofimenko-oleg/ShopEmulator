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


<html>
<head>
    <jsp:include page="fragments/resources.jsp"/>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--    <base href="${pageContext.request.contextPath}/"/>--%>
    <title><spring:message code="page.shop"/></title>
<%--    <spring:url value="resources/css/style.css" var="mainCss"/>--%>
<%--    <link href="${mainCss}" rel="stylesheet"/>--%>
<%--    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>

</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>

<script type="text/javascript" src="static/js/shop.js"></script>

<div class="buttons_and_search_area">
    <div class="list-button-block">
      <form action="drink/alcoholic">
            <button class="list-button" type="submit"><spring:message code="button.alcoholic"/></button>
        </form>
    </div>
    <div class="list-button-block">
        <form action="drink/nonalcoholic">
            <button class="list-button" type="submit"><spring:message code="button.nonalcoholic"/></button>
        </form>
    </div>
    <div class="list-button-block">
        <form action="drink/list">
            <button class="list-button" type="submit"><spring:message code="button.all"/></button>
        </form>
    </div>
    <div class="list-button-block">
        <form class="example" action="drink/search">
            <input type="text" placeholder="Search.." name="search" id="search">
            <button class="list-button list-search" type="submit"></button>
        </form>
    </div>
</div>
<%--<form:form method="post"  modelAttribute="drinksList" name = "mainForm">--%>
    <table class = "shop" border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr class="list">
                <th class="small-column"><spring:message code="product.isalc"/></th>
                <th class="edit-name"><spring:message code="product.name"/></th>
                <th class="edit-column"><spring:message code="product.price"/></th>
                <th class="edit-column"><spring:message code="product.volume"/></th>
                <th class="edit-column"><spring:message code="product.quantity"/></th>
                <th class="small-column"><spring:message code="product.edit"/></th>
                <th class="small-column"><spring:message code="product.delete"/></th>
            </tr>
        </thead>
        <c:forEach items="${drinksList}" var="item" varStatus="status">
            <jsp:useBean id="item" type="com.myshop.domain.Drink"/>
            <tr class = "shop_item">
                <td class="small-column">${item['class'].simpleName == "AlcoholicDrink" ? "Alc" : "Non"}</td>
                <td class="edit-name">${item.name}</td>
                <td class="edit-column">${item.purchasePrice}</td>
                <td class="edit-column">${item.volume}</td>
                <td class="edit-column" name = "quantityInStore">${item.quantity}</td>
                <td class="small-column"><a href="drink/edit/${item.id}" class="edit-icon"></a></td>
                <td class="small-column"><a href="drink/remove/${item.id}" class="delete-btn"></a></td>
            </tr>
        </c:forEach>
    </table>
<%--</form:form>--%>

</body>
</html>
