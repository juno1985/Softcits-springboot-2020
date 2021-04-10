package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.CityForecast;
@Mapper
public interface CityForecastMapper {

	CityForecast getForcastWithCityByCityId(@Param(value="cid")String cid);
}
