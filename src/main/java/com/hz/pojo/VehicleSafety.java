package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
public class VehicleSafety implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "vehicle_safety_id", type = IdType.AUTO)
    private Long vehicleSafetyId;

    /**
     * 车辆id
     */
    private Long vehicleId;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 类型（疲劳驾驶，违规驾驶，路线偏离）
     */
    private Integer vehicleSafetyType;


    /**
     * 运单编号
     */
    private Long waybillInfoId;

    /**
     * 抓拍照片
     */
    private String image;

    /**
     * 逻辑删除0-->正常 1-->删除 
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;


}
