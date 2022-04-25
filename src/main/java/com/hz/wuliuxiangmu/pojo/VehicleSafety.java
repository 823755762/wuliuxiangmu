package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class VehicleSafety {

  private long vehicleSafetyId;
  private long vehicleId;
  private long driverId;
  private long vehicleSafetyType;
  private java.sql.Timestamp vehicleSafetyTime;
  private long waybillInfoId;
  private String image;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getVehicleSafetyId() {
    return vehicleSafetyId;
  }

  public void setVehicleSafetyId(long vehicleSafetyId) {
    this.vehicleSafetyId = vehicleSafetyId;
  }


  public long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(long vehicleId) {
    this.vehicleId = vehicleId;
  }


  public long getDriverId() {
    return driverId;
  }

  public void setDriverId(long driverId) {
    this.driverId = driverId;
  }


  public long getVehicleSafetyType() {
    return vehicleSafetyType;
  }

  public void setVehicleSafetyType(long vehicleSafetyType) {
    this.vehicleSafetyType = vehicleSafetyType;
  }


  public java.sql.Timestamp getVehicleSafetyTime() {
    return vehicleSafetyTime;
  }

  public void setVehicleSafetyTime(java.sql.Timestamp vehicleSafetyTime) {
    this.vehicleSafetyTime = vehicleSafetyTime;
  }


  public long getWaybillInfoId() {
    return waybillInfoId;
  }

  public void setWaybillInfoId(long waybillInfoId) {
    this.waybillInfoId = waybillInfoId;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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
