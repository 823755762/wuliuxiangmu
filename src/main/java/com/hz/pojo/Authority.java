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
public class Authority implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 权限ID
     */
    @TableId(value = "authority_id", type = IdType.AUTO)
    private Long authorityId;

    /**
     * 权限名称
     */
    private String authorityName;

    /**
     * 权限路径
     */
    private String authorityPath;

    /**
     * 权限图标
     */
    private String authorityLogo;

    /**
     * 父级ID
     */
    private Long authorityParentTopicId;

    /**
     * 等级
     */
    private Integer authorityClass;

    /**
     * 排序
     */
    private Integer authoritySort;

    /**
     * 角色id
     */
    private Date roleId;

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
