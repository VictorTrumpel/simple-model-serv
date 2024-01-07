package ru.simplemodel.app.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "container")
public class Container {
  @Id
  @Column(name = "container_name")
  @NotNull(message = "Имя контейнера не может быть пустым")
  private String containerName;

  @Column(name = "date_create")
  @NotNull(message = "Дата создания не может быть пустой")
  private Date dateCreate;

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }

  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }
}
