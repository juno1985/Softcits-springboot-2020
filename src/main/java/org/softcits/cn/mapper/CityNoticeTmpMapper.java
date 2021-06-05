package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.Notice;

@Mapper
public interface CityNoticeTmpMapper {
	Integer insert(Notice notice);
}
