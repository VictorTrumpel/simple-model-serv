package ru.simplemodel.app.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "container")
public class Container {
  @Id
  @Column(name = "container_name")
  @NotNull(message = "Имя контейнера не может быть пустым")
  private String containerName;

  public String getContainerName() {
    return containerName;
  }

  public void setContainerName(String containerName) {
    this.containerName = containerName;
  }
}
