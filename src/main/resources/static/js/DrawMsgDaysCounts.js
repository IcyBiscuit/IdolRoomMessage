function msgDaysCounts() {
    // var url = $("#contextpath").val() + '/ajax/msg/counts/all';

    var contextpath = $("#contextpath").val();
    var url = "/ajax/msg/counts/all";

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
                        var data = buildFormresult(result.data);
                        drawMsgDaysCountsLine("msgDayCounts", data);
                    } catch (e) {
                        console.log(e)
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
