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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <spring:url value="resources/css/style.css" var="mainCss"/>
    <link href="${mainCss}" rel="stylesheet"/>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<c:set var = "totalCost" scope = "session" value = "${0}"/>
<%--<script type="text/javascript" src="static/js/counterscript.js"></script>--%>
<%--<script type="text/javascript" src="static/js/functions.js"></script>--%>


<div class="shopping-cart">
    <!-- Title -->
    <div class="title">
        Shopping Bag
    </div>
    <c:forEach items="${drinks}" var="drink">
        <jsp:useBean id="drink" scope="page" type="com.myshop.domain.Drink"/>
        <!-- Товар #1 -->
        <div class="item">
            <div class="buttons">
                <span class="delete-btn"></span>
            </div>

            <div class="description">
                <span>${drink.name}</span>
                <span>${drink.volume}</span>
                <span>${drink.purchasePrice}</span>
            </div>

            <div class="quantity">
                <button class="minus-btn" type="button" name="button" >
<%--                    onclick=minusOne(this)--%>
                    <img src="static/img/minus-5-16.png" alt="" />
                </button>
                <input class = "myInput" type="text" name="name" value="1">
                <button class="plus-btn" type="button" name="button" >
<%--                    onclick=plusOne(this)--%>
                    <img src="static/img/plus-5-16.png" alt="" />
                </button>
            </div>

            <div class="total-price">

                <input type="text" class = "xxx" value="0">


            </div>
        </div>
    </c:forEach>

    <script type="text/javascript">
        var x = document.getElementsByClassName("xxx");
        for(var i = 0; i < x.length; i++) {
            x[i].value = "22$";
        }
    </script>

    <script>
        function getSum(){
            const field = document.getElementById('finalCount');
            // Selecting the input element and get its value
            var elem = document.getElementsByClassName("myInput");
            var sum = 0;
            for (var i = 0; i < elem.length; ++ i) {
                sum += parseInt(elem[i].value);
            }
            //$(this).value = sum;
            field.value = sum;
            // Displaying the value
            //alert(sum);
            // document.getElementById("name").addEventListener("focus", function(){
            //     alert('Hello')
            // });
        }
    </script>
    <script>
        var elements = document.getElementsByClassName("plus-btn");
        for (var i = 0; i < elements.length; i++) {
            elements[i].addEventListener('click', plusOne(elements[i]), false);
        }

        function plusOne(element) {
            alert("I'm TRYING!");
            alert(element.closest('div').find('input').name);
            var input = element.closest('div').find('input');
            alert(input);
            alert(input.value);
            var inputvalue = parseInt(input.value);
            alert(inputvalue);
            if (inputvalue < 100)
            {
                inputvalue = inputvalue + 1;
            }
            else {
                inputvalue =100;
            }

            input.value = inputvalue;
        }
    </script>
    <%--    <script>getSum();</script>--%>

    <div><input id = "finalCount" type="text" value="0" onclick="getSum()"/></div>
    <script>getSum()</script>
</div>
</body>
</html>
