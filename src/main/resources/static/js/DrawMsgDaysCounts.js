function msgDaysCounts() {
    var _url = $("#contextpath").val() + '/ajax/msg/counts/all';
    $.ajax(
        {
            url: _url,
            type: "get",
            dataType: 'json',
            success: function (result) {
                if (result.status == 200 && result.desc == 'success') {
                    var data = buildFormresult(result.data);
                    drawMsgDaysCountsLine("msgDayCounts", data);
                }
            },
            error: function (e) {
                console.log('error');
                console.log(e)
            }
        }
    )
}
