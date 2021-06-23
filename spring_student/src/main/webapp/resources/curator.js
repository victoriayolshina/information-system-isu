window.onload = function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}


function addFaculty() {
    var _name = document.getElementById("name").value
    var _direction = document.getElementById("direction").value
    var _year = document.getElementById("year").value
    var _profile = document.getElementById("profile").value

    console.log(_name)
    console.log(_direction)
    console.log(_year)
    console.log(_profile)


    $.ajax({
        type: "POST",
        url: window.location.pathname,
        data:{name: _name, direction: _direction, year: _year, profile: _profile},
        success: function (result) {
            console.log(result)
            location.href = "/student_manager_war"+result
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function deleteFaculty(id){
    $.ajax({
        type: "DELETE",
        url: getPath(window.location.pathname, id),
        success: function (result) {
            console.log(result)
            var elem = document.getElementById("allFaculties"+id)
            var tableB = document.getElementById("tableBodyFaculties")
            tableB.removeChild(elem)
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function goToEditFaculty(id){
    location.href = getPath(window.location.pathname, id)
}

function editStudentFromFaculty(id){
    location.href = getPath(window.location.pathname, id)
}

function deleteStudentFromFaculty(id){
    console.log(getPath(window.location.pathname, id))
    $.ajax({
        type: "DELETE",
        url: getPath(window.location.pathname, id),
        success: function (result) {
            console.log("student"+id)
            var elem = document.getElementById("student"+id)
            var tableB = document.getElementById("tableBodyStudents")
            tableB.removeChild(elem)
        },
        error: function (e) {
            console.log(e);
        }
    })
}




















function getPath(path, templ) {
    if (path[path.length - 1] == "/") {
        path = path + "" + templ
    } else {
        path = path + "/" + templ
    }
    return path
}