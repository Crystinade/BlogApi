package cryst.blogapi.service;

import cryst.blogapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deletedComment(Integer commentId);

}
