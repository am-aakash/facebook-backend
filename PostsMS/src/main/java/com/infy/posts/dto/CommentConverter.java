package com.infy.posts.dto;

import com.infy.posts.entity.Comment;

public class CommentConverter {
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setPostId(comment.getPostId());
        commentDTO.setUserId(comment.getUserId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setTimestamp(comment.getTimestamp());
        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentId(commentDTO.getCommentId());
        comment.setPostId(commentDTO.getPostId());
        comment.setUserId(commentDTO.getUserId());
        comment.setContent(commentDTO.getContent());
        comment.setTimestamp(commentDTO.getTimestamp());
        return comment;
    }
}
