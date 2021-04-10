package org.softcits.cn.serivce;

import org.softcits.cn.mapper.CityForecastMapper;
import org.softcits.cn.model.CityForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CityForecastServiceImpl implements CityForecastService {

	@Autowired
	private CityForecastMapper cityForecastMapper;
	
	@Override
	public CityForecast getForcastWithCityByCityId(String cid) {
		CityForecast cityForecast = cityForecastMapper.getForcastWithCityByCityId(cid);
		return cityForecast;
	}

}
