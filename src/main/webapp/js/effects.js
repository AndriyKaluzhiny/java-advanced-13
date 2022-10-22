$('.message a').click(function() {
    $('form').animate({ height: "toggle", opacity: "toggle" }, "slow");
});

$(document).ready(function() {
    $('.leftmenutrigger').on('click', function(e) {
        $('.side-nav').toggleClass("open");
        e.preventDefault();
    });
});

$('#myModal').on('shown.bs.modal', function() {
    $('#myInput').trigger('focus')
})