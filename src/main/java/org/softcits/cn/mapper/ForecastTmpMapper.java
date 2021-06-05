package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.Forecast;

@Mapper
public interface ForecastTmpMapper {
	
	Integer insert(Forecast forecast);
	void updateFromTmpTable();
}
