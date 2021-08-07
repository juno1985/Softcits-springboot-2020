package org.softcits.cn.serivce;

import org.softcits.cn.mapper.UserMapper;
import org.softcits.cn.model.User;
import org.softcits.cn.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${url.weather.token}")
    private String COOKIE_NAME;

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

    @Override
    public String login(String user, String password, HttpServletRequest request, HttpServletResponse response) {
        User userDB = userMapper.get(user);
        //登录失败
        if(password == null || userDB == null || !DigestUtils.md5DigestAsHex(password.getBytes()).equals(userDB.getPasswd()))
        {
            return null;
        }
        //登录成功
        //生成token
        String token = UUID.randomUUID().toString();
        //写入redis
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(token, user, 60, TimeUnit.SECONDS);
        //写入cookie
        CookieUtil.setCookie(request, response, COOKIE_NAME, token);
        return user;
    }

    @Override
    public boolean isLogin(String token) {
       boolean isLogin = stringRedisTemplate.hasKey(token);
       //如果已经登录了，则刷新过期时间
       if(isLogin){
           stringRedisTemplate.expire(token, 60, TimeUnit.SECONDS);
       }
       return isLogin;
    }


}
