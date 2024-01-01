package ru.simplemodel.app.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ru.simplemodel.app.models.Comment;
import ru.simplemodel.app.repositories.CommentRepository;

@Service
@Transactional(readOnly = true)
public class CommentService {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  public Optional<Comment> findById(Integer commentId) {
    return commentRepository.findById(commentId);
  }

  @Transactional
  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  @Transactional
  public void update(Comment comment) {
    commentRepository.save(comment);
  }
}
