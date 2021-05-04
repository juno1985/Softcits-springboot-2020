package org.softcits.cn.serivce;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softcits.cn.model.Yesterday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BatchServiceTest {
	
	private List<Yesterday> yesList;
	
	
	@Autowired
	private BatchService batchService;
	
	@BeforeEach
	private void init() {
		
		yesList = new ArrayList<>();
		
		Yesterday yesterday1 = new Yesterday();
		yesterday1.setDate("10日星期六");
		yesterday1.setFl("<![CDATA[3级]]>");
		yesterday1.setFx("西风");
		yesterday1.setType("晴天");
		yesterday1.setHigh("高温 19℃");
		yesterday1.setCid(3);
		yesterday1.setId(null);
		yesterday1.setLow("低温 2℃");
		
		Yesterday yesterday2 = new Yesterday();
		yesterday2.setDate("10日星期六");
		yesterday2.setFl("<![CDATA[3级]]>");
		yesterday2.setFx("西风");
		yesterday2.setType("晴天");
		yesterday2.setHigh("高温 19℃");
		yesterday2.setCid(4);
		yesterday2.setId(null);
		yesterday2.setLow("低温 2℃");
		
		yesList.add(yesterday1);
		yesList.add(yesterday2);
	}

	
	@Test
	public void insertBatchTest() {
		batchService.insertBatch(yesList);
	}
	
}
