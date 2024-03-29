package ru.simplemodel.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.simplemodel.app.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {}