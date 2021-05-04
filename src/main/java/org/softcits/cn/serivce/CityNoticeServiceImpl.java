package org.softcits.cn.serivce;

import org.softcits.cn.mapper.CityNoticeMapper;
import org.softcits.cn.model.CityNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityNoticeServiceImpl implements CityNoticeService {	
	@Autowired
	private CityNoticeMapper cityNoticeMapper; 
	
	public CityNotice getCityNoticeByCityId(String city_id) {
		return cityNoticeMapper.getCityNoticeByCityId(city_id);
	}
}
