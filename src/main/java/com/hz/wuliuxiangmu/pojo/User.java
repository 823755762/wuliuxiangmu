package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class User {

  private long userId;
  private String userName;
  private String userPwd;
  private long roleId;
  private java.sql.Timestamp userFinallytime;
  private String userFinallyip;
  private long userCreationId;
  private java.sql.Timestamp userCreationTime;
  private String userRemarkColumn;
  private long userState;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public java.sql.Timestamp getUserFinallytime() {
    return userFinallytime;
  }

  public void setUserFinallytime(java.sql.Timestamp userFinallytime) {
    this.userFinallytime = userFinallytime;
  }


  public String getUserFinallyip() {
    return userFinallyip;
  }

  public void setUserFinallyip(String userFinallyip) {
    this.userFinallyip = userFinallyip;
  }


  public long getUserCreationId() {
    return userCreationId;
  }

  public void setUserCreationId(long userCreationId) {
    this.userCreationId = userCreationId;
  }


  public java.sql.Timestamp getUserCreationTime() {
    return userCreationTime;
  }

  public void setUserCreationTime(java.sql.Timestamp userCreationTime) {
    this.userCreationTime = userCreationTime;
  }


  public String getUserRemarkColumn() {
    return userRemarkColumn;
  }

  public void setUserRemarkColumn(String userRemarkColumn) {
    this.userRemarkColumn = userRemarkColumn;
  }


  public long getUserState() {
    return userState;
  }

  public void setUserState(long userState) {
    this.userState = userState;
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
