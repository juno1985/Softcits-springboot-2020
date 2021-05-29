package org.softcits.cn.serivce;

import org.softcits.cn.pojo.Response;

public interface RemoteDataService {

	String getRemoteData(String url);

	Response getResponseFromJSON(String jsonStr);
	//initialize weather data for all cities
	void initBatchWeatherData();
	//initialize weather date for a single city
	void initSingleWeatherData(String cityId);
}
