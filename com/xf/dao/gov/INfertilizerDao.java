package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Nfertilizer;

public interface INfertilizerDao {

	public void add(Nfertilizer obj);
	public void delete(int id);
	public void update(Nfertilizer obj);
	public Nfertilizer getById(int id);
	public List<Integer> getYears(int accountid);
	public Nfertilizer getByField(Nfertilizer obj);
	public List<Nfertilizer> getByYear(Nfertilizer obj);
	public List<Nfertilizer> getByFerType (Nfertilizer obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public Nfertilizer getByContion(Nfertilizer obj);
}