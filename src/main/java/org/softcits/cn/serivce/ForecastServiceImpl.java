package org.softcits.cn.serivce;

import org.softcits.cn.mapper.CityMapper;
import org.softcits.cn.mapper.ForecastMapper;
import org.softcits.cn.mapper.ForecastTmpMapper;
import org.softcits.cn.model.Forecast;
import org.softcits.cn.pojo.ForecastPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	private ForecastTmpMapper forecasttmpMapper;
	@Autowired
	private ForecastMapper forecastMapper;
	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private RemoteDataService remoteDataService;
	
	public List<Forecast> getForecastByCityId(String cid){
		List<Forecast> forecastList = forecastMapper.getForecastByCityId(cid);
		return forecastList;
	}

	@Override
	public void insert(List<ForecastPojo> list, Integer cid, Boolean isTmpTable) {
		
		for(int i = 0; i < list.size(); i++) {
			ForecastPojo forecastPojo = list.get(i);
			Forecast forecast = new Forecast();
	
			forecast.setCid(cid);
			forecast.setDate(forecastPojo.getDate());
			forecast.setDay_order(i);
			forecast.setFengli(forecastPojo.getFengxiang());
			forecast.setFengxiang(forecastPojo.getFengxiang());
			forecast.setHigh(forecastPojo.getHigh());
			forecast.setLow(forecastPojo.getLow());
			forecast.setType(forecastPojo.getType());
			if(isTmpTable){
				forecasttmpMapper.insert(forecast);
			}else{
				forecastMapper.insert(forecast);
			}

		}
		
	}
}
