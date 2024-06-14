package com.myblog1.blogapp1.controller;

import com.myblog1.blogapp1.payload.PostDto;
import com.myblog1.blogapp1.payload.PostResponse;
import com.myblog1.blogapp1.service.PostService;
import com.myblog1.blogapp1.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    private PostService postService;

    public PostController(PostService postService) {

        this.postService = postService;
    }
    @PostMapping
   public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
       return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
   }
   @GetMapping
   public PostResponse getAllPosts(@RequestParam(value="pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                   @RequestParam(value="pageSize", defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                   @RequestParam(value="sortBy", defaultValue =AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                   @RequestParam(value="sortDir", defaultValue =AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir){

      return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
   }

   @GetMapping("/{id}")
   public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
       PostDto dto = postService.getPostById(id);
     return  new ResponseEntity<>(dto,HttpStatus.OK);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
       postService.deletePostById(id);
     return new ResponseEntity<>("Post is deleted successfully",HttpStatus.OK);
   }

   @PutMapping("/{id}")
   public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id")long id){
       PostDto dto = postService.updatePost(postDto,id);
     return  new ResponseEntity<>(dto,HttpStatus.CREATED);
   }
}
