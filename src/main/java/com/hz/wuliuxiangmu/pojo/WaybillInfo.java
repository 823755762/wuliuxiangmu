package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class WaybillInfo {

  private long waybillInfoId;
  private long goodsId;
  private long waybillInfoPredictArrivetime;
  private String waybillInfoSpendTime;
  private String waybillInfoFinallyX;
  private String waybillInfoFinallyY;
  private java.sql.Timestamp createTime;


  public long getWaybillInfoId() {
    return waybillInfoId;
  }

  public void setWaybillInfoId(long waybillInfoId) {
    this.waybillInfoId = waybillInfoId;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getWaybillInfoPredictArrivetime() {
    return waybillInfoPredictArrivetime;
  }

  public void setWaybillInfoPredictArrivetime(long waybillInfoPredictArrivetime) {
    this.waybillInfoPredictArrivetime = waybillInfoPredictArrivetime;
  }


  public String getWaybillInfoSpendTime() {
    return waybillInfoSpendTime;
  }

  public void setWaybillInfoSpendTime(String waybillInfoSpendTime) {
    this.waybillInfoSpendTime = waybillInfoSpendTime;
  }


  public String getWaybillInfoFinallyX() {
    return waybillInfoFinallyX;
  }

  public void setWaybillInfoFinallyX(String waybillInfoFinallyX) {
    this.waybillInfoFinallyX = waybillInfoFinallyX;
  }


  public String getWaybillInfoFinallyY() {
    return waybillInfoFinallyY;
  }

  public void setWaybillInfoFinallyY(String waybillInfoFinallyY) {
    this.waybillInfoFinallyY = waybillInfoFinallyY;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
