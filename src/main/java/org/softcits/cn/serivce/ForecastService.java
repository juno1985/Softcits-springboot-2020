package org.softcits.cn.serivce;

import java.util.List;

import org.softcits.cn.model.Forecast;
import org.softcits.cn.pojo.ForecastPojo;

public interface ForecastService {

	List<Forecast> getForecastByCityId(String cid);

	void insert(List<ForecastPojo> list, Integer cityId);
}
