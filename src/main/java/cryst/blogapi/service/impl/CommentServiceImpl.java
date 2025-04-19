package cryst.blogapi.service.impl;

import cryst.blogapi.entities.Comment;
import cryst.blogapi.entities.Post;
import cryst.blogapi.payloads.CommentDto;
import cryst.blogapi.repository.CommentRepo;
import cryst.blogapi.repository.PostRepo;
import cryst.blogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionException;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentId, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new CompletionException(new RuntimeException("Post not found")));
        Comment comment = this.modelMapper.map(commentId, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deletedComment(Integer commentId) {

    Comment com = this.commentRepo.findById(commentId)
            .orElseThrow(()-> new CompletionException(new RuntimeException("Comment not found")));
    this.commentRepo.delete(com);

    }
}