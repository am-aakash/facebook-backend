package com.infy.friendrequest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infy.friendrequest.dto.FriendRequestDTO;
import com.infy.friendrequest.dto.FriendRequestStatus;

@Entity
@Table(name = "friend_request")
public class FriendRequest {

	@Id
	private String requestId;

	private String senderId;

	private String recieverId;

	@Enumerated(EnumType.STRING)
	private FriendRequestStatus status;

	private Date timestampSent;

	private Date timestampAccepted;

	public static FriendRequest fromDTO(FriendRequestDTO dto) {
		FriendRequest entity = new FriendRequest();
		entity.setRequestId(dto.getRequestId());
		entity.setSenderId(dto.getSenderId());
		entity.setRecieverId(dto.getRecieverId());
		entity.setStatus(dto.getStatus());
		entity.setTimestampSent(dto.getTimestampSent());
		entity.setTimestampAccepted(dto.getTimestampAccepted());
		return entity;
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