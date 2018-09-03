package com.xf.dao.stat;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xf.vo.DetailsOfindustry;

public interface IDetailOfindustryDao {
	public List<DetailsOfindustry> getCompanyvalue(@Param("list") List<Integer> item);
	public List<DetailsOfindustry> getCompanyfill(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getDevice(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getProduct(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getPollutant(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getElec(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getFacility(@Param("list") List<Integer> item,@Param("fillyear") int fillyear);
	public List<DetailsOfindustry> getMaterialBypfid(int productfillId);
	public List<DetailsOfindustry> getMaterial();
	public List<DetailsOfindustry> getDeviceByfaid(int facilityId);
	public List<DetailsOfindustry> getDeviceAll();
	public List<DetailsOfindustry> getStepByfaid(int facilityId);
	public List<DetailsOfindustry> getStep();
}
