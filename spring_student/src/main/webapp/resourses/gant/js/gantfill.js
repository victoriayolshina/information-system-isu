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


    $(".gantt").gantt({
        // or 'data/data.json'
        source: myData
    });

    $(".gantt").gantt({

        // holidays
        holidays: [],

        // how many items per page
        itemsPerPage: 7,

        // localisation
        dow: ["S", "M", "T", "W", "T", "F", "S"],
        months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        waitText: "Please wait...",

        // navigation type
        // or 'scroll'
        navigate: "buttons",

        // auto scrolls to today
        scrollToToday: true,

        // uses cookie to save the current state
        // requires jquery-cookie plugin: https://github.com/carhartl/jquery-cookie
        useCookie: false,
        cookieKey: "jquery.fn.gantt",

        // scale parameters
        scale: "days",
        maxScale: "months",
        minScale: "hours",
    });

    $(".gantt").gantt({

        onItemClick: function (data) { return; },
        onAddClick: function (dt, rowId) { return; },
        onRender: $.noop


});