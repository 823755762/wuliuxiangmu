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
public class Driver implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 权限ID
     */
    @TableId(value = "driver_id", type = IdType.AUTO)
    private Long driverId;

    /**
     * 姓名
     */
    private String driverName;

    /**
     * 身份证号
     */
    @TableField("driver_Idcard")
    private String driverIdcard;

    /**
     * 手机号
     */
    private String driverPhone;

    /**
     * 驾照（照片）
     */
    private String driverLicensePhoto;

    /**
     * 类型（0主驾  1副驾）
     */
    private Integer driverType;

    /**
     * 性别
     */
    private Integer driverSex;

    /**
     * 年龄
     */
    private Integer driverAge;

    /**
     * 司机地址
     */
    private String driverAddress;

    /**
     * 所属车辆ID
     */
    private Long driverAttributionId;

    /**
     * 驾照到期时间
     */
    private String driverLicenseExpirationTime;

    /**
     * 状态（  0.休息 1.离岗 ）
     */
    private Integer driverState;

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
