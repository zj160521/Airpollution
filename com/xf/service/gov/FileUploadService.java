package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IFileUploadDao;
import com.xf.entity.gov.FileUpload;
@Service
public class FileUploadService implements IFileUploadDao {
	@Autowired
	private IFileUploadDao dao;
	public void add(FileUpload obj) {
		dao.add(obj);
	}
	public void update(FileUpload obj) {
		dao.update(obj);
	}
	public FileUpload findFile(FileUpload obj) {
		return dao.findFile(obj);
	}
	public void updatecheck(FileUpload obj) {
		dao.updatecheck(obj);
	}
}
