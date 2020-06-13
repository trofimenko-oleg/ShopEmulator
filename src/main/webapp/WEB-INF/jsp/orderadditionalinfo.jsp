<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 10.06.2020
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add contact information</title>
    <spring:url value="resources/css/style.css" var="mainCss"/>
    <link href="${mainCss}" rel="stylesheet"/>

</head>
<body>
        <form method="post" modelAttribute="info" name = "submit" action="save_additional_order_info/${id}">
            <span>Thank you for order in our shop, your order number is №${id}. Please leave us your contacts and shipping info in the field below</span>
            <input type="text" name = "info">
            <button type="submit" name="submit_button"> S e n d </button>
        </form>

</body>
</html>
