package cryst.blogapi.controller;


import cryst.blogapi.config.AppConstants;
import cryst.blogapi.entities.Post;
import cryst.blogapi.payloads.ApiResponse;
import cryst.blogapi.payloads.PostDto;
import cryst.blogapi.payloads.PostResponse;
import cryst.blogapi.service.FileService;
import cryst.blogapi.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Value("${project.image}")
    private String path;

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){

        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){

        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public  ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

      PostDto postDto =  this.postService.getPostById(postId);
        return  new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){

        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted", "ok");

    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){

      PostDto updatePost = this.postService.updatePost(postDto, postId);

      return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchByPost(
            @PathVariable("keywords") String keywords
    ){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return  new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

       String fileName = this.fileService.uploadImage(path, image);
       PostDto postDto = this.postService.getPostById(postId);
       postDto.setImageName(fileName);
       PostDto updatePost = this.postService.updatePost(postDto, postId);

       return  new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
