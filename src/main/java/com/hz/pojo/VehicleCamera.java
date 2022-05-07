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
public class VehicleCamera implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 车辆监控id
     */
    @TableId(value = "vehicle_camera_id", type = IdType.AUTO)
    private Long vehicleCameraId;

    /**
     * 监控编号
     */
    private String vehicleCameraName;

    /**
     * 车辆id
     */
    private String vehicleId;

    /**
     * 参数1
     */
    private String vehicleCameraParam1;

    /**
     * 参数2
     */
    private String vehicleCameraParam2;

    /**
     * 参数3
     */
    private String vehicleCameraParam3;

    /**
     * 监控状态: 0开1关(默认0)
     */
    private Integer vehicleCameraStatus;

    /**
     * 0存在1删除（默认0）
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
