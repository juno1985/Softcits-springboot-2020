package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.mapper.CityMapper;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.ForecastPojo;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.util.JSONObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
@Service
public class RemoteDataServiceImpl implements RemoteDataService {

	private static final String CITY_KEY_URL = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ForecastService forecastService;
	@Autowired
	private CityMapper cityMapper;
	@Override
	public String getRemoteData(String url) {
		
		ResponseEntity<String> respEntity = restTemplate.getForEntity(url, String.class);
		String resp = null;
		if (respEntity.getStatusCodeValue() == 200) {
			resp = respEntity.getBody();
		}
		
		return resp;
	}
	@Override
	public Response getResponseFromJSON(String jsonStr) {
		TypeReference<Response> typeReference = new TypeReference<Response>() {
		};
		return JSONObjectConverter.generateObjectFromJSON(jsonStr, typeReference);
	}
	@Override
	public void initSingleWeatherData(String cityId) {
		
		String url = CITY_KEY_URL + cityId;
		// get json from http://wthrcdn.etouch.cn/weather_min
		String json = this.getRemoteData(url);
		// convert json to Response object
		Response response = this.getResponseFromJSON(json);
		// insert forecast data into mysql
		List<ForecastPojo> forecastPojoList = response.getData().getForecast(); 
		City city = cityMapper.getCityByCityId(cityId);
		Integer cid = city.getId();
		forecastService.insert(forecastPojoList, cid);
		// insert yesterday data into mysql
		
	}
	@Override
	public void initBatchWeatherData() {
		// TODO Auto-generated method stub
		
	}

}
