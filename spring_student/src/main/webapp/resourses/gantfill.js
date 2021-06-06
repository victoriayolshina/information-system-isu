var myData = [{
    name: "Name 1",
    desc: "Description 1",
    values: [{
        from: 1320192000000, // <a href="https://www.jqueryscript.net/time-clock/">date</a> string
        to: 1322401600000,
        label: "Label 1",
        desc: "Value Description 1",
        customClass: "custom-1",
        dataObj: {}
    }]
}, {
    name: "Name 2",
    desc: "Description 2",
    values: [{
        from: 1320192000000,
        to: 1322401600000,
        label: "Label 2",
        desc: "Value Description 2",
        customClass: "custom-2",
        dataObj: {}
    }]
}, {
    name: "Name 3",
    desc: "Description 3",
    values: [{
        from: 1320192000000,
        to: 1322401600000,
        label: "Label 3",
        desc: "Value Description 3",
        customClass: "custom-3",
        dataObj: {}
    }]

}];
// console.log(myData)
var demoSource = [{
    desc: "Инструктаж",
    values: [{
        from: new Date(2021, 0, 18),
        to: new Date(2021, 0, 18),
        label: "Инструктаж по технике безопасности. Знакомство со структурой организации"
    }]
}, {
    desc: "Проектирование",
    values: [{
        from: new Date(2021, 0, 19),
        to: new Date(2021, 0, 21),
        label: "Проектирование приложения, написание технического задания"
    }]
}, {
    desc: "Сервер",
    values: [{
        from: new Date(2021, 1, 21),
        to: new Date(2021, 1, 25),
        label: "Реализация сервера, основных сервисов, контроллеров и моделей"
    }]
}];


let myJson;

$(function () {
    $.ajax({
        type: "POST",
        url: window.location.pathname + "\/1",
        success: function (result) {
            // console.log(result[1].desc);
            // console.log(result[1].values[0].label);
            // console.log(result[1].values[0].from);
            // console.log(result[1].values[0].to);

            // var params = [];
            // /* Парсинг возвращённого массива */
            // for(var i=0; i<result.length; i++){
            //     params[i]= []
            //     params[i][0]= result[i].desc;
            //     params[i][1]= result[i].values[0].from;
            //     params[i][2]= result[i].values[0].to;
            //     params[i][3]= result[i].values[0].label;
            // }

            // console.log(params);
            //
            // var json = JSON.parse(params);
            // myJsonString = JSON.stringify(result);
            console.log(result);
            myJson = getJson(result, myJson);
            // myJson = result;
            console.log(myJson);
        },
        error: function (e) {
            console.log(e);
        }
    })

    // arrayJson = [{
    //     desc: "Изучение Spring.",
    //     values: [{from: myJson[1].values[0].from, to: myJson[1].values[0].to, label: myJson[1].values[0].label}]
    // }]
    // }, {
    //     "name": "Изучение Spring.",
    //     "desc": "Заполнение дневника практики.",
    //     "values": [{"from": 1613059200000, "to": 1613836800000, "label": "Заполнение дневника практики."}]
    // }, {
    //     "desc": "Инструктаж по технике безопасности. Знакомство с организацией.",
    //     "values": [{
    //         "from": 1613145600000,
    //         "to": 1612800000000,
    //         "label": "Инструктаж по технике безопасности. Знакомство с организацией."
    //     }]
    // }, {
    //     "desc": "Изучение JAVA.",
    //     "values": [{"from": 1614355200000, "to": 1613404800000, "label": "Изучение JAVA."}]
    // }, {
    //     "desc": "Изучение JAVA.",
    //     "values": [{"from": 1615996800000, "to": 1616083200000, "label": "Изучение JAVA."}]
    // }, {
    //     "desc": "Изучение JAVA.", "values": [{"from": 1616169600000, "to": 1614960000000, "label": "Изучение JAVA."}]}]
    // console.log(arrayJson)


    $(".gantt").gantt({
        source: myJson,
        navigate: "scroll",
        scale: "days",
        maxScale: "weeks",
        minScale: "days",
        months: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
        dow: ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"],
        customClass: "ganttRed",
        waitText: "Пожалуйста, подождите...",
        itemsPerPage: 10,
        scrollToToday: false,
        useCookie: false,
        onItemClick: function (data) {
            console.log(data);
        },
        onRender: function () {
            if (window.console && typeof console.log === "function") {
                console.log("chart rendered");
            }
        }
    });

    // $(".gantt").gantt({
    //     // or 'data/data.json'
    //     source: myJson
    // });

    $(".gantt").popover({
        selector: ".bar",
        title: function _getItemText() {
            return this.textContent;
        },
        container: '.gantt',
        trigger: "hover",
        placement: "auto right"
    });


});

function getJson(arrayJson, returnJson) {
    returnJson = [{
        desc: "Изучение Spring.",
        values: [{from: arrayJson[1].values[0].from, to: arrayJson[1].values[0].to, label: arrayJson[1].values[0].label}]
    }]
    return returnJson;
}

//
// $(".gantt").gantt({
//
//     // holidays
//     holidays: [],
//
//     // how many items per page
//     itemsPerPage: 7,
//
//     // localisation
//     dow: ["S", "M", "T", "W", "T", "F", "S"],
//     months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
//     waitText: "Please wait...",
//
//     // navigation type
//     // or 'scroll'
//     navigate: "buttons",
//
//     // auto scrolls to today
//     scrollToToday: true,
//
//     // uses cookie to save the current state
//     // requires jquery-cookie plugin: https://github.com/carhartl/jquery-cookie
//     useCookie: false,
//     cookieKey: "jquery.fn.gantt",
//
//     // scale parameters
//     scale: "days",
//     maxScale: "months",
//     minScale: "hours",
// });
//
// $(".gantt").gantt({
//
//     onItemClick: function (data) {
//         return;
//     },
//     onAddClick: function (dt, rowId) {
//         return;
//     },
//     onRender: $.noop
//
//
// });

