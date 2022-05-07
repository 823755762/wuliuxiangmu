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
public class Vehicle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 车辆信息表id
     */
    @TableId(value = "vehicle_id", type = IdType.AUTO)
    private Long vehicleId;

    /**
     * 载重
     */
    private Double vehicleLoad;

    /**
     * 车牌号
     */
    private String vehicleCard;

    /**
     * 车辆照片
     */
  //@TableField(typeHandler= FastjsonTypeHandler.class)
   // @TableField(exist = false)
    private String vehicleVehiclePhoto;

    /**
     * 车辆类型id
     */
    private Integer vehicleTypeId;

    /**
     * 备注
     */
    private String vehicleNote;

    /**
     * 车辆状态（0.空闲 ，1运送中  2.维修  3.报废）
     */
    private Integer vehicleStatus;

    /**
     * 净重
     */
    private Integer netWeight;

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

    private  String url;


}
