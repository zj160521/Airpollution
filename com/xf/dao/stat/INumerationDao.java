package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.entity.gov.Boat;
import com.xf.entity.gov.Cleaner;
import com.xf.entity.gov.Construction;
import com.xf.entity.gov.DumpField;
import com.xf.entity.gov.EnergySell;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.Oildepot;
import com.xf.entity.gov.Pesticide;

public interface INumerationDao {
	public List<Cleaner> getCleanerNumber(int fillyear);

	public List<GovFactor> getGovfactor(@Param("typename") String typename);
	public List<GovFactor> getXfactor(@Param("typename") String typename);
	public List<GovFactor> getX2factor(@Param("typename") String typename);

	public void addGovStat(List<GovStat> stat);
	public void addGovStat2(List<GovStat> stat);
	public List<Boat> getBoatNumber(int fillyear);

	public List<Oildepot> getOildepotNumber(int fillyear);
	
	public List<Boat> getBoatCity(int year);
	public List<Boat> getBoatCity2(int year);
	public List<Boat> getBoatMonth(int year);
	public List<Boat> getBoatMonth2(int year);
	public List<Pesticide> getPesticideNumber(int fillyear);
	public List<EnergySell> getEnergysell(int fillyear);
	public List<EnergySell> getEnergyconsume(int fillyear);
	public List<DumpField> getDumpField(int fillyear);
	public List<Construction> getConstruction(int fillyear);
	
	public List<Pesticide> getPesticideNumber2(int fillyear);
	public List<EnergySell> getEnergysell2(int fillyear);
	public List<EnergySell> getEnergyconsume2(int fillyear);
	public List<DumpField> getDumpField2(int fillyear);
	public List<Construction> getConstruction2(int fillyear);
	public List<Boat> getBoatNumber2(int fillyear);
	public List<Oildepot> getOildepotNumber2(int fillyear);
	public List<Cleaner> getCleanerNumber2(int fillyear);
}
