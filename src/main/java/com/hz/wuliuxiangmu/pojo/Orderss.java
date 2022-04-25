package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Orderss {

  private long orderId;
  private long vehicleId;
  private long waybillId;
  private String orderFrom;
  private String clientName;
  private String clientPhone;
  private String clientIdNumber;
  private double prepaymentAmount;
  private double remainingAmount;
  private String orderFromTitle;
  private String orderFromX;
  private String orderFromY;
  private String acceptName;
  private String acceptPhone;
  private String contractPicture;
  private long orderState;
  private String orderFinishTitle;
  private String orderFinishX;
  private String orderFinishY;
  private String orderMileage;
  private double orderSpendTime;
  private double orderEstimatedAmount;
  private double orderActualAmount;
  private long adminId;
  private String orderRemarks;
  private java.sql.Timestamp orderDeliveryTime;
  private java.sql.Timestamp orderArrivalTime;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(long vehicleId) {
    this.vehicleId = vehicleId;
  }


  public long getWaybillId() {
    return waybillId;
  }

  public void setWaybillId(long waybillId) {
    this.waybillId = waybillId;
  }


  public String getOrderFrom() {
    return orderFrom;
  }

  public void setOrderFrom(String orderFrom) {
    this.orderFrom = orderFrom;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }


  public String getClientIdNumber() {
    return clientIdNumber;
  }

  public void setClientIdNumber(String clientIdNumber) {
    this.clientIdNumber = clientIdNumber;
  }


  public double getPrepaymentAmount() {
    return prepaymentAmount;
  }

  public void setPrepaymentAmount(double prepaymentAmount) {
    this.prepaymentAmount = prepaymentAmount;
  }


  public double getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(double remainingAmount) {
    this.remainingAmount = remainingAmount;
  }


  public String getOrderFromTitle() {
    return orderFromTitle;
  }

  public void setOrderFromTitle(String orderFromTitle) {
    this.orderFromTitle = orderFromTitle;
  }


  public String getOrderFromX() {
    return orderFromX;
  }

  public void setOrderFromX(String orderFromX) {
    this.orderFromX = orderFromX;
  }


  public String getOrderFromY() {
    return orderFromY;
  }

  public void setOrderFromY(String orderFromY) {
    this.orderFromY = orderFromY;
  }


  public String getAcceptName() {
    return acceptName;
  }

  public void setAcceptName(String acceptName) {
    this.acceptName = acceptName;
  }


  public String getAcceptPhone() {
    return acceptPhone;
  }

  public void setAcceptPhone(String acceptPhone) {
    this.acceptPhone = acceptPhone;
  }


  public String getContractPicture() {
    return contractPicture;
  }

  public void setContractPicture(String contractPicture) {
    this.contractPicture = contractPicture;
  }


  public long getOrderState() {
    return orderState;
  }

  public void setOrderState(long orderState) {
    this.orderState = orderState;
  }


  public String getOrderFinishTitle() {
    return orderFinishTitle;
  }

  public void setOrderFinishTitle(String orderFinishTitle) {
    this.orderFinishTitle = orderFinishTitle;
  }


  public String getOrderFinishX() {
    return orderFinishX;
  }

  public void setOrderFinishX(String orderFinishX) {
    this.orderFinishX = orderFinishX;
  }


  public String getOrderFinishY() {
    return orderFinishY;
  }

  public void setOrderFinishY(String orderFinishY) {
    this.orderFinishY = orderFinishY;
  }


  public String getOrderMileage() {
    return orderMileage;
  }

  public void setOrderMileage(String orderMileage) {
    this.orderMileage = orderMileage;
  }


  public double getOrderSpendTime() {
    return orderSpendTime;
  }

  public void setOrderSpendTime(double orderSpendTime) {
    this.orderSpendTime = orderSpendTime;
  }


  public double getOrderEstimatedAmount() {
    return orderEstimatedAmount;
  }

  public void setOrderEstimatedAmount(double orderEstimatedAmount) {
    this.orderEstimatedAmount = orderEstimatedAmount;
  }


  public double getOrderActualAmount() {
    return orderActualAmount;
  }

  public void setOrderActualAmount(double orderActualAmount) {
    this.orderActualAmount = orderActualAmount;
  }


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public String getOrderRemarks() {
    return orderRemarks;
  }

  public void setOrderRemarks(String orderRemarks) {
    this.orderRemarks = orderRemarks;
  }


  public java.sql.Timestamp getOrderDeliveryTime() {
    return orderDeliveryTime;
  }

  public void setOrderDeliveryTime(java.sql.Timestamp orderDeliveryTime) {
    this.orderDeliveryTime = orderDeliveryTime;
  }


  public java.sql.Timestamp getOrderArrivalTime() {
    return orderArrivalTime;
  }

  public void setOrderArrivalTime(java.sql.Timestamp orderArrivalTime) {
    this.orderArrivalTime = orderArrivalTime;
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
