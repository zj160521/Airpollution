package com.xf.service.gov;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.gov.IGovHandleDao;
import com.xf.entity.gov.VgovTName;
import com.xf.vo.GovHandle;

@Service
public class GovHandleService implements IGovHandleDao {
	@Autowired
private IGovHandleDao igovhandleDao;

	public List<GovHandle> getAll(Map<String,String> map) {
		
		List<GovHandle> gov=igovhandleDao.getAll(map);
		return gov;
	}

	public List<VgovTName> getTName() {
		return igovhandleDao.getTName();
	}

}
