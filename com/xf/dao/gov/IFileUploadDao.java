package com.xf.dao.gov;


import com.xf.entity.gov.FileUpload;

public interface IFileUploadDao {
	
	public void add(FileUpload obj);
	public void update(FileUpload obj);
	public FileUpload findFile(FileUpload obj);
	public void updatecheck(FileUpload obj);
}
