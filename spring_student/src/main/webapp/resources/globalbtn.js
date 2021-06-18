$(document).ready(function () {
    var url = "http://localhost:8089/student_manager_war/"

    $("#savestudents").click(function (e) {
        e.preventDefault();
        $("#btnsavestudents").submit();
    });

    $("#add-task-btn").click(function (e) {
        //$(location).attr('href');
        location.href = window.location.pathname + "/addTask";
        //console.log(window.location.pathname);
    });

    $("#getGant").click(function (e) {
        //$(location).attr('href');
        location.href = window.location.pathname + "/gant";
        //console.log(window.location.pathname);
    });

    $("#edit-btn").click(function (e) {
        //$(location).attr('href');
        location.href = window.location.pathname + "/editTask";
        //console.log(window.location.pathname);
    });

    $("#btn-edit-task").click(function (e) {
        e.preventDefault();
        $("#edit-task-form").submit();
    });

    $("#btn-edit-practice").click(function (e) {
        e.preventDefault();
        $("#edit-practice-form").submit();
    });


    $("#delete-task-btn").click(function (e) {
        //$(location).attr('href');
        location.href = window.location.pathname + "/addStudent";
        //console.log(window.location.pathname);
    });

    /* Выход из учётной записи */
    $("#logout").click(function(e){
        e.preventDefault();
        console.log("elem")
        $("#logout-form").submit();
    });
});

function goToTask(id) {
    var str = "/" + id + "/tasks"
    console.log(str)
    location.href = window.location.pathname + str;
}

function checkInput(text) {

    if (text) {
        $("#clearBtn1").addClass("show");
    } else {
        $("#clearBtn1").removeClass("show");
    }

}