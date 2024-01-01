package ru.simplemodel.app.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
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
  @NotNull(message = "Исполнитель не может быть пустым")
  private String executor;

  @Column(name = "status")
  @Size(min = 1, max = 50)
  private String status;

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
}
