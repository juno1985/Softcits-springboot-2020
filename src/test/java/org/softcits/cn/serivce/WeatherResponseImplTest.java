package org.softcits.cn.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherResponseImplTest {

	@Autowired
	private WeatherResponseService weatherResponseService;

	@Test
	public void getWeatherResponseWithCity_idTest() {
		City city = new City();
		city.setCity_id("101070101");
		
		Response response = weatherResponseService.getWeatherResponse(city);
		
		Assertions.assertEquals("200", response.getStatus());
		Assertions.assertEquals("OK", response.getDesc());
		Assertions.assertEquals(5, response.getData().getForecast().size());
	}
	
	@Test
	public void getWeatherResponseWithCity_NameTest() {
		City city = new City();
		city.setCity_name("沈阳");
		
		Response response = weatherResponseService.getWeatherResponse(city);
		
		Assertions.assertEquals("200", response.getStatus());
		Assertions.assertEquals("OK", response.getDesc());
		Assertions.assertEquals(5, response.getData().getForecast().size());
	}
}
