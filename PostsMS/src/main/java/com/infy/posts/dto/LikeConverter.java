package com.infy.posts.dto;
import com.infy.posts.entity.Like;

public class LikeConverter {
    public static LikeDTO toDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeId(like.getLikeId());
        likeDTO.setPostId(like.getPostId());
        likeDTO.setUserId(like.getUserId());
        likeDTO.setTimestamp(like.getTimestamp());
        return likeDTO;
    }

    public static Like toEntity(LikeDTO likeDTO) {
        Like like = new Like();
        like.setLikeId(likeDTO.getLikeId());
        like.setPostId(likeDTO.getPostId());
        like.setUserId(likeDTO.getUserId());
        like.setTimestamp(likeDTO.getTimestamp());
        return like;
    }
}
