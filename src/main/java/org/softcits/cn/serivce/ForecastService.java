package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.model.Forecast;

public interface ForecastService {

	List<Forecast> getForecastByCityId(String cid);
	
	void insert(String cityId);
}
