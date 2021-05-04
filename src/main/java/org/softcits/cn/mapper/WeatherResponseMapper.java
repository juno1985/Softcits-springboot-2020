package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.City;
import org.softcits.cn.model.CityForecast;
import org.softcits.cn.model.Yesterday;
import org.softcits.cn.pojo.Data;
@Mapper
public interface WeatherResponseMapper {

	Data getWeatherResponse(City city);
	
}
