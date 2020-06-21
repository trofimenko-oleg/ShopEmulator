<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 10.06.2020
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Add contact information</title>
<%--    <spring:url value="resources/css/style.css" var="mainCss"/>--%>
<%--    <link href="${mainCss}" rel="stylesheet"/>--%>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
    <jsp:include page="fragments/strangetop.jsp"/>
    <div class="shopping-cart additional-info">
        <form method="post" modelattribute="info" name="submit" action="save_additional_order_info/${id}">
            <div class="message">Thank you for order in our shop, your order number is <span>${id}</span>. Please leave us your contacts and shipping info in the field below</div>
            <div class="text_field">
                <textarea name="info"></textarea>

            </div>

            <div class="button-info">
                <button type="submit" name="submit_button" id="submit-info"> S e n d </button>
            </div>

        </form>
    </div>
</body>
</html>
