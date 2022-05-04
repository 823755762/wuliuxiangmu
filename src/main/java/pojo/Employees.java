package pojo;

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
public class Employees implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 员工编号id
     */
    @TableId(value = "employees_id", type = IdType.AUTO)
    private Long employeesId;

    /**
     * 员工姓名
     */
    private String employeesName;

    /**
     * 员工性别 0男 1女
     */
    private Integer employeesSex;

    /**
     * 员工地址
     */
    private String employeesAddress;

    /**
     * 手机号
     */
    private String employeesPhone;

    /**
     * 员工身份证号
     */
    @TableField("employees_Idcard")
    private String employeesIdcard;

    /**
     * 密码
     */
    private String employeesPassword;

    /**
     * 入职时间
     */
    private String employeesInductiontime;

    /**
     * 创建时间
     */
    private String employeesCreateTime;

    /**
     * 创建人ID（管理员表）
     */
    private int adminId;

    /**
     * 状态（0在职  1离职  2休假 3禁用
     */
    private Integer employeesState;

    /**
     * 部门ID（部门表）
     */
    private Long departmentId;

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
