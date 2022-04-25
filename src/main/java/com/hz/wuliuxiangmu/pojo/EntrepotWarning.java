package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class EntrepotWarning {

  private long entrepotId;
  private String equipmentNumber;
  private String entrepotWarningDescribe;
  private long employeesId;
  private java.sql.Timestamp entrepotWarningTime;
  private long entrepotDispose;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getEntrepotId() {
    return entrepotId;
  }

  public void setEntrepotId(long entrepotId) {
    this.entrepotId = entrepotId;
  }


  public String getEquipmentNumber() {
    return equipmentNumber;
  }

  public void setEquipmentNumber(String equipmentNumber) {
    this.equipmentNumber = equipmentNumber;
  }


  public String getEntrepotWarningDescribe() {
    return entrepotWarningDescribe;
  }

  public void setEntrepotWarningDescribe(String entrepotWarningDescribe) {
    this.entrepotWarningDescribe = entrepotWarningDescribe;
  }


  public long getEmployeesId() {
    return employeesId;
  }

  public void setEmployeesId(long employeesId) {
    this.employeesId = employeesId;
  }


  public java.sql.Timestamp getEntrepotWarningTime() {
    return entrepotWarningTime;
  }

  public void setEntrepotWarningTime(java.sql.Timestamp entrepotWarningTime) {
    this.entrepotWarningTime = entrepotWarningTime;
  }


  public long getEntrepotDispose() {
    return entrepotDispose;
  }

  public void setEntrepotDispose(long entrepotDispose) {
    this.entrepotDispose = entrepotDispose;
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
