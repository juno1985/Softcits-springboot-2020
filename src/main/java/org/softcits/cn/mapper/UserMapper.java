package org.softcits.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.softcits.cn.model.User;

@Mapper
public interface UserMapper {

	Integer insert(User u);

	User get(String uname);

}
