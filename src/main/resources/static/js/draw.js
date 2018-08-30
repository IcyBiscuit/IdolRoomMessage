// function success(_result) {
//     if (_result.code === '0') {
//
//         $('#time').text("更新时间: " + new Date())
//
//         drawchar('top16', _result.scoreList.slice(0, 15), {
//             "type": "horizontalBar",
//             "backgroundColor": "rgba(255, 99, 132, 0.2)",
//             "borderColor": "rgba(255,99,132,1)"
//         })
//
//
//         drawchar('top32', _result.scoreList.slice(16, 31), {
//             "type": "horizontalBar",
//             "backgroundColor": "rgba(255, 206, 86, 0.2)",
//             "borderColor": "rgba(255, 206, 86, 1)"
//         })
//
//
//         drawchar('all', _result.scoreList.slice(32), {
//             "type": "bar",
//             "backgroundColor": "rgba(75, 192, 192, 0.2)",
//             "borderColor": "rgba(75, 192, 192, 1)"
//         })
//
//     }
// }


function drawchar(nodeid, data, _style) {

    var _formdata = {
        "type": "",
        "data": {
            "labels": [],
            "datasets": [
                {
                    "label": [],
                    "data": [],

                    "backgroundColor": "",
                    "borderColor": "",

                    "borderWidth": 1
                }
            ]
        },
        "options": {}
    };

    var _datalist = obj2array(data);

    var ctx = $("#" + String(nodeid)).get(0).getContext('2d');

    if (nodeid == 'team') {
        _formdata.data.labels = _datalist.teamlist;
    } else {
        _formdata.data.labels = _datalist.namelist;
    }

    _formdata.data.datasets[0].data = _datalist.scorelist;
    _formdata.data.datasets[0].label = _style.label;

    _formdata.type = _style.type;
    _formdata.data.datasets[0].backgroundColor = _style.backgroundColor;
    _formdata.data.datasets[0].borderColor = _style.borderColor;
    _formdata.options = _style.option;

    var myChar = new Chart(ctx, _formdata);
}

function obj2array(obj) {
    var teamlist = [];
    var namelist = [];
    var scorelist = [];

    obj.forEach(function (element) {

        teamlist.push(element.team);

        namelist.push(element.name);
        scorelist.push(element.counts);
    });
    // return [teamlist,namelist, scorelist]
    return {
        'teamlist': teamlist,
        'namelist': namelist,
        'scorelist': scorelist
    }
}