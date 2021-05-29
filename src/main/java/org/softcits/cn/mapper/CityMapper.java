package org.softcits.cn.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.City;
import org.softcits.cn.pojo.CityPojo;

@Mapper
public interface CityMapper {
	
	Integer insertCity(CityPojo cityPojo);
	
	City getCityByCityId(@Param(value="cityId")String city_id);

}
