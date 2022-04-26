package com.hz.pojo;

import java.math.BigDecimal;
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
public class VehicleToll implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 过路费收费id
     */
    @TableId(value = "vehicle_toll_id", type = IdType.AUTO)
    private Integer vehicleTollId;

    /**
     * 最大载重
     */
    private Double vehicleTollWeight;

    /**
     * 每公里价格
     */
    private BigDecimal vehicleTollPrice;


}
