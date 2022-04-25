package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Authority {

  private long authorityId;
  private String authorityName;
  private String authorityPath;
  private String authorityLogo;
  private long authorityParentTopicId;
  private long authorityClass;
  private long authoritySort;
  private java.sql.Timestamp roleId;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;



}
