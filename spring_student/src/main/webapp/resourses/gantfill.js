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

var demoSource = [{
    desc: "Инструктаж",
    values: [{
        from: new Date(2021, 0, 18),
        to: new Date(2021, 0, 18),
        label: "Инструктаж по технике безопасности. Знакомство со структурой организации",
        customClass: "ganttGreen"
    }]
}, {
    desc: "Проектирование",
    values: [{
        from: new Date(2021, 0, 19),
        to: new Date(2021, 0, 21),
        label: "Проектирование приложения, написание технического задания"
    }]
},  {
    desc: "Сервер",
    values: [{
        from: new Date(2021, 1, 21),
        to: new Date(2021, 1, 25),
        label: "Реализация сервера, основных сервисов, контроллеров и моделей"
    }]
}];

    $(function () {
        //Написать Ajax запрос на получение данных и из обработку


    $(".gantt").gantt({
        source: demoSource,
        navigate: "scroll",
        scale: "days",
        maxScale: "weeks",
        minScale: "days",
        months: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
        dow: ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"],
        customClass: "ganttRed",
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

//
// $(".gantt").gantt({
//     // or 'data/data.json'
//     source: myData
// });
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

