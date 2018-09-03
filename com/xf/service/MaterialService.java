package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IMaterialDao;
import com.xf.entity.Material;
import com.xf.vo.IntString;

@Service
public class MaterialService implements IMaterialDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IMaterialDao materialDao;

	public void add(Material material) {
		materialDao.add(material);
	}

	public void delete(int id) {
		materialDao.delete(id);
	}

	public void update(Material material) {
		materialDao.update(material);
	}

	public Material getById(int id) {
		return materialDao.getById(id);
	}

	public List<Material> getByCompany(int companyid) {
		return materialDao.getByCompany(companyid);
	}

	public List<Material> getByStep(int stepid) {
		return materialDao.getByStep(stepid);
	}

	public Material getMaterial(int companyid, String materialName) {
		return materialDao.getMaterial(companyid, materialName);
	}

	public List<IntString> getMaterialFactor(int productId) {
		try {
			List<IntString> list=materialDao.getMaterialFactor(productId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Material> getGroupMaterial(int groupid) {
		return materialDao.getGroupMaterial(groupid);
	}

	public List<Material> getIdbyname(String materialName) {
		return materialDao.getIdbyname(materialName);
	}

}
