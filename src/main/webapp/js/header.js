$.get("user-role", function(data) {
    if (data !== '') {
        userRole = data;
    }
}).done(function() {
    if (userRole == 'ADMINISTRATOR') {
        $('a.bucket').hide();
    }
});