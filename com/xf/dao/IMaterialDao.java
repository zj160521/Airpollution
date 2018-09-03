package com.xf.dao;

import java.util.List;

import com.xf.entity.Material;
import com.xf.vo.IntString;

public interface IMaterialDao {

	public void add(Material mat);
	public void delete(int id);
	public void update(Material mat);
	public Material getById(int id);
	public List<Material> getByCompany(int companyid);
	public List<Material> getByStep(int stepid);
	public Material getMaterial(int companyid,String materialName);
	public List<IntString> getMaterialFactor(int productId);
	public List<Material> getGroupMaterial(int groupid);
	public List<Material> getIdbyname(String materialName);
}
