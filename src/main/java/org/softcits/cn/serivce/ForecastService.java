package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.model.Forecast;

public interface ForecastService {

	public List<Forecast> getForecastByCityId(String cid);
}
