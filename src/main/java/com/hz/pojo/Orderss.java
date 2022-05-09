package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class Orderss implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 车辆ID
     */
    private Long vehicleId;

    /**
     * 运单编号（唯一编码）
     */
    private Long waybillId;

    /**
     * 订单来源（相当于备注）
     */
    private String orderFrom;

    private String clientName;

    /**
     * 客户手机号
     */
    private String clientPhone;

    /**
     * 身份证号
     */
    private String clientIdNumber;

    /**
     * 预付金额
     */
    private BigDecimal prepaymentAmount;

    /**
     * 剩余金额
     */
    private BigDecimal remainingAmount;

    /**
     * 始发地名标题
     */
    private String orderFromTitle;

    /**
     * 始发地地址X
     */
    private String orderFromX;

    /**
     * 始发地地址y
     */
    private String orderFromY;

    /**
     * 收件人姓名
     */
    private String acceptName;

    /**
     * 收件人手机号
     */
    private String acceptPhone;

    /**
     * 合同图片
     */
    private String contractPicture;

    /**
     * 状态（0.待验货 1.已入库 3.运送中 4.已到达 5.已结束）
     */
    private int orderState;

    /**
     * 目的地标题
     */
    private String orderFinishTitle;

    /**
     * 目的地地址X
     */
    private String orderFinishX;

    /**
     * 目的地地址y
     */
    private String orderFinishY;

    /**
     * 公里数
     */
    private String orderMileage;

    /**
     * 付款总金额
     */
    private BigDecimal orderAllAmount;

    /**
     * 预估金额（系统自动计算）
     */
    private BigDecimal orderEstimatedAmount;

    /**
     * 实际金额（手动录入）
     */
    private BigDecimal orderActualAmount;

    /**
     * 操作人ID（管理员ID）
     */
    private Long userId;



    /**
     * 预计发货时间（客户预计）
     */
    private String orderDeliveryTime;

    /**
     * 预计到达时间（客户预计）
     */
    private String orderArrivalTime;

    /**
     * 逻辑删除0-->正常 1-->删除
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
