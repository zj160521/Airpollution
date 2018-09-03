package com.xf.dao;

import java.util.LinkedHashSet;
import java.util.List;

import com.xf.entity.Permission;

public interface IPermissionDao {
	public List<Permission> getAllPermission();
	public List<Permission> getAllPermissionByRole(int roleid);
	public void delPermissionByRole(int roleid);
	public void addAllPer(List<Permission> list);
	public LinkedHashSet<Integer> getRoleIdByPermissionId(int permissionId);
}
