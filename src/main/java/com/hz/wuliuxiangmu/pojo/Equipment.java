package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Equipment {

  private long equipmentId;
  private long equipmentSerial;
  private String equipmentName;
  private long equipmentType;
  private long equipmentConnectId;
  private String equipmentParameter1;
  private String equipmentParameter2;
  private String equipmentParameter3;
  private java.sql.Timestamp equipmentTime;
  private long equipmentStatus;
  private String equipmentRemarks;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public long getEquipmentSerial() {
    return equipmentSerial;
  }

  public void setEquipmentSerial(long equipmentSerial) {
    this.equipmentSerial = equipmentSerial;
  }


  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }


  public long getEquipmentType() {
    return equipmentType;
  }

  public void setEquipmentType(long equipmentType) {
    this.equipmentType = equipmentType;
  }


  public long getEquipmentConnectId() {
    return equipmentConnectId;
  }

  public void setEquipmentConnectId(long equipmentConnectId) {
    this.equipmentConnectId = equipmentConnectId;
  }


  public String getEquipmentParameter1() {
    return equipmentParameter1;
  }

  public void setEquipmentParameter1(String equipmentParameter1) {
    this.equipmentParameter1 = equipmentParameter1;
  }


  public String getEquipmentParameter2() {
    return equipmentParameter2;
  }

  public void setEquipmentParameter2(String equipmentParameter2) {
    this.equipmentParameter2 = equipmentParameter2;
  }


  public String getEquipmentParameter3() {
    return equipmentParameter3;
  }

  public void setEquipmentParameter3(String equipmentParameter3) {
    this.equipmentParameter3 = equipmentParameter3;
  }


  public java.sql.Timestamp getEquipmentTime() {
    return equipmentTime;
  }

  public void setEquipmentTime(java.sql.Timestamp equipmentTime) {
    this.equipmentTime = equipmentTime;
  }


  public long getEquipmentStatus() {
    return equipmentStatus;
  }

  public void setEquipmentStatus(long equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }


  public String getEquipmentRemarks() {
    return equipmentRemarks;
  }

  public void setEquipmentRemarks(String equipmentRemarks) {
    this.equipmentRemarks = equipmentRemarks;
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
