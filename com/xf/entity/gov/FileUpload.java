package com.xf.entity.gov;

import org.apache.ibatis.type.Alias;

@Alias("FileUpload")
public class FileUpload {
private int id;  
private String fillTime;
private int fillyear;
private int accountid;
private int imported;
private String checkFile;
private String importFile;
private String tabletype;
public String getTabletype() {
	return tabletype;
}
public void setTabletype(String tabletype) {
	this.tabletype = tabletype;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFillTime() {
	return fillTime;
}
public void setFillTime(String fillTime) {
	this.fillTime = fillTime;
}
public int getFillyear() {
	return fillyear;
}
public void setFillyear(int fillyear) {
	this.fillyear = fillyear;
}
public int getAccountid() {
	return accountid;
}
public void setAccountid(int accountid) {
	this.accountid = accountid;
}
public int getImported() {
	return imported;
}
public void setImported(int imported) {
	this.imported = imported;
}
public String getCheckFile() {
	return checkFile;
}
public void setCheckFile(String checkFile) {
	this.checkFile = checkFile;
}
public String getImportFile() {
	return importFile;
}
public void setImportFile(String importFile) {
	this.importFile = importFile;
}
}
