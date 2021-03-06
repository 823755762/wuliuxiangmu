package com.hz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
    private String userFinallytime;

    /**
     * 最后一次登录IP
     */
    private String userFinallyip;

    /**
     * 创建人ID
     */
    private Long userCreationId;



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

    private String createTime;

    /**
     * 修改时间
     */

    private String updateTime;


}
