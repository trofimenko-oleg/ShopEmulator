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
    field.textContent = "Общая сумма: " + Math.round(sum * 100) / 100;
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
    field.textContent = "Всего заказано " + sum + "товаров";
}

//max amount = 9
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

//calculates price*quantity for every kind of product added
function priceofeveryunitfill()
{
    $elements = $(".fullPrice");
    $elements.each(function () {
            var $current = $(this);
            var $parent = $current.parent();
            var $quantity = $parent.find(".quantity input").val();

            var quantity = parseInt($quantity);
            var $price = $parent.find('.productPrice').text();
            var price = parseFloat($price);
            $current.text((Math.round(quantity * price * 100) / 100));
        }
    );
}
//buttons + and - handler
$(document).ready(function(){
    $(".edit-count").on("click", function(){
        var $this = $(this);
        var $quantity = $this.closest('div').find('input');
        var quantity = parseInt($quantity.val());
        if ($this.hasClass("minus-btn")) quantity = minusOne(quantity);
        if ($this.hasClass("plus-btn")) quantity = plusOne(quantity);
        $quantity.val(quantity);
        calculateOrderOnInputChange($quantity, quantity);

    });
    $('.myInput').on('input',function(){
        $this = $(this);
        if ($this.val() > 99) $this.val(99);
        if ($this.val() < 0) $this.val(0);
        calculateOrderOnInputChange($(this), $(this).val());
    });

});

function calculateOrderOnInputChange(element, quantity)
{
    $this = element;
    var $parent = $this.closest('div').parent();
    var $price = $parent.find('.productPrice').text();
    var price = parseFloat($price);
    $parent.find('.fullPrice').text((Math.round(price * quantity * 100) / 100));
    getCount();
    getSum();
}

$(document).ready(function(){
    priceofeveryunitfill();
    getSum();
    getCount();
});