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
    <title><spring:message code="add-contact-title"/></title>
    <jsp:include page="fragments/resources.jsp"/>
</head>
<body>
    <jsp:include page="fragments/strangetop.jsp"/>
    <div class="shopping-cart additional-info">
        <form method="post" modelattribute="info" name="submit" action="save_additional_order_info/${id}">
            <div class="message"><spring:message code="add-contact-first"/> <span>${id}</span><spring:message code="add-contact-second"/></div>
            <div class="text_field">
                <textarea name="info"></textarea>
            </div>

            <div class="button-info">
                <button type="submit" name="submit_button" id="submit-info"><spring:message code="button.add-contact"/></button>
            </div>

        </form>
    </div>
</body>
</html>
