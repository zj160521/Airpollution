package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.SmallFacility;

public interface ISfacilityDao {
public void add(SmallFacility facility);
public void delete(int id);
public void delBycomIdYear(int comId, int year);
public List<SmallFacility> getAll(SmallFacility facility);
public SmallFacility getByid(int id);
public void update(SmallFacility facility);
public void setstatus(int status, int companyid,int fillyear);
public void setstatus2(int status, int id, int orignalStatus);
public void setstatus3(int status, int id, int orignalStatus,int fillyear);
public List<SmallFacility> getRemoval(int fillyear);
public List<SmallFacility> getByCompanyYear(@Param("companyid")int companyid,@Param("fillyear")int fillyear);
}
