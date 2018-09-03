package com.xf.entity;

import org.apache.ibatis.type.Alias;

@Alias("InvitationCode")
public class InvitationCode {
	private int id;
	private String invitationCode;
	private int status;
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}

}