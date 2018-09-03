package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;

public interface IProductClassifyDao {
public List<Product> getProduct(int companyid);
public List<Product> findProductName(int tradeid);
public ProductFill findPrYield(int id);
public void addGroup(Product prg);
public List<Product> findGroupBytrade(int tradeid);
public void updatePGroup(int groupid,int id);
public List<Product> findProBygid(int groupid);
public void updateGroup(Product PGro);
public void deleteGroup(int id);
public void updatePGNull(int groupid);
public void updateNullByid(@Param("groupid") int groupid,@Param("id") int id);
public String getgByid(int id);
public Product checkName(String productName);
}
