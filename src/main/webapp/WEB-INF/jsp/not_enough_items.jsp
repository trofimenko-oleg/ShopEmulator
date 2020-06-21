<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 13.06.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Sorry, we don't have enough items</title>
<%--    <spring:url value="resources/css/style.css" var="mainCss"/>--%>
<%--    <link href="${mainCss}" rel="stylesheet"/>--%>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>

<div class="shopping-cart additional-info message"> Sorry, we don't have enough items in our store, you can edit your order or come tomorrow, we are refilling our store every day. Thank you for understanding</div>
<div> <div>
    <form action="shop">
        <button type="submit" id="to-main-page-button">To main page</button>
    </form>
</div></div>


</body>
</html>
