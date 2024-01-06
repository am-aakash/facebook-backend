package com.infy.posts.dto;

import com.infy.posts.entity.Unlike;

public class UnlikeConverter {
	public UnlikeDTO toDTO(Unlike unlike) {
		UnlikeDTO unlikeDTO = new UnlikeDTO();
		unlikeDTO.setUnlikeId(unlike.getUnlikeId());
		unlikeDTO.setPostId(unlike.getPostId());
		unlikeDTO.setUserId(unlike.getUserId());
		unlikeDTO.setTimestamp(unlike.getTimestamp());
		return unlikeDTO;
	}

	public Unlike toEntity(UnlikeDTO unlikeDTO) {
		Unlike unlike = new Unlike();
		unlike.setUnlikeId(unlikeDTO.getUnlikeId());
		unlike.setPostId(unlikeDTO.getPostId());
		unlike.setUserId(unlikeDTO.getUserId());
		unlike.setTimestamp(unlikeDTO.getTimestamp());
		return unlike;
	}
}