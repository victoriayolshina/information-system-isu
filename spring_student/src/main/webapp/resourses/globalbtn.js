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

    $("#btn-edit").click(function (e) {
        e.preventDefault();

        $("#edit-task-form").submit();
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


    /* Обработчик кнопки формы регистрации */
    $("#btn-registr").click(function (e){
        e.preventDefault()
        $("#register-form").submit();
    });
});