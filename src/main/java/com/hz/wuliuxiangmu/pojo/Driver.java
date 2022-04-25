package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Driver {

  private long driverId;
  private String driverName;
  private String driverIdcard;
  private String driverPhone;
  private String driverLicensePhoto;
  private long driverType;
  private long driverSex;
  private long driverAge;
  private String driverAddress;
  private long driverAttributionId;
  private java.sql.Timestamp driverLicenseExpirationTime;
  private long driverState;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getDriverId() {
    return driverId;
  }

  public void setDriverId(long driverId) {
    this.driverId = driverId;
  }


  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }


  public String getDriverIdcard() {
    return driverIdcard;
  }

  public void setDriverIdcard(String driverIdcard) {
    this.driverIdcard = driverIdcard;
  }


  public String getDriverPhone() {
    return driverPhone;
  }

  public void setDriverPhone(String driverPhone) {
    this.driverPhone = driverPhone;
  }


  public String getDriverLicensePhoto() {
    return driverLicensePhoto;
  }

  public void setDriverLicensePhoto(String driverLicensePhoto) {
    this.driverLicensePhoto = driverLicensePhoto;
  }


  public long getDriverType() {
    return driverType;
  }

  public void setDriverType(long driverType) {
    this.driverType = driverType;
  }


  public long getDriverSex() {
    return driverSex;
  }

  public void setDriverSex(long driverSex) {
    this.driverSex = driverSex;
  }


  public long getDriverAge() {
    return driverAge;
  }

  public void setDriverAge(long driverAge) {
    this.driverAge = driverAge;
  }


  public String getDriverAddress() {
    return driverAddress;
  }

  public void setDriverAddress(String driverAddress) {
    this.driverAddress = driverAddress;
  }


  public long getDriverAttributionId() {
    return driverAttributionId;
  }

  public void setDriverAttributionId(long driverAttributionId) {
    this.driverAttributionId = driverAttributionId;
  }


  public java.sql.Timestamp getDriverLicenseExpirationTime() {
    return driverLicenseExpirationTime;
  }

  public void setDriverLicenseExpirationTime(java.sql.Timestamp driverLicenseExpirationTime) {
    this.driverLicenseExpirationTime = driverLicenseExpirationTime;
  }


  public long getDriverState() {
    return driverState;
  }

  public void setDriverState(long driverState) {
    this.driverState = driverState;
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
