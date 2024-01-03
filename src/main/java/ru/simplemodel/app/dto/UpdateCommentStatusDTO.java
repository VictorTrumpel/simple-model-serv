package ru.simplemodel.app.dto;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

public class UpdateCommentStatusDTO {

  @NotNull(message = "Необходимо указать id комментария")
  private Integer commentId;

  @Size(min = 1, max = 50)
  @NotNull(message = "Статус не может быть пустым")
  private String status;

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
