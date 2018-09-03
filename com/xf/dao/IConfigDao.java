package com.xf.dao;

import com.xf.entity.Config;


public interface IConfigDao {

	public void add(Config config);
	public void delete(String k);
	public void update(Config config);
	public String get(String key);
	public Config getConfig(String key);
	public int getLastId();
}
