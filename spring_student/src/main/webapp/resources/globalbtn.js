$(document).ready(function () {
    var url = "http://localhost:8089/student_manager_war/"

    $("#saveFaculty-form").click(function (e) {
        e.preventDefault();
        $("#btnSaveFaculty").submit();
    });

    $("#saveCurator-form").click(function (e) {
        e.preventDefault();
        $("#btnSaveCurator").submit();
    });

    // $("#btnSaveFaculty").click(function (e) {
    //     e.preventDefault();
    //     $("#saveFaculty-form").submit();
    // });

    $("#btn-add").click(function (e) {
        e.preventDefault();
        $("#add-task-form").submit();
    });

    $("#add-task-btn").click(function (e) {
        //$(location).attr('href');
        location.href = window.location.pathname + "/new";
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

    $("#btn-extra-inform").click(function (e) {
        e.preventDefault();
        $("#form-extra-information").submit();
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
    $("#logout").click(function (e) {
        e.preventDefault();
        console.log("elem")
        $("#logout-form").submit();
    });
});

function goToTask(id) {
    var str = "/" + id
    location.href = window.location.pathname + str;
}

function checkInput(text) {
    if (text) {
        $("#clearBtn1").addClass("show");
    } else {
        $("#clearBtn1").removeClass("show");
    }
}

function openTasks() {
    location.href = window.location.href+"/tasks";
}