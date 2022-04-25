package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class VehicleToll {

  private long vehicleTollId;
  private double vehicleTollWeight;
  private double vehicleTollPrice;


  public long getVehicleTollId() {
    return vehicleTollId;
  }

  public void setVehicleTollId(long vehicleTollId) {
    this.vehicleTollId = vehicleTollId;
  }


  public double getVehicleTollWeight() {
    return vehicleTollWeight;
  }

  public void setVehicleTollWeight(double vehicleTollWeight) {
    this.vehicleTollWeight = vehicleTollWeight;
  }


  public double getVehicleTollPrice() {
    return vehicleTollPrice;
  }

  public void setVehicleTollPrice(double vehicleTollPrice) {
    this.vehicleTollPrice = vehicleTollPrice;
  }

}
