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
        _formdata.data.datasets[0].backgroundColor = _style.backgroundColor;
        _formdata.data.datasets[0].borderColor = _style.borderColor;
    } else {
        _formdata.data.labels = _datalist.namelist;
        var colorList = getColorList(_datalist.namelist);
        _formdata.data.datasets[0].backgroundColor = colorList.backgroundColor;
        _formdata.data.datasets[0].borderColor = colorList.borderColor;
    }

    _formdata.data.datasets[0].data = _datalist.scorelist;
    _formdata.data.datasets[0].label = _style.label;

    _formdata.type = _style.type;

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
    return {
        'teamlist': teamlist,
        'namelist': namelist,
        'scorelist': scorelist
    }
}