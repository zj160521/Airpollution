package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.PollutantStat;
import com.xf.vo.StatProd;

public interface ISurfacePollutantDao {
public List<StatProd> getYear();
public List<PollutantStat> getPollutantBycity(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> getPollutantBycity2(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> cityPollutantPm(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> cityPollutantPm2(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> getRestaurant(int fillyear);
public List<PollutantStat> getRestaurant2(int fillyear);
public List<PollutantStat> getFirewoodType(@Param("fillyear")int fillyear,@Param("stattype")String stattype,@Param("groupid")int groupid);
public List<PollutantStat> getFirewoodType2(@Param("fillyear")int fillyear,@Param("stattype")String stattype,@Param("groupid")int groupid);
public List<PollutantStat> getCityFirewoodType(@Param("groupid")int groupid,@Param("year")int year,@Param("stattype")String stattype);
public List<PollutantStat> getCityFirewoodType2(@Param("groupid")int groupid,@Param("year")int year,@Param("stattype")String stattype);
public List<PollutantStat> getNfertigation(int fillyear);
public List<PollutantStat> getNfertigation2(int fillyear);
public List<PollutantStat> getCityNfertigation(@Param("type")int type,@Param("pollutantId")int pollutantId,@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> getCityNfertigation2(@Param("type")int type,@Param("pollutantId")int pollutantId,@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> cityType(@Param("type")int type,@Param("pollutantid")int pollutantid,@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> cityType2(@Param("type")int type,@Param("pollutantid")int pollutantid,@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> typeOffarming(@Param("pollutantId")int pollutantId,@Param("fillyear")int fillyear);
public List<PollutantStat> typeOffarming2(@Param("pollutantId")int pollutantId,@Param("fillyear")int fillyear);
public List<PollutantStat> getCivilfuel(int fillyear,int groupid);
public List<PollutantStat> getCivilfuel2(int fillyear,int groupid);
public List<PollutantStat> sourceValue(int fillyear);
public List<PollutantStat> sourceValue2(int fillyear);
public List<PollutantStat> monthsPollutant(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> monthsPollutant2(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> pollutantMachine(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> pollutantMachine2(@Param("fillyear")int fillyear,@Param("stattype")String stattype);
public List<PollutantStat> roaddustPollutant(int fillyear);
public List<PollutantStat> roaddustPollutant2(int fillyear);
public List<PollutantStat> statDevice(int fillyear);
public List<PollutantStat> statDevice2(int fillyear);
public List<PollutantStat> statProduct(int fillyear);
public List<PollutantStat> statProduct2(int fillyear);
public List<PollutantStat> cityStatDevice(int fillyear);
public List<PollutantStat> cityStatProduct(int fillyear);
public List<PollutantStat> cityStatDevice2(int fillyear);
public List<PollutantStat> cityStatProduct2(int fillyear);
public List<PollutantStat> citySourceAll(int fillyear);
public List<PollutantStat> citySourceAll2(int fillyear);
public List<LookupCityFeulRes> getMotorpollutant(int year);
public List<LookupCityFeulRes> getMotorpollutant2(int year);
}
