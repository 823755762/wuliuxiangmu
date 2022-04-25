package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class OrderStatusRecord {

  private long recordId;
  private long orderId;
  private String statusDescription;
  private java.sql.Timestamp time;
  private long userId;


  public long getRecordId() {
    return recordId;
  }

  public void setRecordId(long recordId) {
    this.recordId = recordId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getStatusDescription() {
    return statusDescription;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
