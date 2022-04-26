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
     * 车辆状态（0.空闲 ，3运送中  5.维修  6.报废）
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
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
