package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Goods {

  private long goodsId;
  private long goodsNumber;
  private String goodsCode;
  private long goodsTypeId;
  private String goodsValue;
  private long goodsUnitId;
  private String goodsDes;
  private long goodsIsStorage;
  private long orderId;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }


  public long getGoodsNumber() {
    return goodsNumber;
  }

  public void setGoodsNumber(long goodsNumber) {
    this.goodsNumber = goodsNumber;
  }


  public String getGoodsCode() {
    return goodsCode;
  }

  public void setGoodsCode(String goodsCode) {
    this.goodsCode = goodsCode;
  }


  public long getGoodsTypeId() {
    return goodsTypeId;
  }

  public void setGoodsTypeId(long goodsTypeId) {
    this.goodsTypeId = goodsTypeId;
  }


  public String getGoodsValue() {
    return goodsValue;
  }

  public void setGoodsValue(String goodsValue) {
    this.goodsValue = goodsValue;
  }


  public long getGoodsUnitId() {
    return goodsUnitId;
  }

  public void setGoodsUnitId(long goodsUnitId) {
    this.goodsUnitId = goodsUnitId;
  }


  public String getGoodsDes() {
    return goodsDes;
  }

  public void setGoodsDes(String goodsDes) {
    this.goodsDes = goodsDes;
  }


  public long getGoodsIsStorage() {
    return goodsIsStorage;
  }

  public void setGoodsIsStorage(long goodsIsStorage) {
    this.goodsIsStorage = goodsIsStorage;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
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
