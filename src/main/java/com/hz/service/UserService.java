package com.hz.service;

import com.hz.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hz.utils.JsonMassage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
public interface UserService extends IService<User> {
    public JsonMassage login(String uname, String upwd);
}
