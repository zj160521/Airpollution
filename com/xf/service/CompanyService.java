package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.ICompanyDao;
import com.xf.entity.Company;
import com.xf.entity.CompanyVo;
import com.xf.entity.Condition;
import com.xf.entity.Role;
import com.xf.entity.Searchform;
import com.xf.entity.SysArea;
import com.xf.entity.VocsTrade;
import com.xf.vo.ListInteger;

@Service
public class CompanyService implements ICompanyDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private ICompanyDao companyDao;

	public void add(Company company) {
		companyDao.add(company);
	}

	public void delete(int id) {
		companyDao.delete(id);
	}

	public void update(Company company) {
		companyDao.update(company);
	}

	public Company getById(int id) {
		return companyDao.getById(id);
	}

	public Company getByName(String name) {
		try {
			return companyDao.getByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Company> getAll(int city) {
		return companyDao.getAll(city);
	}

	public Company CName(int typeid) {
		return companyDao.CName(typeid);
	}

	public void getGroupname(Company company) {
		
	}

	public Company getCode(String code) {
		return companyDao.getCode(code);
		
	}

	public List<Company> getGov() {
		return companyDao.getGov();
	}

	public Company getByContact(String contact) {
		try {
			Company com=companyDao.getByContact(contact);
			return com;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Company> findCompany(int tradeid) {
		return companyDao.findCompany(tradeid);
	}

	public List<Company> getByCity(int cityid) {

		try {
			return companyDao.getByCity(cityid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Company> getCityTown(Condition condition) {
		return companyDao.getCityTown(condition);
	}

	public List<Company> getCompany(int tradeid) {
		return companyDao.getCompany(tradeid);
	}

	public List<Company> getCompanyCount(int id) {
		return companyDao.getCompanyCount(id);
	}

	public List<Company> getByantistop(String antistop) {
		return companyDao.getByantistop(antistop);
	}

	public List<Company> getGovby(String name) {
		return companyDao.getGovby(name);
	}

	public void updateRemark(int id) {
		companyDao.updateRemark(id);
	}

	public List<Company> getProvince(int typeid) {
		return companyDao.getProvince(typeid);
	}

	public SysArea getDisByPath(String path) {
		try {
			return companyDao.getDisByPath(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public VocsTrade getTraByPath(String path) {
		try {
			return companyDao.getTraByPath(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CompanyVo> getByAreaId(int areaId) {
		try {
			return companyDao.getByAreaId(areaId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updatePwd(ListInteger li) {
		companyDao.updatePwd(li);
	}

	public List<CompanyVo> getAll2(Searchform searchform) {
		try {
			return companyDao.getAll2(searchform);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer getTotalRecs(Searchform searchform) {
		try {
			return companyDao.getTotalRecs(searchform);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<CompanyVo> getBoth(Role role) {
		try {
			return companyDao.getBoth(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
