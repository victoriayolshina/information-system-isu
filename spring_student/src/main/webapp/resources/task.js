// var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
// var csrfToken = $("meta[name='_csrf']").attr("content");
// var csrfHeader = $("meta[name='_csrf_header']").attr("content");

window.onload = function () {
    setMinDateInDateInput()
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

// $(document).ready(function(){
//
//     $(".deleteLesson").click(function(e){
//         e.preventDefault();
//         var id = e.target.id;
//         var path = root + "lessons/" + id.substr(6) + "/delete" ;
//
//         $.post(path, "", function (data) {
//             var tr = e.target.closest("tr");
//             $(tr).children().eq(2).html("canceled");
//             $(tr).children().eq(3).html("&mdash;");
//             $(tr).children().eq(4).html("");
//         });
//     });
//
// });

function deleteTask(taskId) {
    var path = window.location.pathname + "/" + taskId;

    $.ajax({
        type: "DELETE",
        url: path,
        success: function (result) {
            var tbody = document.getElementById("taskstbody")
            var row = document.getElementById("taskRow" + taskId)
            tbody.removeChild(row)
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function setMinDateInDateInput() {
    var dateStart = document.getElementById("Datastart")
    var dateEnd = document.getElementById("Dataend")
    if (dateStart != null && dateStart.value != null) {
        dateEnd.min = dateStart.value;
        if (new Date(dateStart.value) > new Date(dateEnd.value)) {
            dateEnd.value = dateStart.value
        }
    }
}

function editTask(taskId) {
    var loc = window.location.pathname
    if (loc[loc.length - 1] == "/") {
        location.href = window.location.pathname + "" + taskId
    } else {
        location.href = window.location.pathname + "/" + taskId
    }
}

