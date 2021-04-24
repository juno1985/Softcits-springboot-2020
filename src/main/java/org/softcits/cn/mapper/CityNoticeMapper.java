package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.CityNotice;
@Mapper
public interface CityNoticeMapper {

	CityNotice getCityNoticeByCityId(@Param(value="city_id") String city_id);
	
}
