var teamColor = {
    'G': {
        "backgroundColor": "rgba(75, 192, 192, 0.2)",
        "borderColor": "rgba(75, 192, 192, 1)"
    },
    'NIII': {
        "backgroundColor": "rgba(255, 206, 86, 0.2)",
        "borderColor": "rgba(255, 206, 86, 1)"
    },
    'Z': {
        "backgroundColor": "rgba(255, 99, 132, 0.2)",
        "borderColor": "rgba(255,99,132,1)"
    },
    '预备生': {
        "backgroundColor": "rgba(54, 162, 235, 0.2)",
        "borderColor": "rgba(54, 162, 235, 1)"
    }
};


function buildFormresult(result) {

    var formdata = {
        "type": "line",
        "data": {
            "labels": [],
            "datasets": []
        },
        "options": {}
    };

    for (var team in result) {

        var parsed = parseObj(result[team]);

        formdata.data.labels = parsed.dates;
        formdata.data.datasets.push({

            "label": team,
            "data": parsed.counts.slice(0, -1),

            // "backgroundColor": teamColor[team]['backgroundColor'],
            "backgroundColor": "rgba(240,240,240,0.2)",

            "borderColor": teamColor[team]['borderColor'],

            "borderWidth": 3.5
        })
    }
    return formdata;
}


function drawMsgDaysCountsLine(nodeid, data) {
    var ctx = $("#" + String(nodeid)).get(0).getContext('2d');
    var myChar = new Chart(ctx, data);
}

function parseObj(obj) {
    var dates = [];
    var counts = [];
    obj.forEach(function (each) {
        dates.push(each['date']);
        counts.push(each['counts'])
    });

    return {
        'dates': dates,
        'counts': counts
    }
}