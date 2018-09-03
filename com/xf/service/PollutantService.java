package com.xf.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.IPollutantDao;
import com.xf.entity.Pollutant;
import com.xf.vo.IntString;
import com.xf.vo.PollutantCount;

@Service
public class PollutantService implements IPollutantDao {

	@Autowired
	private IPollutantDao polluDao;
	
	public void add(Pollutant o) {
		polluDao.add(o);
	}

	public void delete(int id) {
		polluDao.delete(id);
	}

	public void update(Pollutant o) {
		polluDao.update(o);
	}

	public Pollutant getById(int id) {
		return polluDao.getById(id);
	}

	public List<Pollutant> getAll() {
		try {
			return polluDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Pollutant getByName(String name) {
		return polluDao.getByName(name);
	}

	public List<PollutantCount> getFactors(int tradeid,  int formulaid){
		try {
			List<PollutantCount> list=polluDao.getFactors(tradeid,formulaid);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<PollutantCount> getFactors2(int tradeid, int formulaid) {
		try {
			List<PollutantCount> list=polluDao.getFactors2(tradeid,formulaid);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public CopyOnWriteArrayList<IntString> getFNames(){
		try {
			CopyOnWriteArrayList<IntString> list=polluDao.getFNames();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getNames() {
		return polluDao.getNames();
	}

	public List<IntString> getSNames(int pid) {
		try {
			List<IntString> list=polluDao.getSNames(pid);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<IntString> getAllGroup() {
		try {
			List<IntString> list=polluDao.getAllGroup();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Pollutant> getTen() {
		return polluDao.getTen();
	}

	public void updateCofac(int newid, int oldid) {
		polluDao.updateCofac(newid, oldid);
	}

	public List<Pollutant> getBygroupid(int id) {
		return polluDao.getBygroupid(id);
	}

	public void updateFac(int newid, int oldid) {
		polluDao.updateFac(newid, oldid);
	}

	public void updateOutpoll(int newid, int oldid) {
		polluDao.updateOutpoll(newid, oldid);
	}
	
	public void updateVehifac(int newid, int oldid) {
		polluDao.updateVehifac(newid, oldid);
	}

	public void deleteStatdev(int pollid) {
		polluDao.deleteStatdev(pollid);
	}

	public void deleteStatgov(int pollid) {
		polluDao.deleteStatgov(pollid);
	}

	public void deleteStatmotor(int pollid) {
		polluDao.deleteStatmotor(pollid);
	}

	public void deleteStatpro(int pollid) {
		polluDao.deleteStatpro(pollid);
	}

	public List<Pollutant> getBasic() {
		try {
			return polluDao.getBasic();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Pollutant getByGrpName(String name) {
		try {
			return polluDao.getByGrpName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer getMaxGroupId() {
		try {
			return polluDao.getMaxGroupId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void add2(Pollutant o) {
		polluDao.add2(o);
	}
}
