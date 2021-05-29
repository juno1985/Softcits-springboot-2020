package org.softcits.cn.serivce;

import org.junit.jupiter.api.Test;
import org.softcits.cn.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RemoteDataServiceTest {

	@Autowired
	private RemoteDataService remoteDataService;
	
	final String prefix_url = "http://wthrcdn.etouch.cn/weather_mini?citykey=";
	
	@Test
	public String getRemoteDataTest() {
		String cityID = "101010100";
		String full_url = prefix_url + cityID;
		String resp = remoteDataService.getRemoteData(full_url);
		return resp;
	}
	
	@Test
	public Response getResponseFromJSONTest() {
		//get json from remote server
		String jsonStr = this.getRemoteDataTest();
		// convert json to Response object
		Response response = remoteDataService.getResponseFromJSON(jsonStr);
		
		System.out.println(response);
		
		return response;
	}
	
	@Test
	public void initSingleWeatherData() {
		remoteDataService.initSingleWeatherData("101010100");
	}
}
