package org.softcits.cn.controller;

import org.softcits.cn.pojo.AffectedNum;
import org.softcits.cn.serivce.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
@Api(tags = "AdminController Interface")
public class AdminController {
	
	@Autowired
	private CityService cityService;

	@ApiOperation("City date batch insert by json file")
	@GetMapping("/addcities")
	public ResponseEntity<AffectedNum> insertCityBatch() {
		
		int num = cityService.insertCityBatch();
		
		AffectedNum an = new AffectedNum(num, "Cities added successfully");
		
		return new ResponseEntity<AffectedNum>(an, HttpStatus.OK);
	}
}
