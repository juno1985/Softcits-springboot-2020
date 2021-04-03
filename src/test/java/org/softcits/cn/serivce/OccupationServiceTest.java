package org.softcits.cn.serivce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Test
	public void getOccupationByNameTest() {
		Occupation op = occupationMapper.getOccupationByName("Julia");
		Assertions.assertEquals("Julia", op.getName());
		Assertions.assertEquals("Actor", op.getOccupation());
	}
	
	@Test
	public void getOccupationByNamePrefixTest() {
		List<Occupation> occList = occupationMapper.getOccupationByNamePrefix("J%");
		Assertions.assertEquals(3, occList.size());
	}
	
	@Test
	public void updateOccupationByNameTest() {
		int num = occupationMapper.updateOccupationByName("Julia", "Actor");
		System.out.print("Affected Rows: " + num);
		Assertions.assertEquals(1, num);
	}
	
	@Test
	public void insertOccupationTest() {
		int num = occupationMapper.insertOccupation("Mark", "Actor");
		Assertions.assertEquals(1, num);
	}
	
	@Test
	public void deleteOccupationDelete() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Mark");
		map.put("occp", "Actor");
		int num = occupationMapper.deleteOccupation(map);
		System.out.println("Delete Rows: " + num);
	}
	
	@Test
	public void getByNameAndOccupation() {
		Occupation occp = new Occupation();
		occp.setName("Jenny");
		occp.setOccupation("Doctor");
		Occupation result = occupationMapper.getByNameAndOccupation(occp);
		Assertions.assertEquals("Jenny", result.getName());
		Assertions.assertEquals("Doctor", result.getOccupation());
	}
}






