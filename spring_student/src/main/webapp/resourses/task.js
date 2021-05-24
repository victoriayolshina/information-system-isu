function deleteTask(taskId){
    //console.log(taskId)
    //var json = {id: taskId} JSON.stringify(json)

    $.ajax({
        type : "DELETE",
        url: window.location.pathname + "\/" + taskId,
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

function editTask(taskId) {
    location.href = window.location.pathname + "\/editTask/" + taskId
}