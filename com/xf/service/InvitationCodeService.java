package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IInvitationCodeDao;

import com.xf.entity.InvitationCode;

@Service
public class InvitationCodeService implements IInvitationCodeDao {

	@Autowired
	private IInvitationCodeDao dao;
	
	public void add(InvitationCode obj) {
		dao.add(obj);
	}
	public void delete(String invitationCode) {
		dao.delete(invitationCode);
	}
	public InvitationCode getById(int id) {
		return dao.getById(id);
	}
	public List<InvitationCode> getAll() {
		return dao.getAll();
	}
	public InvitationCode getInvitationCode(String invitationCode) {
		return dao.getInvitationCode(invitationCode);
	}
}
