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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/js/shop.js"></script>
    <script type="text/javascript" src="static/js/localechange.js"></script>
    <title><spring:message code="page.shop"/></title>
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

<div class="buttons_and_search_area">
    <form action="drink/new">
        <button  class="list-button" id="create-new-drink" type="submit"><spring:message code="button.createnewdrink"/></button>
    </form>
</div>

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
            <input type="text" placeholder="<spring:message code="searchfield"/>" name="search" id="search">
            <button class="list-button list-search" type="submit"></button>
        </form>
    </div>
</div>

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
                <spring:message code="alc" var="alc"/>
                <spring:message code="non" var="non"/>
                <td class="small-column">${item['class'].simpleName == "AlcoholicDrink" ? alc : non}</td>
                <td class="edit-name">${item.name}</td>
                <td class="edit-column">${item.purchasePrice}</td>
                <td class="edit-column">${item.volume}</td>
                <td class="edit-column" name = "quantityInStore">${item.quantity}</td>
                <td class="small-column"><a href="drink/edit/${item.id}" class="edit-icon"></a></td>
                <td class="small-column"><a href="drink/remove/${item.id}" class="delete-btn"></a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
