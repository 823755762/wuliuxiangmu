package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.mapper.AuthorityMapper;
import com.hz.mapper.AuthorityRoleMapper;
import com.hz.mapper.RoleMapper;
import com.hz.pojo.Authority;
import com.hz.pojo.AuthorityRole;
import com.hz.pojo.Role;
import com.hz.pojo.User;
import com.hz.service.RoleService;
import com.hz.utils.JsonMassage;
import com.hz.utils.RedisUtil;
import com.hz.utils.ResultJson;
import com.sun.tracing.ProviderName;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    public RoleService roleService;
    @Autowired
    public AuthorityMapper authorityMapper;
    @Autowired
    public RoleMapper roleMapper;
    @Autowired
    public AuthorityRoleMapper authorityRoleMapper;
    @RequestMapping("/list")
    public JsonMassage<List<Role>> list(String roleSupperAdmin){
        List<Role> listRole = roleService.findListRole(roleSupperAdmin);
        JsonMassage<List<Role>> jsonMassage = new JsonMassage<List<Role>>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("OK");
        jsonMassage.setData(listRole);
        return jsonMassage;
    }
    @RequestMapping("/update")
    public ResultJson updateRole(Role role){
        //到redis中找对象
        Object obj = redisUtil
                .getStrJson("userToken" , User.class);
        if (obj != null) {
            User user = (User) obj;
            role.setRoleModifier(user.getUserName());
        }
        int i = roleService.updateRole(role);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }
    @RequestMapping("/add")
    public ResultJson addRole(Role role) {
        //到redis中找对象
        Object obj = redisUtil.getStrJson("userToken",User.class);
        if (obj != null) {
            User user = (User) obj;
            role.setRoleCreator(user.getUserName());
            role.setRoleModifier(user.getUserName());
        }
        int i = roleService.addRole(role);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }
    @RequestMapping("/delete")
    public ResultJson roleDelete(Role role){
        ResultJson resultJson = new ResultJson(roleService.deleteRole(role));
        resultJson.setCode(200);
        return resultJson;
    }
    @RequestMapping("/updateRoleMenu")
    public ResultJson updateRoleMenu(Long roleId,String ids){
        int i = 0;
        Role role = roleMapper.selectById(roleId);
        QueryWrapper<AuthorityRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        List<AuthorityRole> authorityRoles = authorityRoleMapper.selectList(queryWrapper);
        for (AuthorityRole authorityRole : authorityRoles) {
            Authority authority = authorityMapper.selectById(authorityRole.getAuthorityId());
            authority.setMenuIds(ids);
            i=authorityMapper.updateById(authority);
        }
        role.setRoleIds(ids);
        int i1 = roleMapper.updateById(role);
        System.out.println(ids);
        ResultJson resultJson = new ResultJson(i1);
        resultJson.setCode(200);
        return resultJson;
    }
}

