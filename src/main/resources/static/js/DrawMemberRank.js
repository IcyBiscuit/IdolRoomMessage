function memberRank() {
    var _url = $("#contextpath").val() + "/ajax/rank/all";

    $.ajax(
        {
            url: _url,
            type: "get",
            dataType: 'json',
            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    var data = result.data;
                    for (var key in data) {
                        try {
                            drawchar(key, data[key], {
                                'type': 'bar',
                                "label": String(key),
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
    var _url = $("#contextpath").val() + '/ajax/rank/all/live';

    $.ajax(
        {
            url: _url,
            type: "get",
            dataType: 'json',
            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    var data = result.data;

                    drawchar("allLive", data, {
                        'type': 'bar',
                        "label": 'all live',
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