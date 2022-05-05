package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.AuthorityMapper;
import com.hz.mapper.RoleMapper;
import com.hz.pojo.Authority;
import com.hz.pojo.Role;
import com.hz.pojo.User;
import com.hz.service.UserService;
import com.hz.utils.JsonMassage;
import com.hz.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 多条件组合模糊查询+翻页
     *
     * @param pageNo     当前页
     * @param pageSize   每页显示的行数
     * @param userName   根据名字模糊查询
     * @param start_time 开始时间
     * @param end_time   结束时间
     * @return
     */
    @PostMapping("/login")
    public JsonMassage login(String username, String password){
        //调用业务层
        JsonMassage<String> login = userService.login(username, password);

        return login;
    }
    @Bean
    public void login(){
        List<Authority> authorities = authorityMapper.selectList(null);
        redisUtil.setStrJson("functions",authorities,null);
    }
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<User>> userList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String userName,
            String start_time,
            String end_time
    ) {
        QueryWrapper<User> queryWrap = new QueryWrapper<User>();
        if ("" != userName && userName != null) {
            queryWrap.like("user_name", userName);
        }
        if ("" != start_time && start_time != null) {

            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + start_time + " ','%Y-%m-%d')");
        }
        if ("" != end_time && end_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + end_time + " ','%Y-%m-%d')");

        }
        queryWrap.orderByDesc("create_time");
        Page<User> page = new Page<User>(pageNo, pageSize);
        Page<User> list = userService.page(page, queryWrap);
        JsonMassage<List<User>> jsonMassage = new JsonMassage<List<User>>(200, "ok", Math.toIntExact(page.getTotal()), list.getRecords());
        return jsonMassage;
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> insertUser(User user) {
        //到redis中找对象
        Object obj = redisUtil.getStrJson("userToken",User.class);
        if (obj != null) {
            User user1 = (User) obj;
            user.setUserCreationId(user1.getUserId());
        }
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        user.setCreateTime(format);
        boolean save = userService.save(user);
        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", null, null);
        return jsonMas;
    }

    /**
     * 根据id查询用户信息 ，返回页面
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectUserById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<User> selectUserById(Integer userId) {
        User user = userService.getById(userId);
        JsonMassage<User> jsonMas = new JsonMassage<User>(200, "查询成功", null, user);
        return jsonMas;
    }

    /**
     * 根据id修改状态
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateUserById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> upState(User user) {
        //到redis中找对象
        Object obj = redisUtil.getStrJson("userToken",User.class);
        if (obj != null) {
            User user1 = (User) obj;
            user.setUserCreationId(user1.getUserId());
        }
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        user.setUpdateTime(format);

        //穿对象修改用户信息
        boolean b = userService.updateById(user);

        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", null, null);
        return jsonMas;
    }

    /**
     * 根据id删除
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage deleteUserById(Integer userId) {
        boolean b = userService.removeById(userId);
        JsonMassage jsonMas = new JsonMassage<>(200, "ok", null, null);
        return jsonMas;
    }


}

