package org.softcits.cn.serivce;

import org.softcits.cn.mapper.YesterdayMapper;
import org.softcits.cn.model.Yesterday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class YesterdayServiceImpl implements YesterdayService {
	
	@Autowired
	private YesterdayMapper yesterdayMapper;

	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NESTED)
	@Override
	public Integer insert(Yesterday yesterday) {
		if(yesterday.getCid() == 4) {
			throw new RuntimeException("Exeception Drill");
		}
		return yesterdayMapper.insert(yesterday);
	}

}