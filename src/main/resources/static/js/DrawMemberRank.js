function memberRank() {
    var contextpath = $("#contextpath").val();
    var url = "/ajax/rank/all";

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
                    var data = result.data;
                    for (var key in data) {
                        try {
                            drawchar(key, data[key], {
                                'type': 'bar',
                                "label": translate(key),
                                "option": {}
                            })
                        } catch (e) {
                            console.log(e)
                        }
                    }
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e);
            }
        }
    )
}

function liveRank() {
    // var url = $("#contextpath").val() + '/ajax/rank/all/live';

    var contextpath = $("#contextpath").val();
    var url = "/ajax/rank/all/live";

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
                    var data = result.data;

                    drawchar("allLive", data, {
                        'type': 'bar',
                        "label": '所有直播统计',
                        "option": {}
                    })
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e);
            }
        }
    )

}