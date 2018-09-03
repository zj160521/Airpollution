package com.xf.dao;

import java.util.List;

import com.xf.entity.InvitationCode;


public interface IInvitationCodeDao {

	public void add(InvitationCode obj);
	public void delete(String invitationCode);
	public InvitationCode getById(int id);
	public List<InvitationCode> getAll();
	public InvitationCode getInvitationCode(String invitationCode);
}