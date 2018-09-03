package com.xf.dao.gov;

import java.util.List;
import com.xf.entity.gov.Equipment;

public interface IEquipmentDao {

	public void add(Equipment obj);
	public void delete(int id);
	public void update(Equipment obj);
	public void update2(Equipment obj);
	public Equipment getById(int id);
	public List<Integer> getYears(int accountid);
	public Equipment getByField(Equipment obj);
	public List<Equipment> getByYear(Equipment obj);
	public List<Equipment> getByYear2(Equipment obj);
	public void setstatus(int status, int accountid,int fillyear);
	public void setstatus2(int status, int accountid, int orignalStatus);
	public void setstatus3(int status, int accountid, int orignalStatus, int fillYear);
	public Equipment getEquipment(Equipment obj);
	public List<Equipment> statAll(int year);
	public List<Equipment> statAll2(int year);
}