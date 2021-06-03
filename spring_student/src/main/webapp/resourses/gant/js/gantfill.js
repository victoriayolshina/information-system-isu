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

$(document).ready(function () {
    $(".gantt").gantt({
        // or 'data/data.json'
        source: myData
    });
});