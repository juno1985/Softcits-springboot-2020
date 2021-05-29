package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.mapper.CityMapper;
import org.softcits.cn.mapper.ForecastMapper;
import org.softcits.cn.model.City;
import org.softcits.cn.model.Forecast;
import org.softcits.cn.pojo.ForecastPojo;
import org.softcits.cn.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastServiceImpl implements ForecastService {

	private static final String CITY_KEY_URL = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
	
	@Autowired
	private ForecastMapper forecastMapper;
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private RemoteDataService remoteDataService;
	
	public List<Forecast> getForecastByCityId(String cid){
		List<Forecast> forecastList = forecastMapper.getForecastByCityId(cid);
		return forecastList;
	}

	@Override
	public void insert(String cityId) {
		
		String url = CITY_KEY_URL + cityId;
		
		String json = remoteDataService.getRemoteData(url);
		
		Response response = remoteDataService.getResponseFromJSON(json);
		
		List<ForecastPojo> list =  response.getData().getForecast();
		
		for(int i = 0; i < list.size(); i++) {
			ForecastPojo forecastPojo = list.get(i);
			Forecast forecast = new Forecast();
			City city = cityMapper.getCityByCityId(cityId);
			forecast.setCid(city.getId());
			forecast.setDate(forecastPojo.getDate());
			forecast.setDay_order(i);
			forecast.setFengli(forecastPojo.getFengxiang());
			forecast.setFengxiang(forecastPojo.getFengxiang());
			forecast.setHigh(forecastPojo.getHigh());
			forecast.setLow(forecastPojo.getLow());
			forecast.setType(forecastPojo.getType());
			forecastMapper.insert(forecast);
		}
		
	}
}
