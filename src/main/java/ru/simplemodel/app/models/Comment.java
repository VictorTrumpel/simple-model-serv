package ru.simplemodel.app.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import ru.simplemodel.app.dto.CameraPositionDTO;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  @Column(name = "element_name")
  @Size(min = 1, max = 250)
  @NotNull(message = "Элемент не может быть пустым")
  private String elementName;

  @Column(name = "comment_title")
  @Size(min = 1, max = 250)
  @NotNull(message = "Заголовок комментария не может быть пустым")
  private String commentTitle;

  @Column(name = "comment_text")
  @Size(min = 1, max = 250)
  @NotNull(message = "Текст комментария не может быть пустым")
  private String commentText;

  @Column(name = "date_create")
  @NotNull(message = "Дата создания не может быть пустой")
  private Date dateCreate;

  @Column(name = "date_to_fix")
  @NotNull(message = "Дата окончания не может быть пустой")
  private Date dateToFix;

  @Column(name = "executor")
  @Size(min = 1, max = 250)
  @NotNull()
  private String executor;

  @Column(name = "status")
  @Size(min = 1, max = 50)
  @NotNull
  private String status = "AWAIT";

  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  @Column(name = "camera_position")
  @NotNull()
  private JsonNode cameraPosition;

  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  @Column(name = "camera_rotation")
  @NotNull()
  private JsonNode cameraRotation;

  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  @Column(name = "position")
  @NotNull()
  private JsonNode position;

  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  @Column(name = "clipper_positions")
  @NotNull()
  private JsonNode clipperPositions;

  public JsonNode getCameraRotation() {
    return cameraRotation;
  }

  public void setCameraRotation(JsonNode cameraRotation) {
    this.cameraRotation = cameraRotation;
  }

  public JsonNode getPosition() {
    return position;
  }

  public void setPosition(JsonNode position) {
    this.position = position;
  }

  public void setCameraPosition(JsonNode cameraPosition) {
    this.cameraPosition = cameraPosition;
  }

  public JsonNode getCameraPosition() {
    return cameraPosition;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public String getElementName() {
    return elementName;
  }

  public void setElementName(String elementName) {
    this.elementName = elementName;
  }

  public String getCommentTitle() {
    return commentTitle;
  }

  public void setCommentTitle(String commentTitle) {
    this.commentTitle = commentTitle;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }

  public Date getDateToFix() {
    return dateToFix;
  }

  public void setDateToFix(Date dateToFix) {
    this.dateToFix = dateToFix;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Comment [commentId=" + commentId + ", elementName=" + elementName + ", commentTitle=" + commentTitle
        + ", commentText=" + commentText + ", dateCreate=" + dateCreate + ", dateToFix=" + dateToFix + ", executor="
        + executor + ", status=" + status + "]";
  }

  public JsonNode getClipperPositions() {
    return clipperPositions;
  }

  public void setClipperPositions(JsonNode clipperPositions) {
    this.clipperPositions = clipperPositions;
  }
}
