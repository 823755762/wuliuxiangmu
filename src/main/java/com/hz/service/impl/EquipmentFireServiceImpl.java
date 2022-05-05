package com.hz.service.impl;

import com.hz.pojo.EquipmentFire;
import com.hz.mapper.EquipmentFireMapper;
import com.hz.service.EquipmentFireService;
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
public class EquipmentFireServiceImpl extends ServiceImpl<EquipmentFireMapper, EquipmentFire> implements EquipmentFireService {

    @Autowired
    private EquipmentFireMapper equipmentFireMapper;


    @Override
    public List<EquipmentFire> findProviderList1(Integer curr_page, Integer page_size,String proName,String proDesc) {
        //计算偏移量 = (当前页-1)*每页显示条数
        Integer pyl = (curr_page-1)*page_size;
        return equipmentFireMapper.findProviderList1(pyl,page_size,proName,proDesc);
    }

    @Override
    public Integer findProviderListCount1(String proName,String proDesc) {
        return equipmentFireMapper.findProviderListCount1(proName,proDesc);
    }




}
