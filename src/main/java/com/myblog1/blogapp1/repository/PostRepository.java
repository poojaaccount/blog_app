package com.myblog1.blogapp1.repository;

import com.myblog1.blogapp1.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
