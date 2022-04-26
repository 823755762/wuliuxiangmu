package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Warehouse implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 仓库id
     */
    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Long warehouseId;

    /**
     * 仓库类型（0冷冻，1危险品，2恒温）
     */
    private Integer warehouseType;

    /**
     * 仓库总容量（平米）
     */
    private Double warehouseSvolume;

    /**
     * 仓库剩余容量
     */
    private Double warehouseResidue;

    /**
     * 仓库编号（A，B，C）
     */
    private String warehouseNumber;

    /**
     * 仓库高度
     */
    private Double warehouseHighly;

    /**
     * 仓库状态 1正常  2已满  3维修
     */
    private Integer warehouseState;

    /**
     * 备注
     */
    private String warehouseNote;

    /**
     * 逻辑删除0-->正常 1-->删除 ==默认0==
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
