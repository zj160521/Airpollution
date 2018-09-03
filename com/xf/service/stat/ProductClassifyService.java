package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.IProductClassifyDao;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;
@Service
public class ProductClassifyService implements IProductClassifyDao{
	@Autowired
	private IProductClassifyDao dao;
	
	public ProductFill findPrYield(int id) {
		return dao.findPrYield(id);
	}
	public void addGroup(Product prg) {
		dao.addGroup(prg);
	}
	public List<Product> findGroupBytrade(int tradeid) {
		return dao.findGroupBytrade(tradeid);
	}
	public void updatePGroup(int groupid, int id) {
		dao.updatePGroup(groupid, id);
	}
	public List<Product> findProBygid(int groupid) {
		return dao.findProBygid(groupid);
	}
	public void updateGroup(Product PGro) {
		dao.updateGroup(PGro);
	}
	public void deleteGroup(int id) {
		dao.deleteGroup(id);
	}
	public void updatePGNull(int groupid) {
		dao.updatePGNull(groupid);
	}
	public void updateNullByid(int groupid,int id) {
		dao.updateNullByid(groupid,id);
	}
	public List<Product> getProduct(int companyid) {
		return dao.getProduct(companyid);
	}
	public List<Product> findProductName(int tradeid) {
		return dao.findProductName(tradeid);
	}
	public String getgByid(int id) {
		return dao.getgByid(id);
	}
	public Product checkName(String productName) {
		return dao.checkName(productName);
	}

}
