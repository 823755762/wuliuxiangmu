package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Warehouse {

  private long warehouseId;
  private long warehouseType;
  private double warehouseSvolume;
  private double warehouseResidue;
  private String warehouseNumber;
  private double warehouseHighly;
  private long warehouseState;
  private String warehouseNote;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(long warehouseId) {
    this.warehouseId = warehouseId;
  }


  public long getWarehouseType() {
    return warehouseType;
  }

  public void setWarehouseType(long warehouseType) {
    this.warehouseType = warehouseType;
  }


  public double getWarehouseSvolume() {
    return warehouseSvolume;
  }

  public void setWarehouseSvolume(double warehouseSvolume) {
    this.warehouseSvolume = warehouseSvolume;
  }


  public double getWarehouseResidue() {
    return warehouseResidue;
  }

  public void setWarehouseResidue(double warehouseResidue) {
    this.warehouseResidue = warehouseResidue;
  }


  public String getWarehouseNumber() {
    return warehouseNumber;
  }

  public void setWarehouseNumber(String warehouseNumber) {
    this.warehouseNumber = warehouseNumber;
  }


  public double getWarehouseHighly() {
    return warehouseHighly;
  }

  public void setWarehouseHighly(double warehouseHighly) {
    this.warehouseHighly = warehouseHighly;
  }


  public long getWarehouseState() {
    return warehouseState;
  }

  public void setWarehouseState(long warehouseState) {
    this.warehouseState = warehouseState;
  }


  public String getWarehouseNote() {
    return warehouseNote;
  }

  public void setWarehouseNote(String warehouseNote) {
    this.warehouseNote = warehouseNote;
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
