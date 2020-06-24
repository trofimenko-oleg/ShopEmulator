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
    <title><spring:message code="notenoughtitle"/></title>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
<jsp:include page="fragments/strangetop.jsp"/>

<div class="shopping-cart additional-info message"><spring:message code="notenoughmessage"/></div>

<div> <div>
    <form action="shop">
        <button type="submit" id="to-main-page-button"><spring:message code="tomainpage"/></button>
    </form>
</div></div>


</body>
</html>
