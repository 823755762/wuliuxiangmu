package com.sample;


public class Authority {

  private long authorityId;
  private String authorityName;
  private String authorityPath;
  private String authorityLogo;
  private long authorityParentTopicId;
  private long authorityClass;
  private long authoritySort;
  private long roleId;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getAuthorityId() {
    return authorityId;
  }

  public void setAuthorityId(long authorityId) {
    this.authorityId = authorityId;
  }


  public String getAuthorityName() {
    return authorityName;
  }

  public void setAuthorityName(String authorityName) {
    this.authorityName = authorityName;
  }


  public String getAuthorityPath() {
    return authorityPath;
  }

  public void setAuthorityPath(String authorityPath) {
    this.authorityPath = authorityPath;
  }


  public String getAuthorityLogo() {
    return authorityLogo;
  }

  public void setAuthorityLogo(String authorityLogo) {
    this.authorityLogo = authorityLogo;
  }


  public long getAuthorityParentTopicId() {
    return authorityParentTopicId;
  }

  public void setAuthorityParentTopicId(long authorityParentTopicId) {
    this.authorityParentTopicId = authorityParentTopicId;
  }


  public long getAuthorityClass() {
    return authorityClass;
  }

  public void setAuthorityClass(long authorityClass) {
    this.authorityClass = authorityClass;
  }


  public long getAuthoritySort() {
    return authoritySort;
  }

  public void setAuthoritySort(long authoritySort) {
    this.authoritySort = authoritySort;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
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
