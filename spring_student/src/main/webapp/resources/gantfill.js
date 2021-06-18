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

var jsonParams = [{
        desc: "Инструктаж по технике безопасности. Знакомство с организацией",
        values: [{
            from: new Date(2021, 1, 12),
            to: new Date(2021, 1, 13),
            label: "Инструктаж по технике безопасности. Знакомство со структурой организации",
        }]
    }, {
        desc: "Знакомство с проектной документацией и устройством компании.",
        values: [{
            from: new Date(2021, 1, 15),
            to: new Date(2021, 1, 16),
            label: "Знакомство с проектной документацией и устройством компании."
        }]
    }, {
        desc: "Изучение скриптов, имеющихся у компании.",
        values: [{
            from: new Date(2021, 1, 17),
            to: new Date(2021, 1, 19),
            label: "Изучение скриптов, имеющихся у компании."
        }]
    }, {
        desc: "Написание методов визуализации системного времени и среднего значения загрузки системы за заданный период времени.",
        values: [{
            from: new Date(2021, 1, 20),
            to: new Date(2021, 1, 25),
            label: "Написание методов визуализации системного времени и среднего значения загрузки системы за заданный период времени."
        }]
    }]
;

$(function () {
    $.ajax({
        type: "POST",
        url: window.location.pathname + "\/1",
        success: function (result) {
            console.log(result)

            jsonParams = result;
            console.log(jsonParams)

            $(".gantt").gantt({
                source: jsonParams,
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

        },

        error: function (e) {
            console.log(e);
        }
    })
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

