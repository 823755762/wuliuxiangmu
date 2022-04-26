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
public class EquipmentLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 排查id
     */
    @TableId(value = "equipment_log_id", type = IdType.AUTO)
    private Long equipmentLogId;

    /**
     * 排查时间
     */
    private Date equipmentLogTime;

    /**
     * 设备id
     */
    private Long equipmentLogEquipmentId;

    /**
     * 排查员工id
     */
    private Long employeeId;

    /**
     * 排查信息描述
     */
    private String equipmentLogDescribe;

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
