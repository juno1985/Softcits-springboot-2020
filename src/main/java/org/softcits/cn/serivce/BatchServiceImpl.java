package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.model.Yesterday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private YesterdayService yesterdaySerivce; 
	
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	@Override
	public Integer insertBatch(List<Yesterday> yesList) {
		int num = 0;
		for(int i = 0; i < yesList.size(); i++) {
			Yesterday yesterday = yesList.get(i);
			try {
				num += yesterdaySerivce.insert(yesterday);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return num;
	}

}
