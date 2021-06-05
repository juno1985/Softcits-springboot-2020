package org.softcits.cn.serivce;

import com.fasterxml.jackson.core.type.TypeReference;
import org.softcits.cn.mapper.*;
import org.softcits.cn.model.City;
import org.softcits.cn.model.Notice;
import org.softcits.cn.model.Yesterday;
import org.softcits.cn.pojo.Data;
import org.softcits.cn.pojo.ForecastPojo;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.pojo.YesterdayPojo;
import org.softcits.cn.util.JSONObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class RemoteDataServiceImpl implements RemoteDataService {

	@Value(value="${url.weather.cityid}")
	private String CITY_KEY_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ForecastService forecastService;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private YesterdayMapper yesterdayMapper;
	@Autowired
	private CityNoticeMapper cityNoticeMapper;
	@Autowired
	private YesterdayTmpMapper yesterdayTmpMapper;
	@Autowired
	private CityNoticeTmpMapper cityNoticeTmpMapper;


	@Override
	public String getRemoteData(String url) {

		String resp = null;
		try {
			ResponseEntity<String> respEntity = restTemplate.getForEntity(url, String.class);
			if (respEntity.getStatusCodeValue() == 200) {
				resp = respEntity.getBody();
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return resp;
	}
	@Override
	public Response getResponseFromJSON(String jsonStr) {
		TypeReference<Response> typeReference = new TypeReference<Response>() {
		};
		return JSONObjectConverter.generateObjectFromJSON(jsonStr, typeReference);
	}

	/**
	 *
	 * @param cityId - national city id
	 */
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	@Override
	public void initSingleWeatherData(String cityId) {

		List<Object> inputList = getRemoteJsonAndConvertToObjList(cityId);
		if(inputList.isEmpty()){
			return;
		}
		Integer cid = getCityIdByNationalCityId(cityId);
		for(Object obj : inputList){
			if(obj instanceof java.util.ArrayList){
				forecastService.insert((ArrayList)obj, cid, false);
			}
			else if(obj instanceof Yesterday){
				yesterdayMapper.insert((Yesterday)obj);
			}
			else if(obj instanceof Notice){
				cityNoticeMapper.insert((Notice) obj);
			}
		}

	}
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	@Override
	public void initBatchWeatherData() {
		List<City> cityList = cityMapper.getAllCities();
		
		for(City city : cityList) {
			initSingleWeatherData(city.getCity_id());
		}
		
	}

	private List<Object> getRemoteJsonAndConvertToObjList(String cityId){
		String url = CITY_KEY_URL + cityId;
		// get json from http://wthrcdn.etouch.cn/weather_min
		String json = this.getRemoteData(url);
		// convert json to Response object
		Response response = this.getResponseFromJSON(json);
		List<Object> resultList = new ArrayList<Object>();
		if(response.getDesc().equals("OK") && response.getStatus().equals("1000")) {
			Integer cid = getCityIdByNationalCityId(cityId);
			Data data = response.getData();
			// add forecast list to result list
			List<ForecastPojo> forecastPojoList = data.getForecast();
			resultList.add(forecastPojoList);
			// add yesterday to result list
			YesterdayPojo yesterdayPojo = data.getYesterday();
			Yesterday yesterday = new Yesterday();
			yesterday.setCid(cid);
			yesterday.setDate(yesterdayPojo.getDate());
			yesterday.setFl(yesterdayPojo.getFl());
			yesterday.setFx(yesterdayPojo.getFx());
			yesterday.setHigh(yesterdayPojo.getHigh());
			yesterday.setLow(yesterdayPojo.getLow());
			yesterday.setType(yesterdayPojo.getType());
			resultList.add(yesterday);
			// insert notice data into mysql
			Notice notice = new Notice();
			notice.setGanmao(data.getGanmao());
			notice.setWendu(data.getWendu());
			notice.setCid(cid);
			resultList.add(notice);
		}
		return resultList;
	}

	private Integer getCityIdByNationalCityId(String cityId){
		// retrieve city cid - primary key in mysql
		City city = cityMapper.getCityByCityId(cityId);
		Integer cid = city.getId();
		return cid;
	}

	public void initSingleWeatherDataTmp(String cityId) {

		List<Object> inputList = getRemoteJsonAndConvertToObjList(cityId);
		if(inputList.isEmpty()){
			return;
		}
		Integer cid = getCityIdByNationalCityId(cityId);
		for(Object obj : inputList){
			if(obj instanceof java.util.ArrayList){
				forecastService.insert((ArrayList)obj, cid, true);
			}
			else if(obj instanceof Yesterday){
				yesterdayTmpMapper.insert((Yesterday)obj);
			}
			else if(obj instanceof Notice){
				cityNoticeTmpMapper.insert((Notice) obj);
			}
		}

	}

	public void initBatchWeatherDataTmp(){
		List<City> cityList = cityMapper.getAllCities();

		for(City city : cityList) {
			initSingleWeatherDataTmp(city.getCity_id());
		}
	}
}
