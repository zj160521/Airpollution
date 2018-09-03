package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Firewood;
import com.xf.vo.IntString;

public interface IFirewoodDao {

	public void add(Firewood obj);
	public void delete(int id, int fillYear);
	public void delete2(int cityid, int accountid);
	public void update(Firewood obj);
	public Firewood getById(int id);
	public List<Integer> getYears(int accountid);
	public Firewood getByField(Firewood obj);
	public List<Firewood> getByTown(Firewood obj);
	public List<Firewood> getByYear(Firewood obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public List<IntString>  getTowns(Firewood firewood);
	public List<IntString>  getCitys(Firewood firewood);
	public void singleCheck(int id,int status);
}