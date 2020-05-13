$(function () {
    $("#modal1").modal({
        onCloseEnd: function() {
            console.log('modal closed');
        }
    });
})

