// this is nginx test

var typeValueMapping = {
    'live': 0,
    'diantai': 1,
    'faipaiText': 2,
    'idolFlip': 3,
    'text': 4,
    'image': 5,
    'audio': 6,
    'videoRecrd': 7
};


function getMemberList() {

    var contextpath = $("#contextpath").val();
    var url = '/ajax/list/member';

    if (contextpath != undefined) {
        url = contextpath + url;
    }

    $.ajax(
        {
            url: url,
            type: "get",
            dataType: 'json',
            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    console.log(result);
                    buildMemberList(result.data);
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e);
            }
        }
    )
}

function getPocketData(roomId) {

    var contextpath = $("#contextpath").val()
    var url = '/ajax/pocketdata';

    if (contextpath != undefined) {
        url = contextpath + url;
    }

    $.ajax(
        {
            url: url,
            type: "post",
            dataType: 'json',
            data: {
                'roomId': roomId
            },

            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    console.log(result);
                    drawData(result.data);
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e);
            }
        }
    )
}

function buildMemberList(result) {
    result.forEach(function (member) {
        var node = member['memberTeam'] + 'MemberList';

        var label = $('<label></label>').attr('class', 'mdui-radio');
        var input = $('<input>').attr({
            // class:'mdui-radio',
            type: 'radio',
            name: 'member',
            value: member['roomId']
        });

        var i = $('<i></i>').attr('class', 'mdui-radio-icon');

        label.append(input, i, member['memberName']);

        // node.append(label);
        $('#' + node).append(label);
    });
    bundleClickEvent();
}

function bundleClickEvent() {

    $('input[type=radio]').click(function () {

        // var roomId = $('input[type=radio]:checked').val();
        var roomId = $(this).val();
        console.log(roomId);

        getPocketData(roomId);
        $("#dataarea").show();
    })
}


function drawData(result) {

    ypg();

    var month = new Date().getMonth() + 1;

    var days = '本月营业天数: ' + result['days'] + '天';

    $("#days").text(days);

    var chart = $("<canvas></canvas>").attr({
        id: 'pocket_data',
        height: '150px'
    });

    // var ctx = $("#data").get(0).getContext('2d');
    var ctx = chart.get(0).getContext('2d');
    $("#chartarea").empty().append(chart);

    result.data.sort(function (a, b) {
        return (typeValueMapping[a['type']] - typeValueMapping[b['type']])
    });

    var datalist = parseData(result['data']);

    for (let index in datalist.type) {
        datalist.type[index] = translate(datalist.type[index]);
    }

    console.log(datalist);
    drawChart(ctx, datalist, {
        'type': 'pie',
        'backgroundColor': ["rgba(255, 159, 64, 0.48)", "rgba(153, 102, 255, 0.48)", "rgba(255, 206, 86, 0.48)", "rgba(54, 162, 235, 0.48)", "rgba(205, 201, 201, 0.48)", "rgba(255, 99, 132, 0.48)", "rgba(75, 192, 192, 0.48)"],
        'borderColor': ["rgba(255, 159, 64, 1)", "rgba(153, 102, 255, 1)", "rgba(255, 206, 86, 1)", "rgba(54, 162, 235, 1)", "rgba(205, 201, 201, 1)", "rgba(255, 99, 132, 1)", "rgba(75, 192, 192, 1)"],
    })
}

function parseData(obj) {
    var returnList = {};

    obj.forEach(function (each) {
        for (var key in each) {
            if (returnList[key] == undefined) {
                returnList[key] = [];
            }
            returnList[key].push(each[key])
        }
    });
    return returnList;
}

function drawChart(ctx, data, style) {
    var formdata = {
        "type": "",
        "data": {
            "labels": [],
            "datasets": [
                {
                    "label": [],
                    "data": [],

                    "backgroundColor": [],
                    "borderColor": [],

                    "borderWidth": 1
                }
            ]
        },
        "options": {}
    };

    formdata.data.datasets[0].data = data.counts;
    formdata.data.labels = data.type;

    formdata.type = style.type;

    formdata.data.datasets[0].backgroundColor = style['backgroundColor'];
    formdata.data.datasets[0].borderColor = style['borderColor'];

    new Chart(ctx, formdata);

}

function translate(type) {
    switch (type) {
        case 'audio':
            return '语音消息';

        case 'text':
            return '文字消息';

        case 'faipaiText':
            return '免费翻牌';

        case 'live':
            return '露脸直播';

        case 'diantai':
            return '电台直播';

        case 'idolFlip':
            return '鸡腿普通翻牌';

        case 'image':
            return '图片消息';

        case 'videoRecord':
            return '视频消息';
    }
    // return type;
}