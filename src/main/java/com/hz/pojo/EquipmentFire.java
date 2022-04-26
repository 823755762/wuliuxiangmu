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
public class EquipmentFire implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 消防设备id
     */
    @TableId(value = "equipment_fire_id", type = IdType.AUTO)
    private Long equipmentFireId;

    /**
     * 设备名称
     */
    private String equipmentFireName;

    /**
     * 设备描述
     */
    private String equipmentFireDescribe;

    /**
     * 设备位置描述
     */
    private String equipmentFireLocationDescribe;

    /**
     * 设备管理员工id
     */
    private Long employeesId;

    /**
     * 仓库ID
     */
    private Integer warehouseId;

    /**
     * 逻辑删除0-->正常 1-->删除 
     */
    @TableLogic
    private Long deleted;

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
