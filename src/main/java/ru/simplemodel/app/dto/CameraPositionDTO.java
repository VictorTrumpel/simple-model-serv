package ru.simplemodel.app.dto;
import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class CameraPositionDTO implements Serializable {
  @NotNull
  private Integer x;

  @NotNull
  private Integer y;

  @NotNull
  private Integer z;

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(Integer z) {
    this.z = z;
  }
}
