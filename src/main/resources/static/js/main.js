var basic_url = 'http://localhost:8080/report/id/';
$(function () {

    var default_cid = '101010100';
    var full_url = basic_url + default_cid;
    displayWeatherReport(full_url);

});

function displayWeatherReport(full_url) {
    $.ajax(
        {
            url: full_url,
            method: 'get',
            datatype: 'json',
            success: function (response) {
                if (response.status == 200) {
                    //拼接上面滚动条的列表
                    var notification_bar_html = '<ul><li>' + response.data.city + '</li><li>'
                        + response.data.ganmao + '</li><li>'
                        + response.data.wendu + '°</li></ul>';

                    $('#report_notification').html(notification_bar_html);

                    setInterval(function () {
                        $('#report_notification').find('ul:first').animate(
                            {marginTop: '-25px'}, 500,
                               function(){
                                   $(this).css({marginTop: '0px'}).find('li:first').appendTo(this);
                               }

                    )
                    }, 1000);

                    // console.log(response);
                    // obtain 'a.report_wrapper_blocker_link'
                    var arr_a_forecast = $('.report_wrapper_blocker .report_wrapper_blocker_link');
                    // 填充每一天天气信息
                    $.each(arr_a_forecast, function (index, element) {
                        var innerHtml = '';
                        innerHtml += '<p>' + response.data.forecast[index].date + '</p>' +
                            '<p>' + response.data.forecast[index].fengli + '</p>' +
                            '<p>' + response.data.forecast[index].fengxiang + '</p>' +
                            '<p>' + response.data.forecast[index].high + '</p>' +
                            '<p>' + response.data.forecast[index].low + '</p>' +
                            '<p>' + response.data.forecast[index].type + '</p>';
                        // 填充a标签
                        $(element).html(innerHtml);
                        // 添加天气属性
                        $(element).attr('data-weather-type', response.data.forecast[index].type);
                        // 添加鼠标悬浮事件
                        $(element).hover(
                            function () {
                                replaceBackground($(this).attr('data-weather-type'), $('#report_wrapper'));
                            }, function () {

                            }
                        );
                    });
                    // 设置背景图片默认用第一天天气
                    replaceBackground($(arr_a_forecast).eq(0).attr('data-weather-type'), $('#report_wrapper'));
                } else {
                    alert("Failed getting data from server.");
                }
            }
        });
}

function replaceBackground(weather_type, html_dom) {

    if (weather_type.indexOf("雷") != -1) {
        html_dom.css('background', 'url(../img/thunder.gif) no-repeat').css('background-size', '100%');

    } else if (weather_type.indexOf("雾") != -1 || weather_type.indexOf("阴") != -1) {
        html_dom.css('background', 'url(../img/cloudy.gif) no-repeat').css('background-size', '100%');
    } else if (weather_type.indexOf("雨") != -1) {
        html_dom.css('background', 'url(../img/rainy.gif) no-repeat').css('background-size', '100%');
    } else {
        html_dom.css('background', 'url(../img/sunny.gif) no-repeat').css('background-size', '100%');
    }
}