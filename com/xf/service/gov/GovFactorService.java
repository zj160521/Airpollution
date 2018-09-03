package com.xf.service.gov;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IGovFactorDao;
import com.xf.entity.gov.GovFactor;

@Service
public class GovFactorService implements IGovFactorDao {
	@Autowired
	private IGovFactorDao dao;

	public void add(GovFactor obj) {
		dao.add(obj);

	}

	public void delete(int id) {
		dao.delete(id);

	}

	public void update(Map<String,String> map) {
		dao.update(map);

	}

	public GovFactor getById(int id) {
		try {
			return dao.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<GovFactor> getByTypeName(String typename) {
		try {
			return dao.getByTypeName(typename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<GovFactor> getTypex(String typename) {
		try {
			return dao.getTypex(typename);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GovFactor getByThree(String typename, String x, String y, String y2) {
		try {
			return dao.getByThree(typename, x, y, y2);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GovFactor getByFour(String typename, String x, String x2, String y,String y2) {
		try {
			return dao.getByFour(typename, x, x2, y, y2);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<GovFactor> getAnimal2Factor() {
		try {
			return dao.getAnimal2Factor();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
