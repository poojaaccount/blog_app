package com.myblog1.blogapp1.service;

import com.myblog1.blogapp1.payload.PostDto;
import com.myblog1.blogapp1.payload.PostResponse;

import java.util.List;

public interface PostService {

 PostDto createPost(PostDto postDto);

 PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);

 PostDto getPostById(long id);

 void deletePostById(long id);

 PostDto updatePost (PostDto postDto,long id);
}
