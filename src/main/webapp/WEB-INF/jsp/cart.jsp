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
    <script>
        $(document).ready(function(){
            $(".edit-count").on("click", function(){
                var $this = $(this);
                var $quantity = $this.closest('div').find('input');
                var quantity = parseInt($quantity.val());
                if ($this.hasClass("minus-btn")) quantity = minusOne(quantity);
                if ($this.hasClass("plus-btn")) quantity = plusOne(quantity);
                $quantity.val(quantity);
                var $parent = $this.closest('div').parent();
                var $price = $parent.find('.productPrice').text();
                var price = parseFloat($price);
                $parent.find('.fullPrice').text(price * quantity);
                getCount();
                getSum();
            });

        });
        $(document).ready(function(){
            priceofeveryunitfill();
            getSum();
            getCount();
        });
    </script>
    <script>
        function plusOne(a) {
            if (a < 100)
                return a + 1;
            else return 100;
        }
        function minusOne(a) {
            if (a > 0)
                return a - 1;
            else return 0;
        }
        function priceofeveryunitfill()
        {
            //var elem = document.getElementsByClassName("fullPrice");
            //for (var i = 0; i < elem.length; ++ i) {
                     //sum += parseInt(elem[i].item().find("span").first().textContent);


            $elements = $(".fullPrice");
            $elements.each(function () {
                var $current = $(this);
                var $parent = $current.parent();
                var $quantity = $parent.find(".quantity input").val();

                var quantity = parseInt($quantity);
                    var $price = $parent.find('.productPrice').text();
                    var price = parseFloat($price);
                    $current.text(quantity * price);
            }
        );
        }
    </script>
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

        <div class="description productName">
            <span>${drink.name}</span>
        </div>

        <div class="description productPrice">
            <span>${drink.purchasePrice}</span>
        </div>

        <div class="quantity" name = "test">
            <button class="minus-btn edit-count" type="button" name="button">
                <img src="static/img/minus-5-16.png" alt="" />
            </button>
            <input class = "myInput" type="text" name="name" value="1">
            <button class="plus-btn edit-count" type="button" name="button" >
<%--                    //onclick=plusOne(this)>--%>
                <img src="static/img/plus-5-16.png" alt="" />
            </button>
        </div>

        <div class="description fullPrice">
            <span></span>
        </div>
    </div>
    </c:forEach>

<%--    <script type="text/javascript">--%>
<%--        var x = document.getElementsByClassName("xxx");--%>
<%--        for(var i = 0; i < x.length; i++) {--%>
<%--            x[i].value = "22$";--%>
<%--        }--%>
<%--    </script>--%>

    <script>
        function getCount(){
            const field = document.getElementById('finalCount');
            // Selecting the input element and get its value
            var elem = document.getElementsByClassName("myInput");
            var sum = 0;
            for (var i = 0; i < elem.length; ++ i) {
                sum += parseInt(elem[i].value);
            }
            field.textContent = "Всего заказано " + sum + "товаров";
        }
    </script>
    <script>
        function getSum(){
            const field = document.getElementById('finalPrice');
            // Selecting the input elements and summing their values
            $elements = $(".fullPrice");
            //var elem = document.getElementsByClassName("fullPrice");
            var sum = 0;
            $elements.each(function () {
                sum += parseFloat($(this).text());
            });
            //  for (var i = 0; i < elem.length; ++ i) {
            //     sum += parseInt(elem[i].item().find("span").first().textContent);
            //  }
            // Displaying the value
            field.textContent = "Общая сумма: " + sum;
        }
    </script>

    <div><span id = "finalCount"></span></div>
    <div><span id = "finalPrice"></span></div>

</div>
</body>
</html>
