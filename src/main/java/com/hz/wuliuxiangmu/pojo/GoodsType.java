package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class GoodsType {

  private long goodsTypeId;
  private String goodsTypeName;
  private long goodsTypeParentId;
  private String goodsTypeRemarks;
  private double goodsTypeMoney;
  private long deleted;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getGoodsTypeId() {
    return goodsTypeId;
  }

  public void setGoodsTypeId(long goodsTypeId) {
    this.goodsTypeId = goodsTypeId;
  }


  public String getGoodsTypeName() {
    return goodsTypeName;
  }

  public void setGoodsTypeName(String goodsTypeName) {
    this.goodsTypeName = goodsTypeName;
  }


  public long getGoodsTypeParentId() {
    return goodsTypeParentId;
  }

  public void setGoodsTypeParentId(long goodsTypeParentId) {
    this.goodsTypeParentId = goodsTypeParentId;
  }


  public String getGoodsTypeRemarks() {
    return goodsTypeRemarks;
  }

  public void setGoodsTypeRemarks(String goodsTypeRemarks) {
    this.goodsTypeRemarks = goodsTypeRemarks;
  }


  public double getGoodsTypeMoney() {
    return goodsTypeMoney;
  }

  public void setGoodsTypeMoney(double goodsTypeMoney) {
    this.goodsTypeMoney = goodsTypeMoney;
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
