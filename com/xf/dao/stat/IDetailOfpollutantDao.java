package com.xf.dao.stat;

import java.util.List;

import com.xf.entity.Condition;
import com.xf.vo.Details;

public interface IDetailOfpollutantDao {
public List<Details> getDetailDevice(Condition condition);
public List<Details> getDetailProduct(Condition condition);
}
