$(document).ready(function(){
    $('input[name$="quantity"]').on('input',function(){
        $this = $(this);
        var maxQuantity = $this.closest(".shop_item").find('td[name^="quantity"]').text();
        var minimum = min(maxQuantity, 99);
        if ($this.val() > minimum) $this.val(minimum);
    });
});

function min(a, b){
    if (parseInt(a, 10) < parseInt(b, 10)) return parseInt(a, 10);
    else return parseInt(b,10);
}


