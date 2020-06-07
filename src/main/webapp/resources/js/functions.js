//calculates total order sum
function getSum(){
    const field = document.getElementById('finalPrice');
    // Selecting the input elements and summing their values
    $elements = $(".fullPrice");
    var sum = 0;
    $elements.each(function () {
        sum += parseFloat($(this).text());
    });
    // Displaying the value
    field.textContent = "Общая сумма: " + round(sum);
}

//rounds to 2 digits after point
function round(a) {
    if (typeof a === "string") return Math.round(parseFloat(a) * 100) / 100;
    else if (typeof a === "number") return Math.round(a*100)/100;
}

//how many different products were added
function getCount(){
    const field = document.getElementById('finalCount');
    // Selecting the input element and get its value
    var elem = document.getElementsByClassName("myInput");
    var sum = 0;
    for (var i = 0; i < elem.length; ++ i) {
        sum += parseInt(elem[i].value);
    }
    field.textContent = "Всего заказано " + sum + " товаров";
}

//max quantity = 99
function plusOne(a) {
    if (a < 99)
        return a + 1;
    else return 99;
}

//doesn't allow to go under 0
function minusOne(a) {
    if (a > 0)
        return a - 1;
    else return 0;
}


function fullPriceOfOneItemFill(element){
    var $current = $(element);
        var $item = $current.closest(".item");
        var $quantity = $item.find(".quantity input").val();
        var quantity = parseInt($quantity);
            var $price = $item.find('.productPrice').text();
        $itemDiscountPrice = $item.find(".priceWithDiscount");
        if ($itemDiscountPrice.css('visibility') != 'hidden')
        {
            $price = $itemDiscountPrice.text();
        }
        var price = parseFloat($price);
        $item.find(".fullPrice").text(round(quantity * price));
}

//calculates price*quantity for every kind of product added
function everyItemFullPriceFill(){
    $elements = $(".fullPrice");
    $elements.each(function () {
            fullPriceOfOneItemFill($(this));
        }
    );
}

//buttons + and - handler
$(document).ready(function(){
    $(".edit-count").on("click", function(){
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var quantity = parseInt($input.val());
        if ($this.hasClass("minus-btn")) quantity = minusOne(quantity);
        if ($this.hasClass("plus-btn")) quantity = plusOne(quantity);
        $input.val(quantity);
        calculateOrderOnInputChange($input, quantity);

    });
    //calculations on changing quantity field
    $('.myInput').on('input',function(){
        $this = $(this);
        if ($this.val() > 99) $this.val(99);
        if ($this.val() < 0) $this.val(0);
        calculateOrderOnInputChange($(this), $(this).val());
    });
});

function calculateOrderOnInputChange(element, quantity){
    showItemDiscount(element.closest('.item'));
    fullPriceOfOneItemFill(element);
    getCount();
    getSum();
}

$(document).ready(function(){
    showAllDiscounts();
    everyItemFullPriceFill();
    getCount();
    getSum();
});

function getPrice (a, b, c){
    //a - initial price, price if > 2, quantity
    var $finalPrice = 0;
       var $initialPrice = parseFloat(a);
       var $wholesalePrice = parseFloat(b);
       var $quantity = c;

    if (parseInt($quantity) <= 2)
       {
           $finalPrice = $initialPrice*$quantity;
       }
       else if (parseInt($quantity) > 2)
       {
           $finalPrice = $initialPrice*2;
           $finalPrice = $finalPrice + $wholesalePrice*($quantity-2);
       }
    return round($finalPrice);
}

function getItemPrice (a, b, c){
    return round(getPrice(a, b, c) / c);
}

function showAllDiscounts(){
    var $elements = $(".item");
    $elements.each(function () {
        showItemDiscount($(this));
    });
}

function showItemDiscount(element) {
    var $this = $(element);
    var $count = $this.find(".myInput").val();
    if ($count > 2)
    {
        var $priceBeforeDiscount = $this.find(".productPrice").text();
        var $priceWithDiscount = $this.find(".hidden").text();
        var $itemPrice = getItemPrice($priceBeforeDiscount, $priceWithDiscount, $count);
        $this.find(".priceWithDiscount").css("visibility", "visible");
        $this.find(".priceWithDiscount").text($itemPrice);
        $this.find(".productPrice").css("text-decoration", "line-through");
    }
    if ($count <= 2)
    {
        $this.find(".priceWithDiscount").css("visibility", "hidden");
        $this.find(".productPrice").css("text-decoration", "none");
    }
}