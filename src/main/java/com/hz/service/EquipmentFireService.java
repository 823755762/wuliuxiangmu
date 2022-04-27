package com.hz.service;

import com.hz.pojo.EquipmentFire;
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
public interface EquipmentFireService extends IService<EquipmentFire> {

    /**
     * 多条件组合查询
     * 翻页查询供应商信息
     * @param curr_page  当前页
     * @param page_size 每页显示条数
     * @return
     */
    public List<EquipmentFire> findProviderList1(
            Integer curr_page, Integer page_size,
            String proName,String proDesc
    );

    /**
     * 获得总条数
     * @return
     */
    public Integer findProviderListCount1(String proName,String proDesc);



}
