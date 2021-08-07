package org.softcits.cn.serivce;

import org.softcits.cn.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {


    public Integer insert(User user);
    public User get(String uname);
    String login(String user, String password, HttpServletRequest request, HttpServletResponse response);
    boolean isLogin(String token);
}
