package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.INumerationDao;
import com.xf.entity.gov.Boat;
import com.xf.entity.gov.Cleaner;
import com.xf.entity.gov.Construction;
import com.xf.entity.gov.DumpField;
import com.xf.entity.gov.EnergySell;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.Oildepot;
import com.xf.entity.gov.Pesticide;

@Service
public class NumerationService implements INumerationDao {
	@Autowired
	private INumerationDao dao;

	public List<Cleaner> getCleanerNumber(int fillyear) {
		return dao.getCleanerNumber(fillyear);
	}

	public List<GovFactor> getGovfactor(String typename) {
		return dao.getGovfactor(typename);
	}

	public void addGovStat(List<GovStat> stat) {
		dao.addGovStat(stat);
	}

	public List<Boat> getBoatNumber(int fillyear) {
		return dao.getBoatNumber(fillyear);
	}

	public List<Oildepot> getOildepotNumber(int fillyear) {
		return dao.getOildepotNumber(fillyear);
	}

	public List<Boat> getBoatCity(int year) {
		return dao.getBoatCity(year);
	}

	public List<Boat> getBoatMonth(int year) {
		return dao.getBoatMonth(year);
	}

	public List<GovFactor> getXfactor(String typename) {
		return dao.getXfactor(typename);
	}

	public List<GovFactor> getX2factor(String typename) {
		return dao.getX2factor(typename);
	}

	public List<Pesticide> getPesticideNumber(int fillyear) {
		return dao.getPesticideNumber(fillyear);
	}

	public List<EnergySell> getEnergysell(int fillyear) {
		return dao.getEnergysell(fillyear);
	}

	public List<EnergySell> getEnergyconsume(int fillyear) {
		return dao.getEnergyconsume(fillyear);
	}

	public List<DumpField> getDumpField(int fillyear) {
		return dao.getDumpField(fillyear);
	}

	public List<Construction> getConstruction(int fillyear) {
		return dao.getConstruction(fillyear);
	}

	public void addGovStat2(List<GovStat> stat) {
		dao.addGovStat2(stat);
	}

	public List<Pesticide> getPesticideNumber2(int fillyear) {
		return dao.getPesticideNumber2(fillyear);
	}

	public List<EnergySell> getEnergysell2(int fillyear) {
		return dao.getEnergysell2(fillyear);
	}

	public List<EnergySell> getEnergyconsume2(int fillyear) {
		return dao.getEnergyconsume2(fillyear);
	}

	public List<DumpField> getDumpField2(int fillyear) {
		return dao.getDumpField2(fillyear);
	}

	public List<Construction> getConstruction2(int fillyear) {
		return dao.getConstruction2(fillyear);
	}

	public List<Boat> getBoatNumber2(int fillyear) {
		return dao.getBoatNumber2(fillyear);
	}

	public List<Oildepot> getOildepotNumber2(int fillyear) {
		return dao.getOildepotNumber2(fillyear);
	}

	public List<Cleaner> getCleanerNumber2(int fillyear) {
		return dao.getCleanerNumber2(fillyear);
	}

	public List<Boat> getBoatMonth2(int year) {
		return dao.getBoatMonth2(year);
	}

	public List<Boat> getBoatCity2(int year) {
		return dao.getBoatCity2(year);
	}

}
