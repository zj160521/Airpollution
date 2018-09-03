package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.Nfertilizer;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeNfertilizer {

	public void compute(ComputeService computeService,
			GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeNfertilizer");
		// 找出因子
		List<GovFactor> factorList = govFactorService
				.getByTypeName("nfertigation");
		
		double factor1 = 0;
		double factor2 = 0;
		double factor3 = 0;
		double factor4 = 0;
		double factor5 = 0;
		double factor6 = 0;
		for(GovFactor g:factorList){
			if(g.getType_x().equals("尿素EF因子")){
				factor1=g.getFactor();
			}else if(g.getType_x().equals("碳酸氢铵EF因子")){
				factor2=g.getFactor();
			}else if(g.getType_x().equals("硝酸铵EF因子")){
				factor3=g.getFactor();
			}else if(g.getType_x().equals("硫酸铵EF因子")){
				factor4=g.getFactor();
			}else if(g.getType_x().equals("氨水EF因子")){
				factor5=g.getFactor();
			}else if(g.getType_x().equals("其他EF因子")){
				factor6=g.getFactor();
			}
		}
		
		// 找出原始数据
		List<Nfertilizer> list = null;
		if(typeid==1){
			list = computeService.getAllNfertilizer2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllNfertilizer(fillyear);
		}
		
		//找出氮肥施用总量
		List<Nfertilizer> list1 =null;
		if(typeid==1){
			list1 =computeService.getSumNfertilizer2(fillyear);
		}else if(typeid==2){
			list1 =computeService.getSumNfertilizer(fillyear);
		}
		
		
		// 完成公式上半部分分解
		List<GovStat> resList1 = new ArrayList<GovStat>();
		for (Nfertilizer n : list) {
			int x = 1;
			for (int i = 1; i <= 12; i++) {
				GovStat govStat = new GovStat();
				govStat.setProvince(n.getProvince());
				govStat.setCity(n.getCity());
				govStat.setTown(n.getTown());
				govStat.setFillyear(n.getFillyear());
				govStat.setStattype2(n.getFerType());
				govStat.setStattype("nfertigation");
				govStat.setPollutantId(10);
				if (x == 1) {
					govStat.setStatvalue(n.getM1());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 2) {
					govStat.setStatvalue(n.getM2());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 3) {
					govStat.setStatvalue(n.getM3());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 4) {
					govStat.setStatvalue(n.getM4());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 5) {
					govStat.setStatvalue(n.getM5());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 6) {
					govStat.setStatvalue(n.getM6());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 7) {
					govStat.setStatvalue(n.getM7());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 8) {
					govStat.setStatvalue(n.getM8());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 9) {
					govStat.setStatvalue(n.getM9());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 10) {
					govStat.setStatvalue(n.getM10());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 11) {
					govStat.setStatvalue(n.getM11());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				else if (x == 12) {
					govStat.setStatvalue(n.getM12());
					govStat.setMonths(x);
					resList1.add(govStat);
				}
				x++;
			}
		}
		
		
		// 完成公式下半部分，带入因子计算
		List<GovStat> resList2 = new ArrayList<GovStat>();
		for (GovStat stat : resList1) {
			GovStat govStat = new GovStat();
			govStat.setProvince(stat.getProvince());
			govStat.setCity(stat.getCity());
			govStat.setTown(stat.getTown());
			govStat.setPollutantId(10);
			govStat.setStattype("nfertigation");
			govStat.setFillyear(stat.getFillyear());
			govStat.setStattype2(stat.getStattype2());
			govStat.setMonths(stat.getMonths());
			double value = 0;
			for (Nfertilizer n : list1) {
				if (stat.getProvince() == n.getProvince()
						&& stat.getCity() == n.getCity()
						&& stat.getTown() == n.getTown()) {
					if (n.getFerType() == 7001) {
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor1;
					} else if (n.getFerType() == 7002) {
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor2;
					} else if (n.getFerType() == 7003) {
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor3;
					} else if (n.getFerType() == 7004) {
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor4;
					} else if (n.getFerType() == 7005) {
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor5;
					} else if (n.getFerType() == 7006){
						value =value+ (stat.getStatvalue()/n.getAmountTotal())
								* factor6;
					}
				}
			}
			
			for (Nfertilizer n : list1) {
				if (stat.getProvince() == n.getProvince()
						&& stat.getCity() == n.getCity()
						&& stat.getTown() == n.getTown()) {
					value=value*n.getAmountTotal();
					govStat.setStat_value(n.getAmountTotal()+"");
				}
			}
			govStat.setStat_factor(factor1+" "+factor2+" "+factor3+" "+factor4+" "+factor5+" "+factor6);
			govStat.setStatvalue(value);
			govStat.setStat_exp("E=施肥总量*(施肥比例*EF)");
			govStat.setStat_valtype("计算氮肥施用");
			govStat.setStat_dsrate(0);
			resList2.add(govStat);
		}
		
		int i = 0; 
		if(resList2.size()>0){
			int count=0;
			List<GovStat> newlist=new ArrayList<GovStat>();
			for(GovStat sp:resList2) {
				if(!(sp.getStatvalue() >= 0)){
					sp.setStatvalue(0);
				}
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
