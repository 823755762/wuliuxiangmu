package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
    @TableId(value = "equipment_fire_id",type = IdType.AUTO)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date  createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
