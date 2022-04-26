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
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 管理员ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 管理员账号(员工姓名拼音)
     */
    private String userName;

    /**
     * o管理员密码
     */
    private String userPwd;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 最后一次登录时间
     */
    private Date userFinallytime;

    /**
     * 最后一次登录IP
     */
    private String userFinallyip;

    /**
     * 创建人ID
     */
    private Long userCreationId;

    /**
     * 创建时间
     */
    private Date userCreationTime;

    private String userRemarkColumn;

    /**
     * 状态
     */
    private Integer userState;

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
