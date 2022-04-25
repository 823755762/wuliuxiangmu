package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class VehicleType {

  private long vehicleTypeId;
  private String vehicleTypeName;
  private long vehicleTypeWeight;


  public long getVehicleTypeId() {
    return vehicleTypeId;
  }

  public void setVehicleTypeId(long vehicleTypeId) {
    this.vehicleTypeId = vehicleTypeId;
  }


  public String getVehicleTypeName() {
    return vehicleTypeName;
  }

  public void setVehicleTypeName(String vehicleTypeName) {
    this.vehicleTypeName = vehicleTypeName;
  }


  public long getVehicleTypeWeight() {
    return vehicleTypeWeight;
  }

  public void setVehicleTypeWeight(long vehicleTypeWeight) {
    this.vehicleTypeWeight = vehicleTypeWeight;
  }

}
