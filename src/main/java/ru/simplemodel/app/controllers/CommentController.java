package ru.simplemodel.app.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.modelmapper.ModelMapper;
import ru.simplemodel.app.services.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import ru.simplemodel.app.dto.CommentDTO;
import ru.simplemodel.app.dto.UpdateCommentStatusDTO;
import ru.simplemodel.app.errors.UpdateCommentStatusError;
import ru.simplemodel.app.models.Comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/comment")
public class CommentController {

  private final ModelMapper modelMapper;
  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService, ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
    this.commentService = commentService;
  }

  @GetMapping()
  public List<Comment> index() {
    return commentService.findAll();
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addComment(@RequestBody @Valid CommentDTO commentDTO) {
    try {
      Comment comment = convertToComment(commentDTO);

      commentService.addComment(comment);

      return ResponseEntity.ok(HttpStatus.OK);
    } catch (EnumConstantNotPresentException | JsonProcessingException e) {
      return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/update-status")
  public ResponseEntity<HttpStatus> updateCommentStatus(
    @RequestBody @Valid UpdateCommentStatusDTO updateDTO,
    BindingResult bindingResult
  ) {

    if (bindingResult.hasErrors()) {
      String errorMessage = bindingResultToString(bindingResult);
      throw new UpdateCommentStatusError(errorMessage);
    }

    Optional<Comment> commentOptional = commentService.findById(updateDTO.getCommentId());

    if (commentOptional.isEmpty()) {
      throw new UpdateCommentStatusError("Такого комментария не существует");
    }

    commentOptional.get().setStatus(updateDTO.getStatus());

    commentService.addComment(commentOptional.get());

    return ResponseEntity.ok(HttpStatus.OK);
  }

  private Comment convertToComment(CommentDTO commentDTO) throws JsonMappingException, JsonProcessingException {
    Comment comment = modelMapper.map(commentDTO, Comment.class);

    ObjectMapper objectMapper = new ObjectMapper();

    String stringCameraPosition = objectMapper.writeValueAsString(commentDTO.getCameraPosition());
    String stringCameraRotation = objectMapper.writeValueAsString(commentDTO.getCameraRotation());
    String stringCommentPosition = objectMapper.writeValueAsString(commentDTO.getPosition());
    String stringClipperPositions = objectMapper.writeValueAsString(commentDTO.getClipperPositions());

    JsonNode cameraPositionJsonNode = objectMapper.readTree(stringCameraPosition);
    JsonNode cameraRotationJsonNode = objectMapper.readTree(stringCameraRotation);
    JsonNode commentPositionJsonNode = objectMapper.readTree(stringCommentPosition);
    JsonNode clipperPositionsJsonNode = objectMapper.readTree(stringClipperPositions);

    comment.setCameraPosition(cameraPositionJsonNode);
    comment.setCameraRotation(cameraRotationJsonNode);
    comment.setPosition(commentPositionJsonNode);
    comment.setClipperPositions(clipperPositionsJsonNode);

    return comment;
  }
  
  @ExceptionHandler
  private ResponseEntity<UpdateCommentStatusError> handleException(UpdateCommentStatusError e) {
    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  private ResponseEntity<JsonProcessingException> handleJsonException(JsonProcessingException e) {
    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
  }

  private String bindingResultToString(BindingResult bindingResult) {
    StringBuilder errorMsg = new StringBuilder();

    List<FieldError> errors = bindingResult.getFieldErrors();
    for (FieldError error : errors) {
      errorMsg.append(error.getField())
        .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
        .append(";");
    }
    
    return errorMsg.toString();
  }
}
