package org.softcits.cn.serivce;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.softcits.cn.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ForecastServiceTest {

	@Autowired
	private ForecastService forecastService;
	@Test
	public void getForecastByCityIdTest() {
		List<Forecast> forecastList = forecastService.getForecastByCityId("1");
		for(Forecast forecast : forecastList) {
			System.out.println(forecast.getId() + "->" + forecast.getDate() + "->" + forecast.getFengli() + "->" + forecast.getHigh() + "->" + forecast.getLow() + "->" + forecast.getType() + "->" + forecast.getFengxiang() + "->" + forecast.getCid());
		}
	}
	
}
