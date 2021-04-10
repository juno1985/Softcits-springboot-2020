package org.softcits.cn.serivce;

import org.softcits.cn.model.CityForecast;

public interface CityForecastService {
	CityForecast getForcastWithCityByCityId(String cid);
}
