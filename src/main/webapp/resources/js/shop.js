$(document).ready(function(){
    $('input[name*="quantity"]').on('input',function(){
        $this = $(this);
        var maxQuantity = $this.closest(".item").find('td[name*="quantity"]').text();
        var min = min(maxQuantity, 99);
        if ($this.val() > min) $this.val(min);
    });
});

function min(a, b){
    if (a < b) return a;
    else return b;
}
