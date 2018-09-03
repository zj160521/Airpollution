package com.xf.service.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xf.dao.stat.IDetailOfindustryDao;
import com.xf.vo.DetailsOfindustry;
@Service
public class DetailsOfindustryService implements IDetailOfindustryDao {
	@Autowired
	private IDetailOfindustryDao dao;
	
	public List<DetailsOfindustry> getCompanyvalue(List<Integer> companyid) {
		return dao.getCompanyvalue(companyid);
	}

	public List<DetailsOfindustry> getDevice(List<Integer> companyid,int fillyear) {
		return dao.getDevice(companyid,fillyear);
	}

	public List<DetailsOfindustry> getProduct(List<Integer> companyid,int fillyear) {
		return dao.getProduct(companyid,fillyear);
	}

	public List<DetailsOfindustry> getPollutant(List<Integer> companyid,int fillyear) {
		return dao.getPollutant(companyid,fillyear);
	}

	public List<DetailsOfindustry> getElec(List<Integer> companyid,int fillyear) {
		return dao.getElec(companyid,fillyear);
	}

	public List<DetailsOfindustry> getFacility(List<Integer> companyid,int fillyear) {
		return dao.getFacility(companyid,fillyear);
	}

	public List<DetailsOfindustry> getMaterialBypfid(int productfillId) {
		return dao.getMaterialBypfid(productfillId);
	}

	public List<DetailsOfindustry> getDeviceByfaid(int facilityId) {
		return dao.getDeviceByfaid(facilityId);
	}

	public List<DetailsOfindustry> getStepByfaid(int facilityId) {
		return dao.getStepByfaid(facilityId);
	}

	public List<DetailsOfindustry> getCompanyfill(List<Integer> item,
			int fillyear) {
		return dao.getCompanyfill(item, fillyear);
	}

	public List<DetailsOfindustry> getMaterial() {
		return dao.getMaterial();
	}

	public List<DetailsOfindustry> getStep() {
		return dao.getStep();
	}

	public List<DetailsOfindustry> getDeviceAll() {
		return dao.getDeviceAll();
	}

}
