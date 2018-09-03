package com.xf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.Static;
import com.xf.vo.IntString;
import com.xf.vo.SecondLevelFuelFactorVo;

public interface IStaticDao {

	public void add(Static info);
	public void update(Static info);
	public void delete(int id);
	public void deleteBypid(int pid);
	public Static getById(int id);
	public int getMaxId(int groupid);
	public List<Static> getAll();
	public List<Static> getGroup(Static info);
	public List<Static> getPid(Static info);
	public List<Static> getGroupPid(Static info);
	public List<String> getNames();
	public Static getByFuelName(String name);
	public List<Static> getType();
	public List<IntString> getTech1();
	public List<Static> getByPid(int pid);
	public List<SecondLevelFuelFactorVo> getByPidAndIdIn2000(@Param("tradeId")int tradeId, @Param("pid")int pid, @Param("formulaId")int formulaId);
	public List<Static> findType();
	public List<Static> getProductfuel(int cpmpanyid);
	public List<Static> getFuel();
	public List<Static> getGl();
	public List<String> getAllNames();
	public Static getByRemark(String remark);
	public List<Static> getByName(String name);
	public void updateElec(int newid,int oldid);
	public void updateFac(@Param("new1")int new1,@Param("new2")int new2,@Param("old1")int old1,@Param("old2")int old2);
	public void updateDev(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateFire(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateGK(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateNflay(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateNffer(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateOildev(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateOilcon(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateRoad(@Param("newid")int newid,@Param("oldid")int oldid);
	public void deleteStadev(int id);
	public void deleteStagov(int id);
	public void deleteStamotor(int id);
	public void deleteStapro(int id);
	public void updateVehifac(@Param("newid")int newid,@Param("oldid")int oldid);
	public void updateVehistan(@Param("newid")int newid,@Param("oldid")int oldid);
	public List<String> getNameByGroupid(Static info);
	public List<Static> getBygroupid(int groupid);
	public List<Static> getAllBygroupid(int groupid);
} 
