package com.myblog1.blogapp1.controller;

import com.myblog1.blogapp1.payload.CommentDto;
import com.myblog1.blogapp1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("post_id") long post_id,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(post_id, commentDto);
      return  new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("posts/{post_id}/comments")
    public List<CommentDto> getAllComments(@PathVariable("post_id") long post_id){
        List<CommentDto> dto = commentService.getCommentsByPostId(post_id);
        return dto;
    }

    @PutMapping("posts/{post_id}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("post_id") long post_id, @PathVariable("id") long id,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(post_id, id, commentDto);
      return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{post_id}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("post_id") long post_id, @PathVariable("id") long id){
        commentService.deleteComment(post_id,id);
    return  new  ResponseEntity<>("Comment is Deleted",HttpStatus.OK);
    }
}
