package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IDistrictDao;
import com.xf.entity.District;

@Service
public class DistrictService implements IDistrictDao {

	@Autowired
	private IDistrictDao districtDao;

	public District getById(int id) {
		return districtDao.getById(id);
	}
	
	public District getByNo(String no) {
		return districtDao.getByNo(no);
	}

	public List<District> getByParent(int parentId) {
		
		try {
			return districtDao.getByParent(parentId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<District> getByParentNo(String no) {
		return districtDao.getByParentNo(no);
	}

	public List<District> searchByNo(String part_no) {
		return districtDao.searchByNo(part_no);
	}
	
	public List<District> searchByName(String part_name) {
		return districtDao.searchByName(part_name);
	}

	public List<District> getByLevel(int levelid) {
		try {
			return districtDao.getByLevel(levelid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public District getByName(String disname) {
		try {
			return districtDao.getByName(disname);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<District> getAll() {
		return districtDao.getAll();
	}

}
