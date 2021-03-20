package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.Occupation;
@Mapper
public interface OccupationMapper {

	Occupation getFirstOccupation();
	
}
