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
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#type-choose").change(function () {
                var selectedOption = $('#type-choose').val();
                if (selectedOption != ''){
                    window.location.replace('drink/saveNew?type=' + selectedOption);
                }
            });
        });
    </script>
    <title><spring:message code="product.edit"/></title>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>
<div id="edit-form">
    <div class="edit-drink">
            <c:if test = "${type == 'nonalcoholic' || param.type == 'nonalcoholic'}"><c:set var="action" value="drink/saveNonAlcoholic"/></c:if>
            <c:if test = "${type == 'alcoholic' || param.type == 'alcoholic'}"><c:set var="action" value="drink/saveAlcoholic"/></c:if>
        <form:form method="post" modelAttribute="drink" action="${action}">
            <table>
                <input type="hidden" name="id" value="${drink.id}">
                <c:if test="${drink.id == null}">
                <tr>
                    <td class = "first"><spring:message code="product.isalc"/></td>
                    <td class = "second">
                        <c:if test="${drink.id == null}">
                            <select name="type" id="type-choose">
                                <option value=""></option>
                                <c:choose>
                                    <c:when test="${param.type == 'alcoholic'}">
                                        <option value="alcoholic" selected="true"><spring:message code="alcoholic"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="alcoholic"><spring:message code="alcoholic"/></option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${param.type == 'nonalcoholic'}">
                                        <option value="nonalcoholic" selected="true"><spring:message code="nonalcoholic"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="nonalcoholic"><spring:message code="nonalcoholic"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </c:if>
                    </td>
                </tr>
                </c:if>
                <tr class = "name-outer">
                    <td class = "first name-inner"><spring:message code="product.name"/></td>
                    <td class = "second name-inner"><input type="text" name = "name" class = "edit-form" value="${drink.name}"></td>
                </tr>
                <tr class = "price-outer">
                    <td class = "first price-inner"><spring:message code="product.price"/></td>
                    <td class = "second price-inner"><input type="number" name = "purchasePrice" class = "edit-form" min="0" step="0.01" value="${drink.purchasePrice}"></td>
                </tr>
                <tr class = "volume-outer">
                    <td class = "first volume-inner"><spring:message code="product.volume"/></td>
                    <td class = "second volume-inner"><input type="number" name = "volume" class = "edit-form" min = "0" max="100" step="0.01" value="${drink.volume}"></td>
                </tr>
                <tr class = "quantity-outer">
                    <td class = "first quantity-inner"><spring:message code="product.quantity"/></td>
                    <td class = "second quantity-inner"><input type="number" name = "quantity" class = "edit-form" min = "0" step="1" value="${drink.quantity}"></td>
                </tr>
                <tr class = "type-outer">
                    <td class = "first type-inner"><spring:message code="product.type"/></td>
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
                            </form:select>
                    </td>
                </tr>



                <c:if test = "${type == 'nonalcoholic' || param.type == 'nonalcoholic'}">
                <tr class = "composition-outer">
                    <td class = "first composition-inner"><spring:message code="product.composition"/></td>
                    <td class = "second composition-inner"><textarea name = "composition" class = "edit-form" rows = "10" cols="45">${drink.composition}</textarea></td>
                </tr>
                </c:if>
                <c:if test = "${type == 'alcoholic' || param.type == 'alcoholic'}">
                <tr class = "abv-outer">
                    <td class = "first abv-inner"><spring:message code="product.ABV"/></td>
                    <td class = "second abv-inner"><input type="number" name = "ABV" class = "edit-form" min = "0" max="100" step="0.1" value="${drink.ABV}"></td>
                </tr>
                </c:if>
            </table>
            <div id="edit-button">
                <button type="submit" id = "edit-submit"><spring:message code="product.save"/></button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
