package com.hz.service.impl;

import com.hz.pojo.User;
import com.hz.mapper.UserMapper;
import com.hz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hz.utils.JsonMassage;
import com.hz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public JsonMassage login(String uname, String upwd){
        //调用dao层  获得登录对象
        User user = userMapper.login(uname, upwd);
        if(user!=null){
            String token = UUID.randomUUID().toString().replace("-","");
            String hostAddress = null;
            try {
                hostAddress = InetAddress.getLocalHost().getHostAddress();
            }catch (Exception e) {
                e.printStackTrace();
            }
            user.setUserFinallyip(hostAddress);
            userMapper.updateById(user);
            redisUtil.setStrJson("login:"+token,user,null);
            return  new JsonMassage(200,"登录成功",0,token);
        }else{
            //登录失败
            return  new JsonMassage(250,"登录失败",1,null);
        }
    }
}
