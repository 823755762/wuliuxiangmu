package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class EquipmentLog {

  private long equipmentLogId;
  private java.sql.Timestamp equipmentLogTime;
  private long equipmentLogEquipmentId;
  private long employeeId;
  private String equipmentLogDescribe;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getEquipmentLogId() {
    return equipmentLogId;
  }

  public void setEquipmentLogId(long equipmentLogId) {
    this.equipmentLogId = equipmentLogId;
  }


  public java.sql.Timestamp getEquipmentLogTime() {
    return equipmentLogTime;
  }

  public void setEquipmentLogTime(java.sql.Timestamp equipmentLogTime) {
    this.equipmentLogTime = equipmentLogTime;
  }


  public long getEquipmentLogEquipmentId() {
    return equipmentLogEquipmentId;
  }

  public void setEquipmentLogEquipmentId(long equipmentLogEquipmentId) {
    this.equipmentLogEquipmentId = equipmentLogEquipmentId;
  }


  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }


  public String getEquipmentLogDescribe() {
    return equipmentLogDescribe;
  }

  public void setEquipmentLogDescribe(String equipmentLogDescribe) {
    this.equipmentLogDescribe = equipmentLogDescribe;
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
