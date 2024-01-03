package ru.simplemodel.app.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "decoration")
public class Decoration {
  @Id
  @Column(name = "decoration_name")
  @NotNull(message = "Имя элемента не может быть пустым")
  private String decorationName;

  @Column(name = "decoration_type")
  @NotNull(message = "Тип элемента не может быть пустым")
  private String decorationType;

  @Column(name = "quantity")
  @NotNull(message = "Количество элемента не может быть пустым")
  private Integer quantity;

  @Column(name = "room_id")
  @NotNull(message = "Идентификатор комнаты не может быть пустым")
  private String roomId;

  @Column(name = "status")
  @NotNull(message = "Статус элемента не может быть пустым")
  private String status;

  public String getDecorationName() {
    return decorationName;
  }

  public void setDecorationName(String decorationName) {
    this.decorationName = decorationName;
  }

  public String getDecorationType() {
    return decorationType;
  }

  public void setDecorationType(String decorationType) {
    this.decorationType = decorationType;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  
}
