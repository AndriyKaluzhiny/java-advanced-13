var buckets;

$.get("buckets", function(data) {
    if (data !== '') {
        buckets = data;
    }
}).done(function() {

    var tableContent = "<tr class='header table-dark'>" +
        "<th style='width: 20%;'>Name</th>" +
        "<th style='width: 20%;'>Description</th>" +
        "<th style='width: 20%;'>Price</th>" +
        "<th style='width: 20%;'>PurchaseDate</th>" +
        "<th style='width: 20%;'>Options</th>" +
        "</tr>";

    jQuery.each(buckets, function(i, value) {

        tableContent += "<tr>" +
            "<td>" + value.name + "</td>" +
            "<td>" + value.description + "</td>" +
            "<td>" + value.price + "</td>" +
            "<td>" + value.purchaseDate + "</td>" +
            "<td><button class='deleteBtn' onclick='deleteOrderFromBucket(" + value.bucketId + ")'>delete</button></td > " +
            "</tr>"
    });

    $('#table').html(tableContent);

});

function deleteOrderFromBucket(bucketId) {
    let customUrl = '';
    let urlContent = window.location.href.split('/');
    for (let i = 0; i < urlContent.length - 1; i++) {
        customUrl += urlContent[i] + '/';
    }
    customUrl += 'bucket?bucketId=' + bucketId;

    $.ajax({
        url: customUrl,
        type: 'DELETE',
        success: function(data) {
            if (data == 'Success') {
                location.reload();
            }
        }
    });
}