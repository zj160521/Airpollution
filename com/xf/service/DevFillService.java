package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IDevFillDao;
import com.xf.entity.DevFill;

@Service
public class DevFillService implements IDevFillDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IDevFillDao devfillDao;

	public void add(DevFill devfill) {
		setTotal(devfill);
		devfillDao.add(devfill);
	}

	public void update(DevFill devfill) {
		setTotal(devfill);
		devfillDao.update(devfill);
	}

	public List<DevFill> getByDevId(int devid,int fillyear) {
		return devfillDao.getByDevId(devid,fillyear);
	}
	
	private void setTotal(DevFill devfill) {
		
		Double total=(double) 0;
		
		if(devfill.getId() > 0){
			DevFill d= devfillDao.getById(devfill.getId());
			
			if(devfill.getM1() != null){
				total+=devfill.getM1();
			} else if(d.getM1() != null){
				total+=d.getM1();
			}
			
			if(devfill.getM2() != null){
				total+=devfill.getM2();
			} else if(d.getM2() != null){
				total+=d.getM2();
			}
			
			if(devfill.getM3() != null){
				total+=devfill.getM3();
			} else if(d.getM3() != null){
				total+=d.getM3();
			}
			
			if(devfill.getM4() != null){
				total+=devfill.getM4();
			} else if(d.getM4() != null){
				total+=d.getM4();
			}
			if(devfill.getM5() != null){
				total+=devfill.getM5();
			} else if(d.getM5() != null){
				total+=d.getM5();
			}
			
			if(devfill.getM6() != null){
				total+=devfill.getM6();
			} else if(d.getM6() != null){
				total+=d.getM6();
			}
			
			if(devfill.getM7() != null){
				total+=devfill.getM7();
			} else if(d.getM7() != null){
				total+=d.getM7();
			}
			
			if(devfill.getM8() != null){
				total+=devfill.getM8();
			} else if(d.getM8() != null){
				total+=d.getM8();
			}
			
			if(devfill.getM9() != null){
				total+=devfill.getM9();
			} else if(d.getM9() != null){
				total+=d.getM9();
			}
			
			if(devfill.getM10() != null){
				total+=devfill.getM10();
			} else if(d.getM10() != null){
				total+=d.getM10();
			}
			
			if(devfill.getM11() != null){
				total+=devfill.getM11();
			} else if(d.getM11() != null){
				total+=d.getM11();
			}
			
			if(devfill.getM12() != null){
				total+=devfill.getM12();
			} else if(d.getM12() != null){
				total+=d.getM12();
			}
			
			devfill.setmTotalYear(total);
		}else{
			if(devfill.getM1() != null) total+=devfill.getM1();
			if(devfill.getM2() != null) total+=devfill.getM2();
			if(devfill.getM3() != null) total+=devfill.getM3();
			if(devfill.getM4() != null) total+=devfill.getM4();
			if(devfill.getM5() != null) total+=devfill.getM5();
			if(devfill.getM6() != null) total+=devfill.getM6();
			if(devfill.getM7() != null) total+=devfill.getM7();
			if(devfill.getM8() != null) total+=devfill.getM8();
			if(devfill.getM9() != null) total+=devfill.getM9();
			if(devfill.getM10() != null) total+=devfill.getM10();
			if(devfill.getM11() != null) total+=devfill.getM11();
			if(devfill.getM12() != null) total+=devfill.getM12();
			
			devfill.setmTotalYear(total);
			
		}
		

		

	}

	public void setstatus(int status, int companyid,int fillyear) {
		devfillDao.setstatus(status, companyid,fillyear);
	}

	public DevFill getCuryearFill(int devid,int fillyear) {
		return devfillDao.getCuryearFill(devid,fillyear);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear) {
		devfillDao.setstatus3(status, companyid, orignalStatus,fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		devfillDao.setstatus2(status, companyid, orignalStatus);
	}
	
	public void unpass(int status, int id, int orignalStatus) {
		devfillDao.unpass(status, id, orignalStatus);
		
	}

	public List<DevFill> getfuelByname(String name) {
		return devfillDao.getfuelByname(name);
	}

	public DevFill getByfuelId(int fuelId,int productid) {
		return devfillDao.getByfuelId(fuelId,productid);
	}

	public DevFill getById(int id) {
		return devfillDao.getById(id);
	}

	public List<DevFill> getByDevId1(int devid) {
		return devfillDao.getByDevId1(devid);
	}

	public void smallSetstatus(int status, int id, int fillyear) {
		devfillDao.smallSetstatus(status, id, fillyear);
	}

	public void smallSetstatus3(int status, int id, int orignalStatus,
			int fillyear) {
		devfillDao.smallSetstatus3(status, id, orignalStatus, fillyear);
	}

	public List<DevFill> getByCompanyId(int companyid, int fillyear,int status) {
		return devfillDao.getByCompanyId(companyid, fillyear,status);
	}


}
