package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.CityForecast;
import org.softcits.cn.model.Yesterday;
@Mapper
public interface YesterdayMapper {

	Integer insert(Yesterday yesterday);
}
