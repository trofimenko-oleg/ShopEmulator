<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 02.06.2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<hr>

<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Volume</th>
        <th>Left, pc</th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${drinks}" var="drink">
        <jsp:useBean id="drink" scope="page" type="com.myshop.domain.Drink"/>
        <tr>
            <td>${drink.name}</td>
            <td>${drink.purchasePrice}</td>
            <td>${drink.volume}</td>
            <td>${drink.quantity}</td>
            <td><a href="shop/delete?id=${drink.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
