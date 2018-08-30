function memberRank() {
    var _url = "/idol/ajax/rank/all";

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
                                "backgroundColor": "",
                                "borderColor": "",
                                "label": String(key),
                                "option": {}
                            })
                        } catch (e) {
                            console.log(e)
                        }

                    }
                }
                // success(result)
            },
            error: function (e) {
                console.log('error');
                console.log(e);
            }
        }
    )
}