package com.xf.dao.gov;

import org.apache.ibatis.annotations.Param;

public interface IFileDownloadDao {

	public String findByid(@Param("tabletype") String tabletype,@Param("accountid")int accountid,@Param("fillyear")int fillyear);
}
