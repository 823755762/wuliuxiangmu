package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Vehicle {

  private long vehicleId;
  private double vehicleLoad;
  private String vehicleCard;
  private String vehicleVehiclePhoto;
  private long vehicleTypeId;
  private String vehicleNote;
  private long vehicleStatus;
  private long netWeight;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(long vehicleId) {
    this.vehicleId = vehicleId;
  }


  public double getVehicleLoad() {
    return vehicleLoad;
  }

  public void setVehicleLoad(double vehicleLoad) {
    this.vehicleLoad = vehicleLoad;
  }


  public String getVehicleCard() {
    return vehicleCard;
  }

  public void setVehicleCard(String vehicleCard) {
    this.vehicleCard = vehicleCard;
  }


  public String getVehicleVehiclePhoto() {
    return vehicleVehiclePhoto;
  }

  public void setVehicleVehiclePhoto(String vehicleVehiclePhoto) {
    this.vehicleVehiclePhoto = vehicleVehiclePhoto;
  }


  public long getVehicleTypeId() {
    return vehicleTypeId;
  }

  public void setVehicleTypeId(long vehicleTypeId) {
    this.vehicleTypeId = vehicleTypeId;
  }


  public String getVehicleNote() {
    return vehicleNote;
  }

  public void setVehicleNote(String vehicleNote) {
    this.vehicleNote = vehicleNote;
  }


  public long getVehicleStatus() {
    return vehicleStatus;
  }

  public void setVehicleStatus(long vehicleStatus) {
    this.vehicleStatus = vehicleStatus;
  }


  public long getNetWeight() {
    return netWeight;
  }

  public void setNetWeight(long netWeight) {
    this.netWeight = netWeight;
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
