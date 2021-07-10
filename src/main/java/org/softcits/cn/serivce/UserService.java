package org.softcits.cn.serivce;

import org.softcits.cn.model.User;

public interface UserService {


    public Integer insert(User user);
    public User get(String uname);
}
