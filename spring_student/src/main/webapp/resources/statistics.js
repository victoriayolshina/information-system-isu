$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


});

function setMinDateInStatistics() {
    var dateStart = document.getElementById("from")
    var dateEnd = document.getElementById("to")
    if (dateStart != null && dateStart.value != null) {
        dateEnd.min = dateStart.value;
        if (new Date(dateStart.value) > new Date(dateEnd.value)) {
            dateEnd.value = dateStart.value
        }
    }
}


function sendRequest() {
    var _from = document.getElementById("from");
    var _to = document.getElementById("to");

    if(_from.value != null && _to.value!=null) {
        $.ajax({
            type: "POST",
            data: {from: _from.value, to: _to.value},
            url: window.location.pathname,
            success: function (result) {
                console.log(result.categories)
                console.log(result.statistics)
                Highcharts.chart('container', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Статистика направлений по годам'
                    },
                    xAxis: {
                        categories: result.categories.categories,
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Количество'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    series: result.statistics
                });

            },
            error: function (e) {
                console.log(e);
            }
        })

    }
}

// Highcharts.chart('container', {
//     chart: {
//         type: 'column'
//     },
//     title: {
//         text: 'Monthly Average Rainfall'
//     },
//     subtitle: {
//         text: 'Source: WorldClimate.com'
//     },
//     xAxis: {
//         categories: [
//             'Jan',
//             'Feb',
//             'Mar',
//             'Apr',
//             'May',
//             'Jun',
//             'Jul',
//             'Aug',
//             'Sep',
//             'Oct',
//             'Nov',
//             'Dec'
//         ],
//         crosshair: true
//     },
//     yAxis: {
//         min: 0,
//         title: {
//             text: 'Rainfall (mm)'
//         }
//     },
//     tooltip: {
//         headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
//         pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
//             '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
//         footerFormat: '</table>',
//         shared: true,
//         useHTML: true
//     },
//     plotOptions: {
//         column: {
//             pointPadding: 0.2,
//             borderWidth: 0
//         }
//     },
//     series: [{
//         name: 'Tokyo',
//         data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
//
//     }, {
//         name: 'New York',
//         data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]
//
//     }, {
//         name: 'London',
//         data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]
//
//     }, {
//         name: 'Berlin',
//         data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]
//     }]
// });