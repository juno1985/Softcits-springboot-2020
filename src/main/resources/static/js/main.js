var basic_url = 'http://localhost:8080/report/id/';
$(function () {

    var default_cid = '101290201';
    var full_url = basic_url + default_cid;
    displayWeatherReport(full_url);

});

function displayWeatherReport(full_url){
    $.ajax(
        {
            url : full_url,
            method: 'get',
            datatype: 'json',
            success : function(response){
                if(response.status == 200){
                    console.log(response);
                    // obtain 'a.report_wrapper_blocker_link'
                    var arr_a_forecast = $('.report_wrapper_blocker .report_wrapper_blocker_link');

                    $.each(arr_a_forecast, function(index, element){
                        var innerHtml = '';
                        innerHtml += '<p>'+ response.data.forecast[index].date+'</p>' +
                            '<p>'+ response.data.forecast[index].fengli+'</p>' +
                            '<p>'+ response.data.forecast[index].fengxiang+'</p>' +
                            '<p>'+ response.data.forecast[index].high+'</p>' +
                            '<p>'+ response.data.forecast[index].low+'</p>' +
                            '<p>'+ response.data.forecast[index].type+'</p>' ;
                        // 填充a标签
                        $(element).html(innerHtml);

                    });
                }
                else{
                    alert("Failed getting data from server.");
                }
            }
        });
}