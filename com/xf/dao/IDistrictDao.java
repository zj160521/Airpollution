package com.xf.dao;

import java.util.List;

import com.xf.entity.District;

public interface IDistrictDao {

	public District getById(int id);
	public District getByNo(String no);
	public List<District> getByParent(int parentId);
	public List<District> getByParentNo(String no);
	public List<District> searchByNo(String part_no);
	public List<District> searchByName(String part_name);
	public List<District> getByLevel(int levelid);
	public District getByName(String disname);
	public List<District> getAll();
}
