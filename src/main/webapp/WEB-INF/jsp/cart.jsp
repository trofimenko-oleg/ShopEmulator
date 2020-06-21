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
    <jsp:include page="fragments/resources.jsp"/>
    <title><spring:message code="page.cart"/></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>
<script type="text/javascript" src="static/js/functions.js"></script>

<c:set var="val1"><spring:message code="message.ordered"/></c:set>
<input id="message-ordered" type="hidden" value="${val1}"/>
<c:set var="val2"><spring:message code="message.items"/></c:set>
<input id="message-items" type="hidden" value="${val2}"/>
<c:set var="val3"><spring:message code="message.fullprice"/></c:set>
<input id="message-fullprice" type="hidden" value="${val3}"/>

<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        <spring:message code="shoppingbag"/>

    </div>
    <form method="post" modelAttribute="order" name = "submit" action="saveOrder">
    <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
        <jsp:useBean id="item" class="com.myshop.service.to.ShortenedOrderItem"/>
        <!-- Товар #<c:out value="${vs.index}"/>> -->


        <div class="item">
            <div class="buttons">
                <a class="delete-btn" href = "cart/remove/${vs.index}"></a>
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
                <input class = "myInput" type="number" max="99" min="0" path="orderItems[${vs.index}].quantity" name="orderItems[${vs.index}].quantity" value="${item.quantity}">
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
            <button type="submit" class="submit"> <spring:message code="button.confirm_order"/> </button>
        </div>
    </form>

</div>
</body>
</html>
