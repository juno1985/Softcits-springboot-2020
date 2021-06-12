package org.softcits.cn.serivce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mysql.cj.log.LogFactory;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softcits.cn.mapper.WeatherResponseMapper;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Data;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.util.JSONObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherResponseServiceImpl implements WeatherResponseService {

	private static final String REDIS_CITY_ID_PREFIX = "weather:cityid:";
	private static final String REDIS_CITY_NAME_PREFIX = "weather:cityname:";
	private static final Logger logger = LoggerFactory.getLogger(WeatherResponseServiceImpl.class);

	@Autowired
	private WeatherResponseMapper weatherResponseMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Override
	public Response getWeatherResponse(City city) {
		Response response = new Response();
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		String key = null;
		if(!StringUtils.isEmptyOrWhitespaceOnly(city.getCity_id())){
			key = REDIS_CITY_ID_PREFIX + city.getCity_id();
		}else if(!StringUtils.isEmptyOrWhitespaceOnly(city.getCity_name())){
			key = REDIS_CITY_NAME_PREFIX + city.getCity_name();
		}
		long startTime = System.currentTimeMillis();
		// if key is in the redis
		if (stringRedisTemplate.hasKey(key)){
			logger.info("query against redis: " + key);

			String json = ops.get(key);
			long endTime = System.currentTimeMillis();
			logger.info("time eclipsed: " + (endTime - startTime)/1000);
			return JSONObjectConverter.generateObjectFromJSON(json, new TypeReference<Response>() {
			});

		}
		// if key is not in redis, extract data from mysql
		try {
			logger.info("query against mysql: " + key);
			// query against mysql
			Data data = weatherResponseMapper.getWeatherResponse(city);
			long endTime = System.currentTimeMillis();
			logger.info("time eclipsed: " + (endTime - startTime)/1000);
			if(data != null) {
				response.setDesc("OK");
				response.setStatus("200");
				response.setData(data);
				// set in redis
				logger.info("load into redis");
				ops.set(key, JSONObjectConverter.generateJSONFromObject(response), 10 * 60, TimeUnit.SECONDS);
			}
			else {
				response.setDesc("Data Not Found");
				response.setStatus("404");
			}
		} catch (Exception e) {
			// TODO -- replace with logger
			e.printStackTrace();
			response.setDesc("Interal Error");
			response.setStatus("500");
		}
		
		return response;
	}

}
