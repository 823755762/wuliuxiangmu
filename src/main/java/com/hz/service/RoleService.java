package com.hz.service;

import com.hz.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hz.utils.JsonMassage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
public interface RoleService extends IService<Role> {


    /**
     * 查询所有
     * @param roleSupperAdmin
     * @return
     */
    List<Role> findListRole(String roleSupperAdmin);

    /**
     * 修改角色
     * @param role
     * @return
     */
     int updateRole(Role role);

    /**
     * 添加角色
     * @param role
     * @return
     */
     int addRole(Role role);

    /**
     * 删除角色
     * @param role
     * @return
     */
     int deleteRole(Role role);
}
