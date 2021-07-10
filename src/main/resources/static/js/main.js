var basic_url = 'http://localhost:8080/report/id/';
var cities_url = 'http://localhost:8080/report/get/cities';
var CITY_LIST = [];
//页面加载执行
$(function () {

    var default_cid = '101010100';
    var full_url = basic_url + default_cid;
    displayWeatherReport(full_url);
    initCityList();
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
                            {marginTop: '-25px'}, 2000,
                            function () {
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

function ambiguousSearch(obj) {

    //游标位置
    var cursor = 0;
    $(this).keyup(function (event) {

        // console.log(event.keyCode);

        // 空格键
        if (event.keyCode == 32) {
            //去掉空格
            var input_value = $('#cityInput').val().replace(' ', '');

            // console.log(input_value);
            //从列表匹配input中输入的汉字
            $.each($('#cityList').find('div'), function (index, element) {
                var div_value = element.innerHTML;
                // console.log(div_value);
                if (div_value.indexOf(input_value) != -1) {
                    element.style.display = 'block';
                } else {
                    element.style.display = 'none';
                }
            });
        }
        // up 键
        else if (event.keyCode == 38) {
            if (cursor > 0) {
                cursor--;
              //  console.log(cursor);
            }
        }
        // down 键
        else if (event.keyCode == 40) {
            if (cursor < $('#cityList').find('div').size() - 1) {
                cursor++;
               // console.log(cursor);
            }
        }
        // enter 键
        else if (event.keyCode == 13) {

        }

        $.each($('#cityList').find('div'), function (index, element) {
            if (index == cursor) {
                $(element).css('background', '#eee');
                //调节滚动条偏移
                $('#cityList').scrollTop(element.offsetTop);
                //console.log($(element).scrollTop());
                console.log(element.offsetTop);
            } else {
                $(element).css('background', '');
            }

        });
    });
}

function initCityList() {

    $.ajax(
        {
            url: cities_url,
            method: 'get',
            dataType: 'json',
            success: function (arr_city) {
                var cityList_innerHtml = '';
                $.each(arr_city, function (index, element) {
                    cityList_innerHtml += '<div id="' + element.id + '" city_id="' + element.city_id + '">'
                        + element.city_name + '</div>';
                });

                $('#cityList').html(cityList_innerHtml);

                $.each($('#cityList').find('div'), function (index, element) {
                    //鼠标经过高亮
                    $(element).mouseenter(function () {
                        $(this).css('background', '#eee');
                        $(this).siblings().css('background', '');
                    });
                    //鼠标点击事件
                    $(element).click(
                        function () {
                            //console.log($(this));
                            var cityName = $(this).html()
                            var cityId = $(this).attr('city_id');
                            $('#cityInput').val(cityName);
                            var fullURL = basic_url + cityId;
                            displayWeatherReport(fullURL);
                        }
                    );
                });
            }
        }
    );
}