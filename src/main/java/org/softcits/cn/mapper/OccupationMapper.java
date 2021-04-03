package org.softcits.cn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.softcits.cn.model.Occupation;
@Mapper
public interface OccupationMapper {

	Occupation getFirstOccupation();
	
	List<Occupation> getAllOccupations();
	
	Occupation getOccupationByName(String name);
	
	List<Occupation> getOccupationByNamePrefix(String prefix);
	
	int updateOccupationByName(@Param(value="_name")String name, @Param(value="_occp")String occp);
	
	int insertOccupation(String name, String occp);
	
	int deleteOccupation(Map<String, Object> map);
	
	Occupation getByNameAndOccupation(Occupation occp);
	
}
