package com.myblog1.blogapp1.service.Impl;


import com.myblog1.blogapp1.entities.Post;
import com.myblog1.blogapp1.exception.ResourceNotFoundException;
import com.myblog1.blogapp1.payload.PostDto;
import com.myblog1.blogapp1.payload.PostResponse;
import com.myblog1.blogapp1.repository.PostRepository;
import com.myblog1.blogapp1.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper mapper) {

        this.postRepo = postRepo;
        this.mapper=mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post postEntity = postRepo.save(post);
       return  mapToDto(postEntity);
    }

     Post mapToEntity(PostDto postDto){
         Post post = mapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
        PostDto mapToDto(Post post) {
            PostDto dto = mapper.map(post, PostDto.class);
//        PostDto dto=new PostDto();
//           dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        return dto;
    }


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortDir).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );

        return mapToDto(post);


    }

    @Override
    public void deletePostById(long id) {
        postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostDto updatePost(PostDto postDto,long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postEntity = postRepo.save(post);

        PostDto dto = mapToDto(postEntity);
        return dto;
    }
}
