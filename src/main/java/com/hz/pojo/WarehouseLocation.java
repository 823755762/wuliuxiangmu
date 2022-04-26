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
public class WarehouseLocation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 位置ID
     */
    @TableId(value = "warehouse_location_id", type = IdType.AUTO)
    private Long warehouseLocationId;

    /**
     * 所属仓库ID
     */
    private Long warehouseId;

    /**
     * 位置编号（A，B，C）
     */
    private String warehouseLocationNumber;

    /**
     * 位置容量（平米）
     */
    private Double warehouseLocationPositionCapacity;

    /**
     * 状态（0空余 1已满 2暂停使用）
     */
    private Integer warehouseLocationState;

    /**
     * 逻辑删除0-->正常 1-->删除 
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
