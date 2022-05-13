package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 货物id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Long goodsId;

    /**
     * 货物编号
     */
    private String goodsNumber;

    /**
     * 条纹码（待定）
     */
    private String goodsCode;

    /**
     * 货物类型ID
     */
    private Long goodsTypeId;

    /**
     * 值(重量)
     */
    private String goodsValue;
    /**
     * 体积，
     */
    private String goodsVolume;
    /**
     * 单位id
     */
    private Integer goodsUnitId;

    /**
     * 货物描述
     */
    private String goodsDes;

    /**
     * 是否需要入库（0是 1否）
     */
    private Integer goodsIsStorage;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 逻辑删除0-->正常 1-->删除 ==默认0==
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */

    private String createTime;

    /**
     * 修改时间
     */

    private String updateTime;


}
