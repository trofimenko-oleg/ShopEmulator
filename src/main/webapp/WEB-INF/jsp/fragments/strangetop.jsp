<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="top-block">
    <div class = "half">
        <form action="shop">
        <button class="strange-button" type="submit"><spring:message code="to-shop"/></button>
        </form>
    </div>
    <div class = "half">
        <form action="drink/list">
        <button class="strange-button" type="submit"><spring:message code="to-drinks-management"/></button>
        </form>
    </div>
</div>


