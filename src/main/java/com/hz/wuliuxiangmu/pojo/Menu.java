package com.hz.wuliuxiangmu.pojo;

import lombok.Data;

@Data
public class Menu {

  private long menuId;
  private String menuTitle;
  private String menuValue;
  private String menuIcon;
  private String menuPId;
  private long menuLevel;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getMenuId() {
    return menuId;
  }

  public void setMenuId(long menuId) {
    this.menuId = menuId;
  }


  public String getMenuTitle() {
    return menuTitle;
  }

  public void setMenuTitle(String menuTitle) {
    this.menuTitle = menuTitle;
  }


  public String getMenuValue() {
    return menuValue;
  }

  public void setMenuValue(String menuValue) {
    this.menuValue = menuValue;
  }


  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }


  public String getMenuPId() {
    return menuPId;
  }

  public void setMenuPId(String menuPId) {
    this.menuPId = menuPId;
  }


  public long getMenuLevel() {
    return menuLevel;
  }

  public void setMenuLevel(long menuLevel) {
    this.menuLevel = menuLevel;
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
