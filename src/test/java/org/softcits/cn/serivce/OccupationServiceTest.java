package org.softcits.cn.serivce;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.mapper.OccupationMapper;
import org.softcits.cn.model.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OccupationServiceTest {
	
	@Autowired
	private OccupationService occupationService;
	@Autowired
	private OccupationMapper occupationMapper;

	@Test
	public void getFirstOccupationTest() {
		Occupation op = occupationService.getFirstOccupation();
		
		Assertions.assertNotNull(op);
	}
	
	@Test
	public void getAllOccupationsTest() {
		List<Occupation> occList = occupationMapper.getAllOccupations();
		Assertions.assertNotEquals(0, occList.size());
		System.out.println("Total Size: " + occList.size());
	}
}






