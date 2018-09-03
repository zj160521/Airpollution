package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IFactorDao;
import com.xf.entity.gov.Factor;

@Service
public class FactorService implements IFactorDao {

	@Autowired
	private IFactorDao dao;
	
	public void add(Factor obj) {
		dao.add(obj);
	}
	public void update(Factor obj) {
		dao.update(obj);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Factor getById(int id) {
		return dao.getById(id);
	}
	public List<Integer> getYears() {
		return dao.getYears();
	}
	public Factor getByField(Factor obj) {
		return dao.getByField(obj);
	}
	public List<Factor> getByYear(Factor obj) {
		return dao.getByYear(obj);
	}
	public Factor getByIdId(Factor obj) {
		
		try {
			Factor ft=dao.getByIdId(obj);
			return ft;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Factor> findFactor(int productid, int pollutantid) {
		return dao.findFactor(productid, pollutantid);
	}
	public List<Factor> findFactorBygroupId(int groupid, int pollutantid) {
		return dao.findFactorBygroupId(groupid,pollutantid);
	}
	public void addGroupfactor(List<Factor> list) {
		dao.addGroupfactor(list);
	}
	public void addFactor(Factor factor) {
		dao.addFactor(factor);
	}
	public void deleteGrf(int groupid) {
		dao.deleteGrf(groupid);
	}
	public void deleteFacBypid(int productid) {
		dao.deleteFacBypid(productid);
	}
	public List<Factor> getSurefactor(int pollutantid, int groupid) {
		return dao.getSurefactor(pollutantid, groupid);
	}
	public List<Factor> getAll() {
		return dao.getAll();
	}
	public List<Factor> getProductfactor(int groupid, int pollutantid) {
		return dao.getProductfactor(groupid, pollutantid);
	}
	public List<Factor> getFuelfactor(int typeid) {
		return dao.getFuelfactor(typeid);
	}
	public void updateIsFollow(Factor ftr) {
		dao.updateIsFollow(ftr);
	}
}
