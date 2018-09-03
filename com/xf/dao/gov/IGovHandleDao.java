package com.xf.dao.gov;

import java.util.List;
import java.util.Map;

import com.xf.entity.gov.VgovTName;
import com.xf.vo.GovHandle;


public interface IGovHandleDao {
 public List<GovHandle> getAll(Map<String,String> map);
 public List<VgovTName> getTName();
}
