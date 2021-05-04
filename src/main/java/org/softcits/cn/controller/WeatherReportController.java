package org.softcits.cn.controller;

import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.serivce.WeatherResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	
	@Autowired
	private WeatherResponseService weatherResponseService;
	/**
	 * http://localhost:8080/report/id/101070101
	 * @param cityid
	 * @return
	 */
	@GetMapping("/id/{cityid}")
	public Response getWeatherResponseByCityId(@PathVariable(name="cityid")String cityid) {
		City city = new City();
		city.setCity_id(cityid);
		Response response = weatherResponseService.getWeatherResponse(city);
		return response;
	}
	/**
	 * http://localhost:8080/report/name/沈阳
	 * @param cityid
	 * @return
	 */
	@GetMapping("/name/{cityname}")
	public Response getWeatherResponseByCityName(@PathVariable(name="cityname")String cityname) {
		City city = new City();
		city.setCity_name(cityname);
		Response response = weatherResponseService.getWeatherResponse(city);
		return response;
	}
	
}
