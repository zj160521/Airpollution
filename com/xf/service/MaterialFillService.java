package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IMaterialFillDao;
import com.xf.entity.MaterialFill;

@Service
public class MaterialFillService implements IMaterialFillDao {

	@Autowired
	private IMaterialFillDao materialFillDao;

	public void add(MaterialFill fill) {
		materialFillDao.add(fill);
	}

	public void update(MaterialFill fill) {
		materialFillDao.update(fill);
	}

	public List<MaterialFill> getByMaterialId(int mid) {
		return materialFillDao.getByMaterialId(mid);
	}

	public List<MaterialFill> getByProductFillId(int pfid,int fillyear) {
		return materialFillDao.getByProductFillId(pfid,fillyear);
	}

	public void delete(MaterialFill fill) {
		materialFillDao.delete(fill);
	}

	public void setstatus(int status, int companyid,int fillyear) {
		materialFillDao.setstatus(status, companyid,fillyear);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		materialFillDao.setstatus2(status, companyid, orignalStatus);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,int fillyear) {
		materialFillDao.setstatus3(status, companyid, orignalStatus,fillyear);
	}
	
	public void deletepfill(int pfillid) {
		materialFillDao.deletepfill(pfillid);
	}

}
