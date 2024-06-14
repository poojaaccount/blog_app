package com.myblog1.blogapp1.service;

import com.myblog1.blogapp1.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long post_id, CommentDto commentDto);

   List<CommentDto> getCommentsByPostId(long post_id);

    CommentDto updateComment(long post_id, long id, CommentDto commentDto);

    void deleteComment(long post_id, long id);
}
