package com.hz.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.mapper.AuthorityRoleMapper;
import com.hz.pojo.Authority;
import com.hz.pojo.AuthorityRole;
import com.hz.pojo.Role;
import com.hz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component("rbacAuthorityService")

public class RbacAuthorityService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AuthorityRoleMapper authorityRoleMapper;
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 获取当前请求的URI
        String requestURI = request.getServletPath();
        // 放开登录url
        if (requestURI.equals("/user/login")) {
            return true;
        }
        if (requestURI.equals("/user/loginOut")) {
            return true;
        }
        if (requestURI.equals("/user/exit")) {
            return true;
        }
        if (requestURI.equals("/menu/menu")) {
            return true;
        }
        if (requestURI.equals("/driver/driverList")) {
            return true;
        }
        //放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            request.setAttribute("code", "1");
            return false;
        }
        //到redis中找对象
        Object obj = redisUtil
                .getStrJson("login:" + token, User.class);
        redisUtil.setStrJson("userToken",obj,null);
        if (obj == null) {
            request.setAttribute("code", "1");
            return false;
        }

        User user = (User) obj;
        QueryWrapper<AuthorityRole> queryWrapper = new QueryWrapper<AuthorityRole>();
        queryWrapper.eq("role_id",user.getRoleId());
        List<AuthorityRole> authorityRoles = authorityRoleMapper.selectList(queryWrapper);
        String Stringfunctions = redisUtil.getStr("functions");
        List<Authority> functions1 = JSON.parseArray(Stringfunctions, Authority.class);

        /*
            1.启动项目是获得所有角色ID 与对应权限  存入redis
            2.根据角色ID  获得相应权限菜单集合 (控制到按钮)

            3.控制菜单  写controller路径
         */
        // 权限判断 利用token获取用户登录对象 获取角色ID 通过角色ID到redis中找角色对应 的权限集合
        Set<String> roles = new HashSet();

        for (AuthorityRole authorityRole:authorityRoles){
              for (Authority authority : functions1) {
                  if (authorityRole.getAuthorityId().equals(authority.getAuthorityId())){
                      roles.add("/"+authority.getAuthorityPath());
                  }
              }
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String url : roles) {
            if (antPathMatcher.match(url,request.getRequestURI())) {
                return true;
            }
        }
        if (!roles.contains(requestURI)) {
            request.setAttribute("code", "2");
            return false;
        }

        return true;
    }
}




