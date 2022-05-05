package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
public class WaybillInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 运单编号（唯一编号）
     */
    @TableId(value = "waybill_info_id", type = IdType.AUTO)
    private Long waybillInfoId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 位置名称
     */
    private String waybillInfoSpendTime;

    /**
     * 最后一次定位坐标latitude
     */
    private String waybillInfoFinallyX;

    /**
     * 最后一次定位坐标longitude
     */
    private String waybillInfoFinallyY;

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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
