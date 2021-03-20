package org.softcits.cn.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.model.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OccupationServiceTest {
	
	@Autowired
	private OccupationService occupationService;

	@Test
	public void getFirstOccupationTest() {
		Occupation op = occupationService.getFirstOccupation();
		
		Assertions.assertNotNull(op);
	}
}
