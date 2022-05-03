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
public class Equipment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 设备ID
     */
    @TableId(value = "equipment_id", type = IdType.AUTO)
    private Long equipmentId;

    /**
     * 设备编号
     */
    private Long equipmentSerial;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备类型（0车辆检测、1仓库...）
     */
    private Integer equipmentType;

    /**
     * 设备外联ID（0车辆ID、1仓库位置ID...）
     */
    private Long equipmentConnectId;

    /**
     * 参数1
     */
    private String equipmentParameter1;

    /**
     * 参数2
     */
    private String equipmentParameter2;

    /**
     * 参数3
     */
    private String equipmentParameter3;

    /**
     * 是否正常（0是、1否）
     */
    private Integer equipmentStatus;

    /**
     * 备注
     */
    private String equipmentRemarks;

    /**
     * 逻辑删除0-->正常 1-->删除 ==
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
