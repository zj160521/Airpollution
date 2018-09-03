package com.xf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IStaticDao;
import com.xf.entity.Static;
import com.xf.vo.IntString;
import com.xf.vo.SecondLevelFuelFactorVo;

@Service
public class StaticService implements IStaticDao {
	// 此类中调用IUserDao中所有方法
	@Autowired
	private IStaticDao staticDao;

	public void add(Static info) {
		int maxid = getMaxId(info.getGroupid());
		info.setId((maxid>100000)?maxid:(maxid+100000));
		
		if (info.getGroupname() == null || info.getGroupname().isEmpty()) {
			Static st = getById(maxid-1);
			if (st == null) return;
			
			info.setGroupname(st.getGroupname());
		}
		
		staticDao.add(info);
	}

	public void update(Static info) {
		staticDao.update(info);
	}
	
	public void delete(int id) {
		staticDao.delete(id);
	}
	
	public Static getById(int id) {
		return staticDao.getById(id);
	}
	
	public int getMaxId(int groupid) {
		return staticDao.getMaxId(groupid);
	}

	public List<Static> getAll() {
		return staticDao.getAll();
	}
	
	public List<Static> getGroup(Static info) {
		return staticDao.getGroup(info);
	}

	public List<Static> getPid(Static info) {
		return staticDao.getPid(info);
	}

	public List<Static> getGroupPid(Static info) {
		return staticDao.getGroupPid(info);
	}

	public List<String> getNames() {
		
		return staticDao.getNames();
	}

	public Static getByFuelName(String name) {
		
		try {
			return staticDao.getByFuelName(name);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Static> getType() {
		return staticDao.getType();
	}

	public List<IntString> getTech1() {
		
		try {
			return staticDao.getTech1();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Static> getByPid(int pid) {
		try {
			return staticDao.getByPid(pid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Static> findType() {
		return staticDao.findType();
	}

	public List<Static> getProductfuel(int cpmpanyid) {
		return staticDao.getProductfuel(cpmpanyid);
	}

	public List<Static> getFuel() {
		return staticDao.getFuel();
	}

	public List<Static> getGl() {
		try {
			return staticDao.getGl();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllNames() {

		try {
			return staticDao.getAllNames();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Static getByRemark(String remark) {
		try {
			return staticDao.getByRemark(remark);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Static> getByName(String name) {
		return staticDao.getByName(name);
	}

	public void updateElec(int newid, int oldid) {
		staticDao.updateElec(newid, oldid);
	}

	public void deleteBypid(int pid) {
		staticDao.deleteBypid(pid);
	}

	public void updateFac(int new1, int new2, int old1, int old2) {
		staticDao.updateFac(new1, new2, old1, old2);
	}

	public void updateDev(int newid, int oldid) {
		staticDao.updateDev(newid, oldid);
		
	}

	public void updateFire(int newid, int oldid) {
		staticDao.updateFire(newid, oldid);
		
	}

	public void updateGK(int newid, int oldid) {
		staticDao.updateGK(newid, oldid);
	}

	public void updateNflay(int newid, int oldid) {
		staticDao.updateNflay(newid, oldid);
	}

	public void updateNffer(int newid, int oldid) {
		staticDao.updateNffer(newid, oldid);
	}

	public void updateOildev(int newid, int oldid) {
		staticDao.updateOildev(newid, oldid);
	}

	public void updateOilcon(int newid, int oldid) {
		staticDao.updateOilcon(newid, oldid);
	}

	public void updateRoad(int newid, int oldid) {
		staticDao.updateRoad(newid, oldid);
	}

	public void deleteStadev(int id) {
		staticDao.deleteStadev(id);
	}

	public void deleteStagov(int id) {
		staticDao.deleteStagov(id);
	}

	public void deleteStamotor(int id) {
		staticDao.deleteStamotor(id);
	}

	public void deleteStapro(int id) {
		staticDao.deleteStapro(id);
	}

	public void updateVehifac(int newid, int oldid) {
		staticDao.updateVehifac(newid, oldid);
	}

	public void updateVehistan(int newid, int oldid) {
		staticDao.updateVehistan(newid, oldid);
	}

	public List<String> getNameByGroupid(Static info) {
		return staticDao.getNameByGroupid(info);
	}

	public List<Static> getBygroupid(int groupid) {
		return staticDao.getBygroupid(groupid);
	}

	public List<Static> getAllBygroupid(int groupid) {
		try {
			return staticDao.getAllBygroupid(groupid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SecondLevelFuelFactorVo> getByPidAndIdIn2000(int tradeId,int pid,int formulaId) {
		try {
			return staticDao.getByPidAndIdIn2000(tradeId,pid,formulaId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
