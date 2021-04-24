package org.softcits.cn.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.model.CityNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityNoticeServiceTest {
	
	@Autowired
	private CityNoticeService cityNoticeService;
	
	
	@Test
	public void getCityNoticeByCityIdTest() {
		final String city_id = "101070101";
		CityNotice cityNotice = cityNoticeService.getCityNoticeByCityId(city_id);
		
		Assertions.assertEquals("12", cityNotice.getNotice().getWendu());
	}

}
