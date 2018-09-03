package com.xf.service.gov;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IVgovimportDao;
import com.xf.entity.gov.Vgovimport;
@Service
public class VgovimportService implements IVgovimportDao {
    @Autowired
	private IVgovimportDao dao;
	public List<Vgovimport> getAll(int fillyear) {
		return dao.getAll(fillyear);
	}
	public List<Vgovimport> getByCity(int city,int fillyear) {
		return dao.getByCity(city,fillyear);
	}
	public List<Vgovimport> getBymorename(int fillyear,String name) {
		return dao.getBymorename(fillyear,name);
	}
	public List<Vgovimport> cityBymorename(int city,int fillyear,String name) {
		return dao.cityBymorename(city,fillyear,name);
	}

}
