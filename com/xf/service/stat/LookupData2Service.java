package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.LookupData2Dao;
import com.xf.entity.gov.Gknumber;
import com.xf.entity.gov.RoadDust;
import com.xf.vo.CityNumber;
import com.xf.vo.CityPitchType;
import com.xf.vo.CityProdSumRes;
import com.xf.vo.FourValues;
import com.xf.vo.GasStationByCity;
import com.xf.vo.LookupCityFeulRes;
import com.xf.vo.LookupCityTradeASRes;
import com.xf.vo.LookupFeulTypeRes;
import com.xf.vo.LookupMotorStatRes;
import com.xf.vo.NameFirewood;
import com.xf.vo.NameValue;
import com.xf.vo.NameValue2;
import com.xf.vo.NfertilizerByCityRes;
import com.xf.vo.PollutantStat;

@Service
public class LookupData2Service implements LookupData2Dao{
	@Autowired
	private LookupData2Dao dao;

	public List<LookupFeulTypeRes> getNfertilizerByType(int year) {
		return dao.getNfertilizerByType(year);
	}

	public List<CityProdSumRes> getNfertilizerByMonth(int year) {
		return dao.getNfertilizerByMonth(year);
	}

	public List<GasStationByCity> getGasStationByCity(int year) {
		return dao.getGasStationByCity(year);
	}

	public List<NfertilizerByCityRes> getNfertilizerByCity(int year) {
		return dao.getNfertilizerByCity(year);
	}

	public List<GasStationByCity> getOildepot(int year) {
		return dao.getOildepot(year);
	}

	public List<Gknumber> getGknumber(int year) {
		return dao.getGknumber(year);
	}

	public List<Gknumber> getGknumberBytype(int year, int start, int end) {
		return dao.getGknumberBytype(year, start, end);
	}

	public List<NameValue> getGknumberByCity(int year, int start, int end) {
		return dao.getGknumberByCity(year, start, end);
	}

	public List<CityNumber> getCanyinByCity(int year) {
		return dao.getCanyinByCity(year);
	}

	public List<NameFirewood> getWoodByType(int year,int city) {
		return dao.getWoodByType(year,city);
	}

	public List<NameValue> getPlaneByType(int year) {
		return dao.getPlaneByType(year);
	}

	public List<NameValue> getRoaddustByCity(int year) {
		return dao.getRoaddustByCity(year);
	}

	public List<LookupCityFeulRes> getRoaddustBytype(int year) {
		return dao.getRoaddustBytype(year);
	}

	public List<CityPitchType> getRoaddustByPitch(int year) {
		return dao.getRoaddustByPitch(year);
	}

	public List<NameValue2> getConstructionByCity(int year) {
		return dao.getConstructionByCity(year);
	}

	public List<NameValue2> getConstructionByCity2(int year) {
		return dao.getConstructionByCity2(year);
	}

	public List<NameValue2> getConstructionByCity3(int year) {
		return dao.getConstructionByCity3(year);
	}

	public List<NameValue> getFarmingByType(int year) {
		return dao.getFarmingByType(year);
	}

	public List<NameValue> getFarmingByType2(int year) {
		return dao.getFarmingByType2(year);
	}

	public List<NameValue> getFarmingByType3(int year) {
		return dao.getFarmingByType3(year);
	}

	public List<FourValues> getRoadType(int year) {
		return dao.getRoadType(year);
	}

	public List<RoadDust> getRoadCity(int year) {
		return dao.getRoadCity(year);
	}

	public List<RoadDust> getRoadByCity(int year) {
		return dao.getRoadByCity(year);
	}

	public List<PollutantStat> motorCity(int year) {
		return dao.motorCity(year);
	}

	public List<PollutantStat> motorType(int year) {
		return dao.motorType(year);
	}

	public List<LookupMotorStatRes> getMotorStat(int year) {
		return dao.getMotorStat(year);
	}

	public List<LookupCityFeulRes> getMotorstatByGas(int year) {
		return dao.getMotorstatByGas(year);
	}

	public List<LookupCityTradeASRes> getMotorstatByMotorType(int year) {
		return dao.getMotorstatByMotorType(year);
	}

	public List<NameFirewood> getWoodByType2(int year, int city) {
		return dao.getWoodByType2(year, city);
	}

	public List<LookupFeulTypeRes> getNfertilizerByType2(int year) {
		return dao.getNfertilizerByType2(year);
	}

	public List<NfertilizerByCityRes> getNfertilizerByCity2(int year) {
		return dao.getNfertilizerByCity2(year);
	}

	public List<CityProdSumRes> getNfertilizerByMonth2(int year) {
		return dao.getNfertilizerByMonth2(year);
	}

	public List<FourValues> getRoadType2(int year) {
		return dao.getRoadType2(year);
	}

	public List<RoadDust> getRoadCity2(int year) {
		return dao.getRoadCity2(year);
	}

	public List<NameValue2> getConstructionByCity_pc(int year) {
		return dao.getConstructionByCity_pc(year);
	}

	public List<CityNumber> getCanyinByCity2(int year) {
		return dao.getCanyinByCity2(year);
	}

	public List<Gknumber> getGknumber2(int year) {
		return dao.getGknumber2(year);
	}

	public List<NameValue> getGknumberByCity2(int year, int start, int end) {
		return dao.getGknumberByCity2(year, start, end);
	}

	public List<GasStationByCity> getGasStationByCity2(int year) {
		return dao.getGasStationByCity2(year);
	}

	public List<GasStationByCity> getOildepot2(int year) {
		return dao.getOildepot2(year);
	}

	public List<NameValue> getRoaddustByCity2(int year) {
		return dao.getRoaddustByCity2(year);
	}

	public List<LookupCityFeulRes> getRoaddustBytype2(int year) {
		return dao.getRoaddustBytype2(year);
	}

	public List<LookupCityTradeASRes> getMotorstatByMotorType2(int year) {
		return dao.getMotorstatByMotorType2(year);
	}

	public List<LookupCityFeulRes> getMotorstatByGas2(int year) {
		return dao.getMotorstatByGas2(year);
	}

	public List<NameValue> getFarmingByType_pc(int year) {
		return dao.getFarmingByType_pc(year);
	}

	public List<NameValue> getFarmingByType2_pc(int year) {
		return dao.getFarmingByType2_pc(year);
	}

	public List<NameValue> getFarmingByType3_pc(int year) {
		return dao.getFarmingByType3_pc(year);
	}
	
}
