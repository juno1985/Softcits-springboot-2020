package org.softcits.cn.serivce;

import java.util.List;
import org.softcits.cn.mapper.ForecastMapper;
import org.softcits.cn.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

	@Autowired
	private ForecastMapper forecastMapper;
	
	public List<Forecast> getForecastByCityId(String cid){
		List<Forecast> forecastList = forecastMapper.getForecastByCityId(cid);
		return forecastList;
	}
}
