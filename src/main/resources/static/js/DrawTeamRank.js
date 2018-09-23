function teamRank() {
    // var url = $("#contextpath").val() + '/ajax/rank/team';
    var contextpath = $("#contextpath").val();
    var url = "/ajax/rank/team";

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
                    try {
                        drawchar('team', result.data, {
                            'type': 'doughnut',
                            "backgroundColor": ["rgba(75, 192, 192, 0.5)", "rgba(255, 206, 86, 0.5)", "rgba(255, 99, 132, 0.5)", "rgba(200,200,200,0.5)"],
                            "borderColor": ["rgba(75, 192, 192, 1)", "rgba(255, 206, 86, 1)", "rgba(255,99,132,1)", "rgba(200,200,200,1)"],
                            "option": {}
                        })
                    } catch (e) {
                        console.log(e);
                    }
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e)
            }
        }
    )
}