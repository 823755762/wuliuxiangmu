package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Unit {

  private long unitId;
  private String unitName;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getUnitId() {
    return unitId;
  }

  public void setUnitId(long unitId) {
    this.unitId = unitId;
  }


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
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
