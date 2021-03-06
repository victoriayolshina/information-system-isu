$(document).ready(function () {
    var url = "http://localhost:8089/student_manager_war/"


    $("#saveCurator-form").click(function (e) {
        e.preventDefault();
        $("#btnSaveCurator").submit();
    });

    $("#savestudentCase").click(function (e) {
        e.preventDefault();
        $("#btnsavestudentCase").submit();
    });

    $("#savestudentFacultyEdit").click(function (e) {
        e.preventDefault();
        $("#btnsavestudentsInfo").submit();
    });


    $("#btn-add").click(function (e) {
        e.preventDefault();
        $("#add-task-form").submit();
    });

    $("#add-task-btn").click(function (e) {
        //$(location).attr('href');
        location.href = getPath(window.location.pathname, "new")
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

    $("#add-faculty-formbtn").click(function (e) {
        e.preventDefault();
        $("#add-faculty-form").submit();
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
    location.href = window.location.href + "/tasks";
}

function goToAddInformation() {
    location.href = window.location.href + "information";
}

function goToStudents(id) {
    location.href = getPath(window.location.href, id) + "/students";
}

function goToSupervisors() {
    location.href = window.location.href + "/supervisor";
}

function goToPractics() {
    location.href = window.location.href + "practice";
}

function addGroup() {
    location.href = getPath(window.location.href, "new");
}

function getPath(path, templ) {
    if (path[path.length - 1] == "/") {
        path = path + "" + templ
    } else {
        path = path + "/" + templ
    }
    return path
}