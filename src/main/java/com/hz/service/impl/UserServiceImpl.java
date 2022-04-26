package com.hz.service.impl;

import com.hz.pojo.User;
import com.hz.mapper.UserMapper;
import com.hz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
