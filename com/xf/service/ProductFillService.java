package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IProductFillDao;
import com.xf.entity.ProductFill;
import com.xf.vo.IfNull;

@Service
public class ProductFillService implements IProductFillDao {

	@Autowired
	private IProductFillDao productFillDao;
	private IfNull ifnull = new IfNull();

	private void setTotal(ProductFill fill) {
		fill.setRealOutput(ifnull.ifNullDouble(fill.getM1())
				+ ifnull.ifNullDouble(fill.getM2())
				+ ifnull.ifNullDouble(fill.getM3())
				+ ifnull.ifNullDouble(fill.getM4())
				+ ifnull.ifNullDouble(fill.getM5())
				+ ifnull.ifNullDouble(fill.getM6())
				+ ifnull.ifNullDouble(fill.getM7())
				+ ifnull.ifNullDouble(fill.getM8())
				+ ifnull.ifNullDouble(fill.getM9())
				+ ifnull.ifNullDouble(fill.getM10())
				+ ifnull.ifNullDouble(fill.getM11())
				+ ifnull.ifNullDouble(fill.getM12()));
	}

	public void add(ProductFill fill) {
		if(fill.getRealOutput()<=0)
			setTotal(fill);
		productFillDao.add(fill);
	}

	public void update(ProductFill fill) {
		if(fill.getRealOutput()<=0)
			setTotal(fill);
		productFillDao.update(fill);
	}

	public List<ProductFill> getByProductId(int pid) {
		return productFillDao.getByProductId(pid);
	}

	public List<ProductFill> getById(int productid, int stepid) {
		return productFillDao.getById(productid, stepid);
	}

	public int getRelation(int productid, int stepid) {
		return productFillDao.getRelation(productid, stepid);
	}

	public void addRelation(int productid, int stepid) {
		productFillDao.addRelation(productid, stepid);
	}

	public List<ProductFill> getByCompany(int companyid) {
		return productFillDao.getByCompany(companyid);
	}

	public List<ProductFill> getByStepId(int stepid) {
		return productFillDao.getByStepId(stepid);
	}

	public void delRelation(int productid, int stepid) {
		productFillDao.delRelation(productid, stepid);
	}

	public void setstatus(int status, int companyid, int fillyear) {
		productFillDao.setstatus(status, companyid, fillyear);
	}

	public ProductFill getById1(int pfillid) {
		return productFillDao.getById1(pfillid);
	}

	public List<ProductFill> getByYear(int year, int companyid) {
		return productFillDao.getByYear(year, companyid);
	}

	public void setstatus2(int status, int companyid, int orignalStatus) {
		productFillDao.setstatus2(status, companyid, orignalStatus);
	}

	public void setstatus3(int status, int companyid, int orignalStatus,
			int fillyear) {
		productFillDao.setstatus3(status, companyid, orignalStatus, fillyear);
	}

	public void unpass(int status, int id, int orignalStatus) {
		productFillDao.unpass(status, id, orignalStatus);

	}

	public List<ProductFill> getFillid(int id) {
		return productFillDao.getFillid(id);
	}

	public void deletepfill(int pfillid) {
		productFillDao.deletepfill(pfillid);
	}

	public List<ProductFill> getSmallpfill(ProductFill pf) {
		return productFillDao.getSmallpfill(pf);
	}

	public void smallSetstatus(int status, int id, int fillyear) {
		productFillDao.smallSetstatus(status, id, fillyear);
	}

	public void smallSetstatus3(int status, int id, int orignalStatus,
			int fillyear) {
		productFillDao.smallSetstatus3(status, id, orignalStatus, fillyear);
	}

	public List<ProductFill> getotalYear(int fillyear) {
		return productFillDao.getotalYear(fillyear);
	}

	public void update2(ProductFill fill) {
		productFillDao.update2(fill);
	}

	public void delBycomIdYear(int comId, int year) {
		productFillDao.delBycomIdYear(comId, year);
	}

}
