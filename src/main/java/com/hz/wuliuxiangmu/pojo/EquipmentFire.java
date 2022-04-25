package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class EquipmentFire {

  private long equipmentFireId;
  private String equipmentFireName;
  private String equipmentFireDescribe;
  private String equipmentFireLocationDescribe;
  private long employeesId;
  private long warehouseId;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getEquipmentFireId() {
    return equipmentFireId;
  }

  public void setEquipmentFireId(long equipmentFireId) {
    this.equipmentFireId = equipmentFireId;
  }


  public String getEquipmentFireName() {
    return equipmentFireName;
  }

  public void setEquipmentFireName(String equipmentFireName) {
    this.equipmentFireName = equipmentFireName;
  }


  public String getEquipmentFireDescribe() {
    return equipmentFireDescribe;
  }

  public void setEquipmentFireDescribe(String equipmentFireDescribe) {
    this.equipmentFireDescribe = equipmentFireDescribe;
  }


  public String getEquipmentFireLocationDescribe() {
    return equipmentFireLocationDescribe;
  }

  public void setEquipmentFireLocationDescribe(String equipmentFireLocationDescribe) {
    this.equipmentFireLocationDescribe = equipmentFireLocationDescribe;
  }


  public long getEmployeesId() {
    return employeesId;
  }

  public void setEmployeesId(long employeesId) {
    this.employeesId = employeesId;
  }


  public long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(long warehouseId) {
    this.warehouseId = warehouseId;
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
