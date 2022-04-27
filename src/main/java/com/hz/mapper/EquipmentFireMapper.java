package com.hz.mapper;

import com.hz.pojo.EquipmentFire;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
public interface EquipmentFireMapper extends BaseMapper<EquipmentFire> {

    /**
     * 多条件组合查询
     * 翻页查询供应商信息
     * @param pyl  偏移量
     * @param page_size 每页显示条数
     * @return
     */
    public List<EquipmentFire> findBillList1(
            @Param("pyl") Integer pyl,
            @Param("page_size") Integer page_size,
            @Param("productName") String productName,
            @Param("productUnit") String productUnit
    );

    /**
     * 获得总条数
     * @return
     */
    public Integer findProviderListCount1(String proName,String proDesc);


}
