package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class VehicleType implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 车辆类型id
     */
    @TableId(value = "vehicle_type_id", type = IdType.AUTO)
    private Integer vehicleTypeId;

    /**
     * 车辆类型名称
     */
    private String vehicleTypeName;

    /**
     * 类型说明
     */
    private Integer vehicleTypeWeight;


}
