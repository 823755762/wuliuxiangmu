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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            role.setRoleIds("1");
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
        int i2 = 0;
        Role role = roleMapper.selectById(roleId);
        role.setRoleIds(ids);
        String[] split = ids.split(",");
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        ResultJson resultJson = null;
        for (String string:split) {
            QueryWrapper<Authority> queryWrapper = new QueryWrapper();
            System.out.println("String:========================================"+string);
            queryWrapper.eq("menu_ids",string);
            Authority authority = authorityMapper.selectOne(queryWrapper);
            System.out.println("role.getRoleId():+++++++++++++++++++++++"+role.getRoleId());
            if (authority != null) {
                if (i2 == 0){
                    QueryWrapper<AuthorityRole> queryWrapper1 = new QueryWrapper();
                    queryWrapper1.eq("role_id",role.getRoleId());
                    i2 = authorityRoleMapper.delete(queryWrapper1);
                    resultJson= new ResultJson(i2);
                    resultJson.setCode(200);
                }
                AuthorityRole authorityRole = new AuthorityRole();
                authorityRole.setRoleId(role.getRoleId());
                authorityRole.setAuthorityId(authority.getAuthorityId());
                authorityRole.setCreateTime(format);
                authorityRole.setUpdateTime(format);
                List<AuthorityRole> authorityRoles = authorityRoleMapper.selectList(null);
                int insert = authorityRoleMapper.insert(authorityRole);
                if (insert == 0) {
                    resultJson.setCode(0);

                }


            }
        }
        int i1 = roleMapper.updateById(role);
        if (i1 > 0) {
            resultJson.setCode(200);
        }
        return resultJson;
    }
}

