package com.myblog1.blogapp1.service.Impl;

import com.myblog1.blogapp1.entities.Comment;
import com.myblog1.blogapp1.entities.Post;
import com.myblog1.blogapp1.exception.ResourceNotFoundException;
import com.myblog1.blogapp1.payload.CommentDto;
import com.myblog1.blogapp1.repository.CommentRepository;
import com.myblog1.blogapp1.repository.PostRepository;
import com.myblog1.blogapp1.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;
    private PostRepository postRepo;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.mapper=mapper;
    }

    @Override
    public CommentDto createComment(long post_id, CommentDto commentDto) {
        Post post = postRepo.findById(post_id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", post_id)
        );
        Comment comment = mapToCommentEntity(commentDto);
        comment.setPost(post); //meaning is we are initialising post variable in comment entity
        Comment entityComment = commentRepo.save(comment);
        CommentDto dto = mapToCommentDto(entityComment);

        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long post_id) {
        List<Comment> comments = commentRepo.findByPostId(post_id);
        List<CommentDto> dto = comments.stream().map(comment -> mapToCommentDto(comment)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public CommentDto updateComment(long post_id, long id, CommentDto commentDto) {
        Post post = postRepo.findById(post_id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", post_id)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(comment.getEmail());

        Comment updatedComment = commentRepo.save(comment);

      return  mapToCommentDto(updatedComment);


    }

    @Override
    public void deleteComment(long post_id, long id) {
        Post post = postRepo.findById(post_id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", post_id)
        );

        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", id)
        );

        commentRepo.deleteById(id);
    }

    private CommentDto mapToCommentDto(Comment entityComment) {
        CommentDto commentDto = mapper.map(entityComment, CommentDto.class);
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(entityComment.getId());
//        commentDto.setBody(entityComment.getBody());
//        commentDto.setEmail(entityComment.getEmail());
//        commentDto.setName(entityComment.getName());
        return commentDto;
    }

    private Comment mapToCommentEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);

//        Comment comment=new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
        return comment;
    }
}
