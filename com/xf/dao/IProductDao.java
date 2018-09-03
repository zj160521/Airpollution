package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.CompanyProduct;
import com.xf.entity.Product;

public interface IProductDao {

	public void add(Product product);
	public void delete(int id);
	public void update(Product product);
	public void updatePro(Product product);
	public Product getById(int id);
	public List<Product> getByCompany(int companyid);
	public List<Product> getByStep(int stepid);
	public List<Product> getAll();
	public int getCount(int id);
	public List<CompanyProduct> findProduct(@Param("groupid") int groupid, @Param("productName") String productName,
			@Param("tradeid") int tradeid,@Param("year") int year,@Param("districtid") int districtid,@Param("isPass") int isPass);
	public List<Product> getGroupBytrade(int tradeid);
	public List<Product> getProductfac();
	public List<Product> getByName(Product p);
}
