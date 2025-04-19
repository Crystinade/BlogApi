package cryst.blogapi.controller;

import cryst.blogapi.entities.Comment;
import cryst.blogapi.payloads.ApiResponse;
import cryst.blogapi.payloads.CommentDto;
import cryst.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto, @PathVariable Integer postId){

        CommentDto commentDto1 = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deletedComment(commentId);

        return  new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully", "true"), HttpStatus.OK);
    }


}
