function teamRank() {
    var _url = '/ajax/rank/team';
    $.ajax(
        {
            url: _url,
            type: "get",
            dataType: 'json',
            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    drawchar('team', result.data, {
                        'type': 'doughnut',
                        "backgroundColor": ["rgba(75, 192, 192, 0.5)", "rgba(255, 206, 86, 0.5)", "rgba(255, 99, 132, 0.5)", "rgba(200,200,200,0.5)"],
                        "borderColor": ["rgba(75, 192, 192, 1)", "rgba(255, 206, 86, 1)", "rgba(255,99,132,1)", "rgba(200,200,200,1)"],
                        "option": {
                            // "startAngle": -0.75 * Math.PI
                        }
                    })
                }
                // success(result)
            },
            error: function (e) {
                console.log('error');
                console.log(e)
            }
        }
    )
}