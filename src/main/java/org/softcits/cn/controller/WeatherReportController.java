package org.softcits.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.Response;
import org.softcits.cn.serivce.CityService;
import org.softcits.cn.serivce.RemoteDataService;
import org.softcits.cn.serivce.UserService;
import org.softcits.cn.serivce.WeatherResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
@Api(tags = "WeatherReportController Interface")
public class WeatherReportController {
	
	@Autowired
	private WeatherResponseService weatherResponseService;
	
	@Autowired
	private RemoteDataService remoteDataService;

	@Autowired
	private CityService cityService;

	@Autowired
	private UserService userService;
	
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

	@GetMapping("/main")
	public ModelAndView redirectMainPage(){
		ModelAndView modelAndView = new ModelAndView("/page/main.html");
		return modelAndView;
	}

	@ApiOperation("Get all cities")
	@GetMapping("/get/cities")
	public ResponseEntity<List<City>> getAllCities(){
		List<City> list = cityService.getAllCities();
		return new ResponseEntity<List<City>>(list, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(String user, String password, HttpServletRequest request, HttpServletResponse response){
		String u = userService.login(user, password, request, response);
		if(u == null){
			try {
				//重新登录
				response.sendRedirect("/page/login.html");

			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("Login Failed", HttpStatus.FORBIDDEN);
		}
		else return new ResponseEntity<String>("Login Success", HttpStatus.OK);
	}
	
}
