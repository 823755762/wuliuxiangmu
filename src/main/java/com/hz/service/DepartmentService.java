package com.hz.service;

import com.hz.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
public interface DepartmentService extends IService<Department> {

    public List<Department> findAll(String DepartmentName,String CreateTime);

    public int findcount(String DepartmentName,String CreateTime);

    public int adddepar();

    public int deleteder(int id);

}
