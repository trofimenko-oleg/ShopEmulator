<%--
  Created by IntelliJ IDEA.
  User: bethrezene
  Date: 21.06.2020
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="fragments/resources.jsp"/>
    <title><spring:message code="page.shop"/></title>
</head>
<body>
    <div class="shop">
        <div class="http-error-container" style="text-align: center">
        <h1>HTTP Status 404 - Page Not Found</h1>
        <p class="message-text">The page you requested is not available. You might try returning to the <a href="<c:url value="/"/>">home page</a>.</p>
        </div>
    </div>
</body>
</html>
