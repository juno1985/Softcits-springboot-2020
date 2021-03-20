package org.softcits.cn.controller;

import org.softcits.cn.mapper.OccupationMapper;
import org.softcits.cn.model.Occupation;
import org.softcits.cn.serivce.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class OccupationController {
	
	@Autowired
	private OccupationService occupationService;
	
	@GetMapping("/occupation/getfirst")
	public Occupation getFirstOccupation() {
		return occupationService.getFirstOccupation();
	}

}
