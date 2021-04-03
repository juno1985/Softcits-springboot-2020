package org.softcits.cn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.Occupation;
@Mapper
public interface OccupationMapper {

	Occupation getFirstOccupation();
	
	List<Occupation> getAllOccupations();
	
}
