package ru.simplemodel.app.errors;

public class UpdateCommentStatusError extends RuntimeException {
  public UpdateCommentStatusError(String message) {
    super(message);
  }
  
}
