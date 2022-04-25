package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Employees {

  private long employeesId;
  private String employeesName;
  private long employeesSex;
  private String employeesAddress;
  private String employeesPhone;
  private String employeesIdcard;
  private String employeesPassword;
  private java.sql.Timestamp employeesInductiontime;
  private java.sql.Timestamp employeesCreateTime;
  private long adminId;
  private long employeesState;
  private long departmentId;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getEmployeesId() {
    return employeesId;
  }

  public void setEmployeesId(long employeesId) {
    this.employeesId = employeesId;
  }


  public String getEmployeesName() {
    return employeesName;
  }

  public void setEmployeesName(String employeesName) {
    this.employeesName = employeesName;
  }


  public long getEmployeesSex() {
    return employeesSex;
  }

  public void setEmployeesSex(long employeesSex) {
    this.employeesSex = employeesSex;
  }


  public String getEmployeesAddress() {
    return employeesAddress;
  }

  public void setEmployeesAddress(String employeesAddress) {
    this.employeesAddress = employeesAddress;
  }


  public String getEmployeesPhone() {
    return employeesPhone;
  }

  public void setEmployeesPhone(String employeesPhone) {
    this.employeesPhone = employeesPhone;
  }


  public String getEmployeesIdcard() {
    return employeesIdcard;
  }

  public void setEmployeesIdcard(String employeesIdcard) {
    this.employeesIdcard = employeesIdcard;
  }


  public String getEmployeesPassword() {
    return employeesPassword;
  }

  public void setEmployeesPassword(String employeesPassword) {
    this.employeesPassword = employeesPassword;
  }


  public java.sql.Timestamp getEmployeesInductiontime() {
    return employeesInductiontime;
  }

  public void setEmployeesInductiontime(java.sql.Timestamp employeesInductiontime) {
    this.employeesInductiontime = employeesInductiontime;
  }


  public java.sql.Timestamp getEmployeesCreateTime() {
    return employeesCreateTime;
  }

  public void setEmployeesCreateTime(java.sql.Timestamp employeesCreateTime) {
    this.employeesCreateTime = employeesCreateTime;
  }


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public long getEmployeesState() {
    return employeesState;
  }

  public void setEmployeesState(long employeesState) {
    this.employeesState = employeesState;
  }


  public long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(long departmentId) {
    this.departmentId = departmentId;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
