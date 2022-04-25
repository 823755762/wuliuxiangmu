package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class System {

  private long systemId;
  private String systemName;
  private String systemDomain;
  private String systemSerialnumber;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getSystemId() {
    return systemId;
  }

  public void setSystemId(long systemId) {
    this.systemId = systemId;
  }


  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }


  public String getSystemDomain() {
    return systemDomain;
  }

  public void setSystemDomain(String systemDomain) {
    this.systemDomain = systemDomain;
  }


  public String getSystemSerialnumber() {
    return systemSerialnumber;
  }

  public void setSystemSerialnumber(String systemSerialnumber) {
    this.systemSerialnumber = systemSerialnumber;
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
