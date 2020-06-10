$(document).ready(function(){
    $('input[name*="quantity"]').on('input',function(){
        $this = $(this);
        var maxQuantity = $this.closest(".item").find('td[name*="quantity"]').text();
        var minimum = min(maxQuantity, 99);
        if ($this.val() > minimum) $this.val(minimum);
    });
});

function min(a, b){
    if (a < b) return a;
    else return b;
}
