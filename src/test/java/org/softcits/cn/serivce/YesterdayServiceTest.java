package org.softcits.cn.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.softcits.cn.model.Yesterday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class YesterdayServiceTest {

	@Autowired
	private YesterdayService yesterdayService; 
	
	@Test
	public void insertTest() {
		Yesterday yesterday = new Yesterday();
		yesterday.setDate("10日星期六");
		yesterday.setFl("<![CDATA[3级]]>");
		yesterday.setFx("西风");
		yesterday.setType("晴天");
		yesterday.setHigh("高温 19℃");
		yesterday.setCid(2);
		yesterday.setId(null);
		yesterday.setLow("低温 2℃");
		int rowNum = yesterdayService.insert(yesterday);
		Assertions.assertEquals(1, rowNum);
	}
}
