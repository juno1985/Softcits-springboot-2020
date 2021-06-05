package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeatherDataAdminMapper {
	void cleanForecast();
	void cleanYesterday();
	void cleanNotice();
	void cleanForecastTmp();
	void cleanYesterdayTmp();
	void cleanNoticeTmp();
}
