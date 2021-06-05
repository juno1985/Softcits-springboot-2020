package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.Yesterday;

@Mapper
public interface YesterdayTmpMapper {

	Integer insert(Yesterday yesterday);
	void updateFromTmpTable();
}
