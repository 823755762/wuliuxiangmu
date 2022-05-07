package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Storage {
    private static final long serialVersionUID=1L;
    /**
     * 状态ID
     */
    @TableId(value = "storage_id", type = IdType.AUTO)
    private long storageId;

    /**
     * 货物ID
     */
    private long goodsId;

    /**
     * 入库员工ID
     */
    private long inEmployeesId;

    /**
     * 入库时间
     */
    private String storageInTime;

    /**
     * 出库时间
     */
    private String storageOutTime;

    /**
     * 位置ID
     */
    private long warehouseLocationId;

    /**
     * 状态（0入库  1出库）
     */
    private long storageStatus;

    /**
     * 出库员工ID
     */
    private long employeesId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

}
