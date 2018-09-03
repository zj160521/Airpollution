package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.MaterialFill;

public interface IMaterialFillDao {

	public void add(MaterialFill fill);
	public void update(MaterialFill fill);
	public void delete(MaterialFill fill);
	public List<MaterialFill> getByMaterialId(int mid);
	public List<MaterialFill> getByProductFillId(@Param("pfid")int pfid,@Param("fillyear")int fillyear);
	public void setstatus(int status, int companyid,int fillyear);
	public void setstatus2(int status, int companyid, int orignalStatus);
	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear);
	public void deletepfill(int pfillid);
}
