package com.hz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.pojo.Department;
import com.hz.mapper.DepartmentMapper;
import com.hz.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> findAll(String DepartmentName,String CreateTime){
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        if(DepartmentName!=null){
            wrapper.like("DepartmentName","DepartmentName");
        }
        if(CreateTime!=null){
            wrapper.like("CreateTime","CreateTime");
        }
        if(wrapper!=null){
            List<Department> list=departmentMapper.selectList(wrapper);
        }
        List<Department> list=departmentMapper.selectList(null);
        return list;
    }

    public int findcount(String DepartmentName,String CreateTime){
        QueryWrapper<Department> wrapper=new QueryWrapper<>();
        if(DepartmentName!=null){
            wrapper.like("DepartmentName","DepartmentName");
        }
        if(CreateTime!=null){
            wrapper.like("CreateTime","CreateTime");
        }
        if(wrapper!=null){
            int i=departmentMapper.selectCount(wrapper);
        }
        int i=departmentMapper.selectCount(null);
        return i;
    }

    public int adddepar(){
        Department department =new Department();
        department.setDepartmentName("财务管理");
        department.setDeleted(0);
        department.setUpdateTime("2012-12-26");
        department.setCreateTime("2012-11-11");
        int i=departmentMapper.insert(department);
        return i;
    }

    public int deleteder(int id){
        int i=departmentMapper.deleteById(id);
        return i;
    }


}
