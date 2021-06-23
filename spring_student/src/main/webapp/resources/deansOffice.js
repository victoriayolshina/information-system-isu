window.onload = function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}


function deleteCurator(id){
    $.ajax({
        type: "DELETE",
        url: getPath(window.location.pathname, id),
        success: function (result) {
            console.log(result)
            var elem = document.getElementById("curator"+id)
            var tableB = document.getElementById("tableBodyAllCurators")
            tableB.removeChild(elem)
        },
        error: function (e) {
            console.log(e);
        }
    })

}