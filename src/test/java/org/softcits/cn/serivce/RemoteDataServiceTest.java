package org.softcits.cn.serivce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RemoteDataServiceTest {

	@Autowired
	private RemoteDataService remoteDataService;
	
	final String prefix_url = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
	
	@Test
	public void getRemoteDataTest() {
		String cityID = "101010100";
		String full_url = prefix_url + cityID;
		String resp = remoteDataService.getRemoteData(full_url);
		System.out.println(resp);
	}
}
