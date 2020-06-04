function minusOne() {

    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value &gt; 1) {
        value = value - 1;
    } else {
        value = 0;
    }

    $input.val(value);

}

function plusOne() {

    var input = $(this).closest('div').find('input');
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