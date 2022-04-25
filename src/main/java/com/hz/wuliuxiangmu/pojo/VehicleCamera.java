package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class VehicleCamera {

  private long vehicleCameraId;
  private String vehicleCameraName;
  private String vehicleId;
  private String vehicleCameraParam1;
  private String vehicleCameraParam2;
  private String vehicleCameraParam3;
  private long vehicleCameraStatus;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getVehicleCameraId() {
    return vehicleCameraId;
  }

  public void setVehicleCameraId(long vehicleCameraId) {
    this.vehicleCameraId = vehicleCameraId;
  }


  public String getVehicleCameraName() {
    return vehicleCameraName;
  }

  public void setVehicleCameraName(String vehicleCameraName) {
    this.vehicleCameraName = vehicleCameraName;
  }


  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }


  public String getVehicleCameraParam1() {
    return vehicleCameraParam1;
  }

  public void setVehicleCameraParam1(String vehicleCameraParam1) {
    this.vehicleCameraParam1 = vehicleCameraParam1;
  }


  public String getVehicleCameraParam2() {
    return vehicleCameraParam2;
  }

  public void setVehicleCameraParam2(String vehicleCameraParam2) {
    this.vehicleCameraParam2 = vehicleCameraParam2;
  }


  public String getVehicleCameraParam3() {
    return vehicleCameraParam3;
  }

  public void setVehicleCameraParam3(String vehicleCameraParam3) {
    this.vehicleCameraParam3 = vehicleCameraParam3;
  }


  public long getVehicleCameraStatus() {
    return vehicleCameraStatus;
  }

  public void setVehicleCameraStatus(long vehicleCameraStatus) {
    this.vehicleCameraStatus = vehicleCameraStatus;
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
