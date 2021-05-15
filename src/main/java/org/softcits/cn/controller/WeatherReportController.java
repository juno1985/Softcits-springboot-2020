package org.softcits.cn.controller;

import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.serivce.RemoteDataService;
import org.softcits.cn.serivce.WeatherResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/report")
@Api(tags = "WeatherReportController Interface")
public class WeatherReportController {
	
	@Autowired
	private WeatherResponseService weatherResponseService;
	
	@Autowired
	private RemoteDataService remoteDataService;
	
	/**
	 * http://localhost:8080/report/id/101070101
	 * @param cityid
	 * @return
	 */
	@ApiOperation("Get report json by city id")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cityid", value = "national city id", required = true)
	})
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
	@ApiOperation("Get report json by city name")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cityname", value = "national city name", required = true)
	})
	@GetMapping("/name/{cityname}")
	public Response getWeatherResponseByCityName(@PathVariable(name="cityname")String cityname) {
		City city = new City();
		city.setCity_name(cityname);
		Response response = weatherResponseService.getWeatherResponse(city);
		return response;
	}
	
	
	
}
