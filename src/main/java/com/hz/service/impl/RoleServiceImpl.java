package com.hz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.pojo.Role;
import com.hz.mapper.RoleMapper;
import com.hz.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();
    @Override
    public List<Role> findListRole(String roleSupperAdmin) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        if (roleSupperAdmin != null&&!"".equals(roleSupperAdmin)) {
            queryWrapper.like("role_supper_admin",roleSupperAdmin);
        }

        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public int updateRole(Role role) {
        role.setUpdateTime(sdf.format(date));
        return roleMapper.updateById(role);
    }

    @Override
    public int addRole(Role role) {
        role.setUpdateTime(sdf.format(date));
        role.setCreateTime(sdf.format(date));
        role.setDeleted(0);
        return roleMapper.insert(role);
    }
}
