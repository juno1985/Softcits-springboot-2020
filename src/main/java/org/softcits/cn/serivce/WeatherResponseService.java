package org.softcits.cn.serivce;

import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Response;

public interface WeatherResponseService {
	
	Response getWeatherResponse(City city);

}
