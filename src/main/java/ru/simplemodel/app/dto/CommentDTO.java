package ru.simplemodel.app.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class CommentDTO {
  @Size(min = 1, max = 250)
  @NotEmpty(message = "Элемент не может быть пустым")
  private String elementName;

  @Size(min = 1, max = 250)
  @NotEmpty(message = "Заголовок комментария не может быть пустым")
  private String commentTitle;

  @Size(min = 1, max = 250)
  @NotEmpty(message = "Текст комментария не может быть пустым")
  private String commentText;

  @Size(min = 1, max = 250)
  @NotEmpty(message = "Дата создания не может быть пустой")
  private String dateCreate;

  @NotEmpty(message = "Дата окончания не может быть пустой")
  private String dateToFix;

  @Size(min = 1, max = 250)
  @NotEmpty(message = "Исполнитель не может быть пустым")
  private String executor;

  @Valid
  @NotNull(message = "Положение камеры не может быть пустым")
  private CameraPositionDTO cameraPosition;

  @Valid
  @NotNull(message = "Поворот камеры не может быть пустым")
  private CameraRotationDTO cameraRotation;

  @Valid
  @NotNull(message = "Позиция комментария не может быть пустой")
  private CommentPositionDTO position;

  @Valid
  @NotNull(message = "Позиции подрезок должны быть переданы")
  private List<ClipperPositionDTO> clipperPositions;  

  public CameraRotationDTO getCameraRotation() {
    return cameraRotation;
  }

  public void setCameraRotation(CameraRotationDTO cameraRotation) {
    this.cameraRotation = cameraRotation;
  }

  public CommentPositionDTO getPosition() {
    return position;
  }

  public void setPosition(CommentPositionDTO position) {
    this.position = position;
  }

  public CameraPositionDTO getCameraPosition() {
    return cameraPosition;
  }

  public void setCameraPosition(CameraPositionDTO cameraPosition) {
    this.cameraPosition = cameraPosition;
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
    return toDate(dateCreate);
  }

  public void setDateCreate(String dateCreate) {
    this.dateCreate = dateCreate;
  }

  public Date getDateToFix() {
    return toDate(dateToFix);
  }

  public void setDateToFix(String dateToFix) {
    this.dateToFix = dateToFix;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  private Date toDate(String dateStr) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = dateFormat.parse(dateStr);
      return date;
    } catch (ParseException e) {
      System.out.println(e);
      return new Date();
    }
  }

  public List<ClipperPositionDTO> getClipperPositions() {
    return clipperPositions;
  }

  public void setClipperPositions(List<ClipperPositionDTO> clipperPositions) {
    this.clipperPositions = clipperPositions;
  }
}
