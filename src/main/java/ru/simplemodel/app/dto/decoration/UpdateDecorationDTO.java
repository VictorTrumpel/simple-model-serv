package ru.simplemodel.app.dto.decoration;

import javax.validation.constraints.NotNull;

public class UpdateDecorationDTO {
  
  @NotNull(message = "Фактическое количество должно быть передано")
  private Integer quantity;

  @NotNull(message = "Статус должен быть передан")
  private String status;

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
