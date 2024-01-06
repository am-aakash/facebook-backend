package com.infy.friendrequest.dto;

import java.util.Date;

import com.infy.friendrequest.entity.FriendRequest;

public class FriendRequestDTO {

	private String requestId;
	private String senderId;
	private String recieverId;
	private FriendRequestStatus status;
	private Date timestampSent;
	private Date timestampAccepted;

	public static FriendRequestDTO fromEntity(FriendRequest entity) {
		FriendRequestDTO dto = new FriendRequestDTO();
		dto.setRequestId(entity.getRequestId());
		dto.setSenderId(entity.getSenderId());
		dto.setRecieverId(entity.getRecieverId());
		dto.setStatus(entity.getStatus());
		dto.setTimestampSent(entity.getTimestampSent());
		dto.setTimestampAccepted(entity.getTimestampAccepted());
		return dto;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}

	public FriendRequestStatus getStatus() {
		return status;
	}

	public void setStatus(FriendRequestStatus status) {
		this.status = status;
	}

	public Date getTimestampSent() {
		return timestampSent;
	}

	public void setTimestampSent(Date timestampSent) {
		this.timestampSent = timestampSent;
	}

	public Date getTimestampAccepted() {
		return timestampAccepted;
	}

	public void setTimestampAccepted(Date timestampAccepted) {
		this.timestampAccepted = timestampAccepted;
	}
}
