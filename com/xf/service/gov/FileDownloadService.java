package com.xf.service.gov;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IFileDownloadDao;
@Service
public class FileDownloadService implements IFileDownloadDao{
	@Autowired
	private IFileDownloadDao dao;
	public String findByid(String tabletype,int accountid,int fillyear) {
		return dao.findByid(tabletype,accountid,fillyear);
	}
}
