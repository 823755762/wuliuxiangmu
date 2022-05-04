package com.hz.pojo;

import lombok.Data;

@Data
public class Storage {

  private long storageId;
  private long goodsId;
  private long inEmployeesId;
  private String storageInTime;
  private String storageOutTime;
  private long warehouseLocationId;
  private long storageStatus;
  private long employeesId;
  private String createTime;
  private String updateTime;

}
