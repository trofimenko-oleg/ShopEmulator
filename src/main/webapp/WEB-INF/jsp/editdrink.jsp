<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 20.06.2020
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Edit drink</title>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">--%>
<%--    <base href="${pageContext.request.contextPath}/"/>--%>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>
<div id="edit-form">
    <div class="edit-drink">
        <c:if test = "${drink['class'].simpleName == 'NonAlcoholicDrink'}"><c:set var="action" value="drink/saveNonAlcoholic"/></c:if>
        <c:if test = "${drink['class'].simpleName == 'AlcoholicDrink'}"><c:set var="action" value="drink/saveAlcoholic"/></c:if>
<%--            <c:if test = "${drink['class'].simpleName == 'NonAlcoholicDrink'}"> action = "drink/saveNonAlcoholic" </c:if>--%>
<%--            <c:if test = "${drink['class'].simpleName == 'AlcoholicDrink'}"> action = "drink/saveAlcoholic" </c:if>--%>
        <form:form method="post" modelAttribute="drink" action="${action}">
            <table>
                <input type="hidden" name="id" value="${drink.id}">
                <tr class = "name-outer">
                    <td class = "first name-inner">Name</td>
                    <td class = "second name-inner"><input type="text" name = "name" class = "edit-form" value="${drink.name}"></td>
                </tr>
                <tr class = "price-outer">
                    <td class = "first price-inner">Price</td>
                    <td class = "second price-inner"><input type="number" name = "purchasePrice" class = "edit-form" min="0" step="0.01" value="${drink.purchasePrice}"></td>
                </tr>
                <tr class = "volume-outer">
                    <td class = "first volume-inner">Volume</td>
                    <td class = "second volume-inner"><input type="number" name = "volume" class = "edit-form" min = "0" max="100" step="0.01" value="${drink.volume}"></td>
                </tr>
                <tr class = "quantity-outer">
                    <td class = "first quantity-inner">Quantity</td>
                    <td class = "second quantity-inner"><input type="number" name = "quantity" class = "edit-form" min = "0" step="1" value="${drink.quantity}"></td>
                </tr>
                <tr class = "type-outer">
                    <td class = "first type-inner">Type</td>
                    <td class = "second type-inner">
                            <form:select path="group" name ="group" class = "edit-form">
                                <c:forEach items="${groupValues}" var="group" varStatus="status">
                                    <c:choose>
                                        <c:when test="${group == drink.group}">
                                            <option value="${group}" selected="true">${group}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${group}">${group}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
    <%--                                <form:option value="${drink.group}"/>--%>
    <%--                                <form:options items="${groupValues}"/>--%>
                            </form:select>
                    </td>
                </tr>
                <c:if test = "${drink['class'].simpleName == 'NonAlcoholicDrink'}">
                <tr class = "composition-outer">
                    <td class = "first composition-inner">Composition</td>
                    <td class = "second composition-inner"><textarea name = "composition" class = "edit-form" rows = "10" cols="45">${drink.composition}</textarea></td>
                </tr>
                </c:if>
                <c:if test = "${drink['class'].simpleName == 'AlcoholicDrink'}">
                <tr class = "abv-outer">
                    <td class = "first abv-inner">ABV</td>
                    <td class = "second abv-inner"><input type="number" name = "ABV" class = "edit-form" min = "0" max="100" step="0.1" value="${drink.ABV}"></td>
                </tr>
                </c:if>
            </table>
            <div id="edit-button">
                <button type="submit" id = "edit-submit">Save</button>
            </div>
        </form:form>

    </div>
</div>
</body>
</html>
