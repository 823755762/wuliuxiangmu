package com.hz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.mapper.*;
import com.hz.pojo.*;
import com.hz.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityRoleMapper authorityRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Override
    public String findMenuListByUserid(Long userId) {
        User user = userMapper.selectById(userId);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("role_id",user.getRoleId());
        Role role = roleMapper.selectOne(queryWrapper);

//        List<AuthorityRole> authorityRoles = authorityRoleMapper.selectList(queryWrapper);

//        List<String> menuIds = new ArrayList();

        /*
        for (AuthorityRole authorityRole : authorityRoles) {
            QueryWrapper<Authority> queryWrapper1 = new QueryWrapper<Authority>();
            queryWrapper1.eq("authority_id",authorityRole.getAuthorityId());
            List<Authority> authorities = authorityMapper.selectList(queryWrapper1);
            for (Authority authority : authorities) {
                menuIds.add(authority.getMenuIds());
            }
        }*/
/*        List<Menu> menus = new ArrayList<>();
       for (int i = 0; i < classNumber.size(); i++){
           QueryWrapper<Menu> queryWrapper1 = new QueryWrapper<Menu>();
           queryWrapper1.eq("menu_level",classNumber.get(i));*/


//           queryWrapper1.eq("menu_p_id",0);
//           List<Menu> menus1 = menuMapper.selectList(queryWrapper1);
//           menus.addAll(menus1);
//       }

//        LinkedHashSet<String> hashSet = new LinkedHashSet<>(menuIds);
//        menuIds = new ArrayList<>(hashSet);
        return role.getRoleIds();
    }

    @Override
    public List<Menu> getClildren(String menuId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
        queryWrapper.eq("menu_p_id",menuId);
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public List<String> findMenuIdsByroleId(Long roleId) {
        QueryWrapper<AuthorityRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        Role role = roleMapper.selectById(roleId);
        List<String> menuIds = new ArrayList<>();
        String roleIds = role.getRoleIds();
        System.out.println(roleIds);
        String[] split = roleIds.split(",");
        for (String str : split) {
            menuIds.add(str);
        }
        /*List<AuthorityRole> authorityRoles = authorityRoleMapper.selectList(queryWrapper);
        List<String> menuIds = new ArrayList<>()
        for (AuthorityRole authorityRole : authorityRoles) {
            QueryWrapper<Authority> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("authority_id",authorityRole.getAuthorityId());
            List<Authority> authorities = authorityMapper.selectList(queryWrapper2);
            for (Authority authority : authorities) {
                String menuIds1 = authority.getMenuIds();
                System.out.println(menuIds1);
                String[] split = menuIds1.split(",");
                for (String str : split) {
                    menuIds.add(str);
                }
                LinkedHashSet<String> hashSet = new LinkedHashSet<>(menuIds);
                menuIds = new ArrayList<>(hashSet);
            }
        }*/
        return menuIds;
    }

}
