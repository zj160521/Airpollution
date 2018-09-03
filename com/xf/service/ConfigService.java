package com.xf.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IConfigDao;
import com.xf.entity.Config;

@Service
public class ConfigService implements IConfigDao {

	@Autowired
	private IConfigDao configDao;

	public void add(Config config) {
		configDao.add(config);
	}

	public void delete(String k) {
		configDao.delete(k);
	}

	public void update(Config config) {
		configDao.update(config);
	}

	public String get(String key) {
		return configDao.get(key);
	}

	public int getLastId() {
		return configDao.getLastId();
	}

	public int getLastYear() {
		String current_year = null;
		
		if (current_year == null)
			current_year = configDao.get("current_year");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int ret = year;
		try {
			if (current_year != null) {
				ret = Integer.parseInt(current_year);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Config getConfig(String key) {
		return configDao.getConfig(key);
	}
}
