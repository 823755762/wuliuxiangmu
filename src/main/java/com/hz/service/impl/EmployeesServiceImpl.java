package com.hz.service.impl;

import com.hz.pojo.Employees;
import com.hz.mapper.EmployeesMapper;
import com.hz.service.EmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements EmployeesService {

}
