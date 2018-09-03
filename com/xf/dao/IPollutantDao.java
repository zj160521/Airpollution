package com.xf.dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Pollutant;
import com.xf.vo.IntString;
import com.xf.vo.PollutantCount;


public interface IPollutantDao {

	public void add(Pollutant o);
	public void add2(Pollutant o); 
	public void delete(int id);
	public void update(Pollutant o);
	public Pollutant getById(int id);
	public Pollutant getByName(String name);
	public Pollutant getByGrpName(String name);
	public List<Pollutant> getAll();
	public List<Pollutant> getBasic();
	public List<Pollutant> getTen();
	public List<PollutantCount> getFactors(int tradeid, int formulaid);//获取二级燃料的污染物因子
	public List<PollutantCount> getFactors2(int tradeid, int formulaid);//获取一级燃料污染物因子
	public CopyOnWriteArrayList<IntString> getFNames();//获取已设置了因子的ap_static中的燃料名字
	public List<String> getNames();//获取ap_pollutant中污染物的名字
	public List<IntString> getSNames(int pid);
	public List<IntString> getAllGroup();
	public List<Pollutant> getBygroupid(int id);
	public void updateCofac(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateFac(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateOutpoll(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateVehifac(@Param("newid")int newid,@Param("oldid")int oldid);
	public void deleteStatdev(int pollid);
	public void deleteStatgov(int pollid);
	public void deleteStatmotor(int pollid);
	public void deleteStatpro(int pollid);
	public Integer getMaxGroupId();
}
