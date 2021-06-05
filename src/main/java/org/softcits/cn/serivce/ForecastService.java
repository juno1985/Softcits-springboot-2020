package org.softcits.cn.serivce;

import org.softcits.cn.model.Forecast;
import org.softcits.cn.pojo.ForecastPojo;

import java.util.List;

public interface ForecastService {

	List<Forecast> getForecastByCityId(String cid);

	void insert(List<ForecastPojo> list, Integer cityId, Boolean isTempTable);
}
