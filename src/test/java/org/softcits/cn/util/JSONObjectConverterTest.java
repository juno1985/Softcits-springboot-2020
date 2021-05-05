package org.softcits.cn.util;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.pojo.CityPojo;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootTest
public class JSONObjectConverterTest {

	@Test
	public void generateObjectFromJSONFileTest() {
		String filePath = "citylist.json";
		TypeReference<List<CityPojo>> typeReference = new TypeReference<List<CityPojo>>() {
		};
		
		List<CityPojo> cityList = JSONObjectConverter.generateObjectFromJSONFile(filePath, typeReference);
		
		Assertions.assertEquals(2918, cityList.size());
	}
}
