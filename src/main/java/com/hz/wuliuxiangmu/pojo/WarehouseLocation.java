package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class WarehouseLocation {

  private long warehouseLocationId;
  private long warehouseId;
  private String warehouseLocationNumber;
  private double warehouseLocationPositionCapacity;
  private long warehouseLocationState;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getWarehouseLocationId() {
    return warehouseLocationId;
  }

  public void setWarehouseLocationId(long warehouseLocationId) {
    this.warehouseLocationId = warehouseLocationId;
  }


  public long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(long warehouseId) {
    this.warehouseId = warehouseId;
  }


  public String getWarehouseLocationNumber() {
    return warehouseLocationNumber;
  }

  public void setWarehouseLocationNumber(String warehouseLocationNumber) {
    this.warehouseLocationNumber = warehouseLocationNumber;
  }


  public double getWarehouseLocationPositionCapacity() {
    return warehouseLocationPositionCapacity;
  }

  public void setWarehouseLocationPositionCapacity(double warehouseLocationPositionCapacity) {
    this.warehouseLocationPositionCapacity = warehouseLocationPositionCapacity;
  }


  public long getWarehouseLocationState() {
    return warehouseLocationState;
  }

  public void setWarehouseLocationState(long warehouseLocationState) {
    this.warehouseLocationState = warehouseLocationState;
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
