package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IProductDao;
import com.xf.entity.CompanyProduct;
import com.xf.entity.Product;

@Service
public class ProductService implements IProductDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IProductDao productDao;

	public void add(Product product) {
		productDao.add(product);
	}

	public void delete(int id) {
		productDao.delete(id);
	}

	public void update(Product product) {
		productDao.update(product);
	}

	public Product getById(int id) {
		return productDao.getById(id);
	}

	public List<Product> getByCompany(int companyid) {
		return productDao.getByCompany(companyid);
	}

	public List<Product> getByStep(int stepid) {
		return productDao.getByStep(stepid);
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}

	public void updatePro(Product product) {
		productDao.updatePro(product);
	}

	public int getCount(int id) {
		return productDao.getCount(id);
	}

	public List<CompanyProduct> findProduct(int groupid, String productName,int tradeid,int year,int districtid,int isPass) {
		return productDao.findProduct(groupid, productName,tradeid,year,districtid,isPass);
	}

	public List<Product> getGroupBytrade(int tradeid) {
		return productDao.getGroupBytrade(tradeid);
	}

	public List<Product> getProductfac() {
		return productDao.getProductfac();
	}

	public List<Product> getByName(Product p) {
		return productDao.getByName(p);
	}
}
