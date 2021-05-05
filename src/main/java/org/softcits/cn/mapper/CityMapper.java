package org.softcits.cn.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.pojo.CityPojo;

@Mapper
public interface CityMapper {
	
	Integer insertCity(CityPojo cityPojo);

}
