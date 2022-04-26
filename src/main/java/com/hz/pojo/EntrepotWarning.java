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
public class EntrepotWarning implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 仓库安全预警信息表：ID
     */
    @TableId(value = "entrepot_id", type = IdType.AUTO)
    private Long entrepotId;

    /**
     * 设备编号
     */
    private String equipmentNumber;

    /**
     * 预警描述
     */
    private String entrepotWarningDescribe;

    /**
     * 处理人员工ID
     */
    private Long employeesId;

    /**
     * 预警时间
     */
    private Date entrepotWarningTime;

    /**
     * 是否处理（0未处理 1已处理
     */
    private Integer entrepotDispose;

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
