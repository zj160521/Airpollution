package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.ISurfacePollutantDao;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.PollutantStat;
import com.xf.vo.StatProd;
@Service
public class SurfacePollutantService implements ISurfacePollutantDao{
	@Autowired
	private ISurfacePollutantDao dao;
	public List<PollutantStat> getPollutantBycity(int fillyear,String stattype) {
		return dao.getPollutantBycity(fillyear,stattype);
	}
	public List<PollutantStat> getRestaurant(int fillyear) {
		return dao.getRestaurant(fillyear);
	}
	public List<StatProd> getYear() {
		return dao.getYear();
	}
	public List<PollutantStat> getFirewoodType(int fillyear, String stattype,int groupid) {
		return dao.getFirewoodType(fillyear, stattype,groupid);
	}
	public List<PollutantStat> getNfertigation(int fillyear) {
		return dao.getNfertigation(fillyear);
	}
	public List<PollutantStat> getCityNfertigation(int type,int pollutantId,int fillyear, String stattype) {
		return dao.getCityNfertigation(type,pollutantId,fillyear, stattype);
	}
	public List<PollutantStat> typeOffarming(int pollutantId, int fillyear) {
		return dao.typeOffarming(pollutantId, fillyear);
	}
	public List<PollutantStat> getCivilfuel(int fillyear, int groupid) {
		return dao.getCivilfuel(fillyear, groupid);
	}
	public List<PollutantStat> sourceValue(int fillyear) {
		return dao.sourceValue(fillyear);
	}
	public List<PollutantStat> monthsPollutant(int fillyear, String stattype) {
		return dao.monthsPollutant(fillyear, stattype);
	}
	public List<PollutantStat> cityPollutantPm(int fillyear, String stattype) {
		return dao.cityPollutantPm(fillyear, stattype);
	}
	public List<PollutantStat> pollutantMachine(int fillyear,String stattype) {
		return dao.pollutantMachine(fillyear,stattype);
	}
	public List<PollutantStat> roaddustPollutant(int fillyear) {
		return dao.roaddustPollutant(fillyear);
	}
	public List<PollutantStat> statDevice(int fillyear) {
		return dao.statDevice(fillyear);
	}
	public List<PollutantStat> statProduct(int fillyear) {
		return dao.statProduct(fillyear);
	}
	public List<PollutantStat> cityStatDevice(int fillyear) {
		return dao.cityStatDevice(fillyear);
	}
	public List<PollutantStat> cityStatProduct(int fillyear) {
		return dao.cityStatProduct(fillyear);
	}
	public List<PollutantStat> citySourceAll(int fillyear) {
		return dao.citySourceAll(fillyear);
	}
	public List<LookupCityFeulRes> getMotorpollutant(int year) {
		return dao.getMotorpollutant(year);
	}
	public List<PollutantStat> getCityFirewoodType(int groupid, int year,
			String stattype) {
		return dao.getCityFirewoodType(groupid, year, stattype);
	}
	public List<PollutantStat> cityType(int type, int pollutantid,
			int fillyear, String stattype) {
		return dao.cityType(type, pollutantid, fillyear, stattype);
	}
	public List<PollutantStat> getCityFirewoodType2(int groupid, int year,
			String stattype) {
		return dao.getCityFirewoodType2(groupid, year, stattype);
	}
	public List<PollutantStat> getPollutantBycity2(int fillyear, String stattype) {
		return dao.getPollutantBycity2(fillyear, stattype);
	}
	public List<PollutantStat> getRestaurant2(int fillyear) {
		return dao.getRestaurant2(fillyear);
	}
	public List<PollutantStat> getNfertigation2(int fillyear) {
		return dao.getNfertigation2(fillyear);
	}
	public List<PollutantStat> cityType2(int type, int pollutantid,
			int fillyear, String stattype) {
		return dao.cityType2(type, pollutantid, fillyear, stattype);
	}
	public List<PollutantStat> getCivilfuel2(int fillyear, int groupid) {
		return dao.getCivilfuel2(fillyear, groupid);
	}
	public List<PollutantStat> typeOffarming2(int pollutantId, int fillyear) {
		return dao.typeOffarming2(pollutantId, fillyear);
	}
	public List<PollutantStat> getCityNfertigation2(int type, int pollutantId,
			int fillyear, String stattype) {
		return dao.getCityNfertigation2(type, pollutantId, fillyear, stattype);
	}
	public List<PollutantStat> cityPollutantPm2(int fillyear, String stattype) {
		return dao.cityPollutantPm2(fillyear, stattype);
	}
	public List<PollutantStat> pollutantMachine2(int fillyear, String stattype) {
		return dao.pollutantMachine2(fillyear, stattype);
	}
	public List<PollutantStat> monthsPollutant2(int fillyear, String stattype) {
		return dao.monthsPollutant2(fillyear, stattype);
	}
	public List<PollutantStat> getFirewoodType2(int fillyear, String stattype,
			int groupid) {
		return dao.getFirewoodType2(fillyear, stattype, groupid);
	}
	public List<LookupCityFeulRes> getMotorpollutant2(int year) {
		return dao.getMotorpollutant2(year);
	}
	public List<PollutantStat> sourceValue2(int fillyear) {
		return dao.sourceValue2(fillyear);
	}
	public List<PollutantStat> statDevice2(int fillyear) {
		return dao.statDevice2(fillyear);
	}
	public List<PollutantStat> statProduct2(int fillyear) {
		return dao.statProduct2(fillyear);
	}
	public List<PollutantStat> cityStatDevice2(int fillyear) {
		return dao.cityStatDevice2(fillyear);
	}
	public List<PollutantStat> cityStatProduct2(int fillyear) {
		return dao.cityStatProduct2(fillyear);
	}
	public List<PollutantStat> citySourceAll2(int fillyear) {
		return dao.citySourceAll2(fillyear);
	}
	public List<PollutantStat> roaddustPollutant2(int fillyear) {
		return dao.roaddustPollutant2(fillyear);
	}
}
