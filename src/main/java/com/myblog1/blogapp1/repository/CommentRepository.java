package com.myblog1.blogapp1.repository;

import com.myblog1.blogapp1.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByPostId(long post_id);
}
