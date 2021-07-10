package org.softcits.cn.serivce;

import org.softcits.cn.mapper.UserMapper;
import org.softcits.cn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public Integer insert(User user) {
        if(user == null){
            throw new RuntimeException("User is null");
        }
        //密码加密
        String encrytPasswd = DigestUtils.md5DigestAsHex(user.getPasswd().getBytes());
        user.setPasswd(encrytPasswd);

        return userMapper.insert(user);
    }

    @Override
    public User get(String uname) {
        if(uname == null){
            throw new RuntimeException("User name cannot be null");
        }
        return userMapper.get(uname);
    }
}
