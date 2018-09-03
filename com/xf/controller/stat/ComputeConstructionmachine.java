package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xf.entity.gov.Equipment;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;
import com.xf.vo.ConstructionmachineFactor;

public class ComputeConstructionmachine {

	private void setValue(List<GovStat> reslist,Equipment e,ConstructionmachineFactor cf,int pollutantId){
		GovStat stat1 = new GovStat();
		stat1.setCity(e.getCity());
		stat1.setFillyear(e.getFillyear());
		stat1.setPollutantId(pollutantId);
		stat1.setProvince(e.getProvince());
		stat1.setStattype("constructionmachine");
		stat1.setTown(e.getTown());
		stat1.setStatvalue(e.getEnumber() * cf.getFactor1() * cf.getFactor2() * cf.getFactor3() * Math.pow(10, -6));
		stat1.setStattype2(e.getEtype());
		stat1.setStat_exp("E=N*T(活动水平=小时油耗*平均工作时长)*EF*0.000001");
		stat1.setStat_valtype("计算工程机械"+e.getEmodel()+e.getEtypeName()+"的排放量");
		stat1.setStat_factor(cf.getFactor1()+" "+cf.getFactor2()+" "+cf.getFactor3());
		stat1.setStat_value(e.getEnumber() +" X "+ cf.getFactor1() +" X "+ cf.getFactor2() +" X "+ cf.getFactor3() +" X "+ Math.pow(10, -6));
		stat1.setStat_dsrate(0);
		reslist.add(stat1);
	}
	public void compute(ComputeService computeService,GovFactorService govFactorService,NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeConstructionmachine");
		List<Equipment> list = null;
		if(typeid==1){
			list = computeService.getAllEquipment2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllEquipment(fillyear);
		}
		List<GovFactor> factorList = govFactorService.getByTypeName("constructionmachine");
		Set<String> set=new HashSet<String>();
		for(GovFactor gf:factorList){
			set.add(gf.getType_x()+gf.getType_x2()+gf.getType_y());
		}
		List<ConstructionmachineFactor> cflist=new ArrayList<ConstructionmachineFactor>();
		for(String str:set){
			ConstructionmachineFactor cf=new ConstructionmachineFactor();
			cf.setName(str);
			for(GovFactor gf:factorList){
				if((gf.getType_x()+gf.getType_x2()+gf.getType_y()).equals(str)){
					if(gf.getType_y2().contains("排放因子")){
						cf.setFactor1(gf.getFactor());
					}else if(gf.getType_y2().contains("小时油耗")){
						cf.setFactor2(gf.getFactor());
					}else if(gf.getType_y2().contains("年均工作时长")){
						cf.setFactor3(gf.getFactor());
					}
				}
			}
			cflist.add(cf);
		}
		
		List<GovStat> reslist = new ArrayList<GovStat>();
		for (Equipment e : list) {
			for(ConstructionmachineFactor cf:cflist){
				if(cf.getName().contains(e.getEtypeName()+e.getEmodel().substring(0, 1))){
					if(cf.getName().contains("PM")){
						setValue(reslist,e,cf,4);
						setValue(reslist,e,cf,6);
					}else if(cf.getName().contains("SO2")){
						setValue(reslist,e,cf,1);
					}else if(cf.getName().contains("NO2")){
						setValue(reslist,e,cf,102);
					}else if(cf.getName().contains("NO")){
						setValue(reslist,e,cf,101);
					}else if(cf.getName().contains("THC")){
						setValue(reslist,e,cf,9);
					}else if(cf.getName().contains("CO") && !cf.getName().contains("CO2")){
						setValue(reslist,e,cf,3);
					}else if(cf.getName().contains("CO2")){
						setValue(reslist,e,cf,103);
					}
				}
			}
		}
		if(reslist.size()>0){
			int count=0;
			List<GovStat> newlist=new ArrayList<GovStat>();
			for(GovStat sp:reslist) {
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
