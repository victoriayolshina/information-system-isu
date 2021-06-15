window.onload = function () {
    setMinDateInDateInput()

}

function deleteTask(taskId){


    $.ajax({
        type : "DELETE",
        url: window.location.pathname + "\/" + taskId,
        success: function (result) {
            console.log(result)
            var tbody = document.getElementById("taskstbody")
            var row = document.getElementById("taskRow" + taskId)
            tbody.removeChild(row)
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function setMinDateInDateInput(){
    var dateStart = document.getElementById("Datastart")
    var dateEnd = document.getElementById("Dataend")
    if(dateStart!=null && dateStart.value!=null) {
        dateEnd.min = dateStart.value;
        if (new Date(dateStart.value) > new Date(dateEnd.value)) {
            dateEnd.value = dateStart.value
        }
    }
}

function editTask(taskId) {
    location.href = window.location.pathname + "\/editTask/" + taskId
}

