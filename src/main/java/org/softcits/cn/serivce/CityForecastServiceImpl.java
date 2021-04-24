package org.softcits.cn.serivce;

import org.softcits.cn.mapper.CityForecastMapper;
import org.softcits.cn.model.CityForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CityForecastServiceImpl implements CityForecastService {

	@Autowired
	private CityForecastMapper cityForecastMapper;
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public CityForecast getForcastWithCityByCityId(String cid) {
		CityForecast cityForecast = cityForecastMapper.getForcastWithCityByCityId(cid);
		return cityForecast;
	}

}
