package cryst.blogapi.service;

import cryst.blogapi.payloads.PostDto;
import cryst.blogapi.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto, Integer postId);

    // delete

    void deletePost(Integer postId);

    //get all posts

   // PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

    //get single post

    PostDto getPostById(Integer postId);

    //get all posts by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer categoryId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
