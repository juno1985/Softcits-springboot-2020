package org.softcits.cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.Forecast;

public interface ForecastMapper {
	
	List<Forecast> getForecastByCityId(@Param(value="cid")String cid);

}
