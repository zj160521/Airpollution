package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.Gasstation;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeGasStation {

	public void compute(ComputeService computeService,
			GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeGasStation");
		List<Gasstation> list = null;
		if(typeid==1){
			list = computeService.getAllGasStation2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllGasStation(fillyear);
		}
		List<GovFactor> factorList = govFactorService.getByTypeName("gasstation");
		GovFactor factor1 = factorList.get(0);
		GovFactor factor2 = factorList.get(1);
		GovFactor factor3 = factorList.get(2);

		List<GovStat> resList = new ArrayList<GovStat>();
		for (Gasstation gas : list) {
			double a = 0;
			double b = 0;
			double c = 0;
			double res = 0;
			String sa="";
			String sb="";
			String sc="";
			if (gas.getGasolineGross() / 2 >= gas.getGasolineSellMonth() * 12) {
				a = gas.getGasolineGross() / 2 * (1-gas.getRecovery() * 0.01) * factor1.getFactor();
				sa = gas.getGasolineGross() +" / "+ 2 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor1.getFactor();
			} else {
				a = gas.getGasolineSellMonth() * 12 * (1-gas.getRecovery() * 0.01) * factor1.getFactor();
				sa = gas.getGasolineSellMonth() +" X "+ 12 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor1.getFactor();
			}

			if (gas.getDieselGross() / 2 >= gas.getDieselSellMonth() * 12) {
				b = gas.getDieselGross() / 2 * (1-gas.getRecovery() * 0.01) * factor2.getFactor();
				sb = gas.getDieselGross() +" / "+ 2 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor2.getFactor();
			} else {
				b = gas.getDieselSellMonth() * 12 * (1-gas.getRecovery() * 0.01) * factor2.getFactor();
				sb = gas.getDieselSellMonth() +" X "+ 12 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor2.getFactor();
			}

			if (gas.getNatgasGross() / 2 >= gas.getNatgasSellMonth() * 12) {
				c = gas.getNatgasGross() / 2 * (1-gas.getRecovery() * 0.01) * factor3.getFactor();
				sc = gas.getNatgasGross() +" / "+ 2 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor3.getFactor();
			} else {
				c = gas.getNatgasSellMonth() * 12 * (1-gas.getRecovery() * 0.01) * factor3.getFactor();
				sc = gas.getNatgasSellMonth() +" X "+ 12 +" X ("+ 1+" - "+gas.getRecovery()+" X "+ 0.01 +") X "+ factor3.getFactor();
			}
			res = a + b + c;

			GovStat govStat = new GovStat();
			govStat.setProvince(gas.getProvince());
			govStat.setCity(gas.getCity());
			govStat.setFillyear(gas.getFillyear());
			govStat.setPollutantId(9);
			govStat.setStattype("gasstation");
			govStat.setTown(gas.getTown());
			govStat.setStatvalue(res);
			govStat.setStat_exp("(数据A*排放系数*(1-油气回收效率))+(数据B*排放系数*(1-油气回收效率))+(数据C*排放系数*(1-油气回收效率))");
			govStat.setStat_valtype(gas.getGasStationName());
			govStat.setStat_factor(factor1.getFactor()+" "+factor2.getFactor()+" "+factor3.getFactor());
			govStat.setStat_value(sa+" + "+sb+" + "+sc);
			govStat.setStat_dsrate(gas.getRecovery() * 0.01);
			resList.add(govStat);
		}
		
		if(resList.size()>0){
			int count=0;
			List<GovStat> newlist=new ArrayList<GovStat>();
			for(GovStat sp:resList) {
				if(count > 0 && count % 100 == 0) {
					if(typeid==1){
						numerationService.addGovStat2(newlist);
					}else if(typeid==2){
						numerationService.addGovStat(newlist);
					}
					newlist.clear();
				}
				else {
					newlist.add(sp);
				}
				count++;
			}
			if(newlist.size() > 0){
				if(typeid==1){
					numerationService.addGovStat2(newlist);
				}else if(typeid==2){
					numerationService.addGovStat(newlist);
				}
			}
		}
	}

}
