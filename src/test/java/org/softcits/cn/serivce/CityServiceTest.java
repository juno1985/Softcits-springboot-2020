package org.softcits.cn.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softcits.cn.pojo.CityPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityServiceTest {
	
	@Autowired
	private CityService cityService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void insertCityTest() {
		CityPojo cityPojo = new CityPojo();
		cityPojo.setName("重庆");
		cityPojo.setId("123213213221");
		Integer num = cityService.insertCity(cityPojo);
		Assertions.assertEquals(1, num);
	}
	
	@Test
	public void insertCityBatch() {
		int nums = cityService.insertCityBatch();
		log.info("Records inserted: " + nums);
	}

}
