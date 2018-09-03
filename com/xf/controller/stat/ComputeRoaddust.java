package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.RoadDust;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeRoaddust {
	public void compute(ComputeService computeService,GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid){
		System.out.println("ComputeRoaddust");
		List<RoadDust> list=null;
		if(typeid==1){
			list=computeService.getAllRoaddust2(fillyear);
		}else if(typeid==2){
			list=computeService.getAllRoaddust(fillyear);
		}
		List<GovFactor> factorList = govFactorService.getByTypeName("roaddust");
		GovFactor factor1 = factorList.get(0);
		GovFactor factor2 = factorList.get(1);
		GovFactor factor3 = factorList.get(2);
		GovFactor factor4 = factorList.get(3);
		GovFactor factor5 = factorList.get(4);
		GovFactor factor6 = factorList.get(5);
		GovFactor factor7 = factorList.get(6);
		GovFactor factor8 = factorList.get(7);
		GovFactor factor9 = factorList.get(8);
		GovFactor factor10 = factorList.get(9);
		GovFactor factor11 = factorList.get(10);
		GovFactor factor12 = factorList.get(11);
		GovFactor factor13 = factorList.get(12);
		GovFactor factor14 = factorList.get(13);
		GovFactor factor15 = factorList.get(14);
		GovFactor factor16 = factorList.get(15);
		
		List<GovStat> resList = new ArrayList<GovStat>();
		for(RoadDust stat:list){
			//快速路
			if(factor3.getFactor()>0&&factor4.getFactor()>0&&factor5.getFactor()>0&&factor6.getFactor()>0&&factor7.getFactor()>0&&factor8.getFactor()>0){
				GovStat stat1=new GovStat();
				stat1.setCity(stat.getCity());
				stat1.setFillyear(stat.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(stat.getProvince());
				stat1.setTown(stat.getTown());
				stat1.setStattype("roaddust");
				double res=(factor3.getFactor()*(Math.pow(factor4.getFactor(), 0.91))*(Math.pow(factor5.getFactor(), 1.02)))*(1-factor8.getFactor()/(4*factor6.getFactor()))*factor7.getFactor()*factor6.getFactor()*24*stat.getKsPitch()/1000;
				stat1.setStattype3("快速路");
				stat1.setStatvalue(res/Math.pow(10, 6));
				stat1.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat1.setStat_valtype("计算道路扬尘");
				stat1.setStat_factor(factor3.getFactor()+""+factor4.getFactor()+""+factor5.getFactor()+""+factor6.getFactor()+""+factor7.getFactor()+""+factor8.getFactor());
				stat1.setStat_value(stat.getKsPitch()+"");
				stat1.setStat_dsrate(0);
				resList.add(stat1);
			}
			
			if(factor11.getFactor()>0&&factor12.getFactor()>0&&factor13.getFactor()>0&&factor14.getFactor()>0&&factor15.getFactor()>0&&factor16.getFactor()>0){
				GovStat stat2=new GovStat();
				stat2.setCity(stat.getCity());
				stat2.setFillyear(stat.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(stat.getProvince());
				stat2.setTown(stat.getTown());
				stat2.setStattype("roaddust");
				double res2=(factor11.getFactor()*(Math.pow(factor12.getFactor(), 0.91))*(Math.pow(factor13.getFactor(), 1.02)))*(1-factor16.getFactor()/(4*factor14.getFactor()))*factor15.getFactor()*factor14.getFactor()*24*stat.getKsPitch()/1000;
				stat2.setStattype3("快速路");
				stat2.setStatvalue(res2/Math.pow(10, 6));
				stat2.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat2.setStat_valtype("计算道路扬尘");
				stat2.setStat_factor(factor11.getFactor()+""+factor12.getFactor()+""+factor13.getFactor()+""+factor14.getFactor()+""+factor15.getFactor()+""+factor16.getFactor());
				stat2.setStat_value(stat.getKsPitch()+"");
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
			//主干道
			if(factor3.getFactor()>0&&factor4.getFactor()>0&&factor5.getFactor()>0&&factor6.getFactor()>0&&factor7.getFactor()>0&&factor8.getFactor()>0){
				GovStat stat1=new GovStat();
				stat1.setCity(stat.getCity());
				stat1.setFillyear(stat.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(stat.getProvince());
				stat1.setTown(stat.getTown());
				stat1.setStattype("roaddust");
				double res=(factor3.getFactor()*(Math.pow(factor4.getFactor(), 0.91))*(Math.pow(factor5.getFactor(), 1.02)))*(1-factor8.getFactor()/(4*factor6.getFactor()))*factor7.getFactor()*factor6.getFactor()*24*stat.getZgPitch()/1000;
				stat1.setStattype3("主干道");
				stat1.setStatvalue(res/Math.pow(10, 6));
				stat1.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat1.setStat_valtype("计算道路扬尘");
				stat1.setStat_factor(factor3.getFactor()+""+factor4.getFactor()+""+factor5.getFactor()+""+factor6.getFactor()+""+factor7.getFactor()+""+factor8.getFactor());
				stat1.setStat_value(stat.getZgPitch()+"");
				stat1.setStat_dsrate(0);
				resList.add(stat1);
			}
			if(factor11.getFactor()>0&&factor12.getFactor()>0&&factor13.getFactor()>0&&factor14.getFactor()>0&&factor15.getFactor()>0&&factor16.getFactor()>0){
				GovStat stat2=new GovStat();
				stat2.setCity(stat.getCity());
				stat2.setFillyear(stat.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(stat.getProvince());
				stat2.setTown(stat.getTown());
				stat2.setStattype("roaddust");
				double res2=(factor11.getFactor()*(Math.pow(factor12.getFactor(), 0.91))*(Math.pow(factor13.getFactor(), 1.02)))*(1-factor16.getFactor()/(4*factor14.getFactor()))*factor15.getFactor()*factor14.getFactor()*24*stat.getZgPitch()/1000;
				stat2.setStattype3("主干道");
				stat2.setStatvalue(res2/Math.pow(10, 6));
				stat2.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat2.setStat_valtype("计算道路扬尘");
				stat2.setStat_factor(factor11.getFactor()+""+factor12.getFactor()+""+factor13.getFactor()+""+factor14.getFactor()+""+factor15.getFactor()+""+factor16.getFactor());
				stat2.setStat_value(stat.getZgPitch()+"");
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
			//次干道
			if(factor3.getFactor()>0&&factor4.getFactor()>0&&factor5.getFactor()>0&&factor6.getFactor()>0&&factor7.getFactor()>0&&factor8.getFactor()>0){
				GovStat stat1=new GovStat();
				stat1.setCity(stat.getCity());
				stat1.setFillyear(stat.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(stat.getProvince());
				stat1.setTown(stat.getTown());
				stat1.setStattype("roaddust");
				double res=(factor3.getFactor()*(Math.pow(factor4.getFactor(), 0.91))*(Math.pow(factor5.getFactor(), 1.02)))*(1-factor8.getFactor()/(4*factor6.getFactor()))*factor7.getFactor()*factor6.getFactor()*24*stat.getCgPitch()/1000;
				stat1.setStattype3("次干道");
				stat1.setStatvalue(res/Math.pow(10, 6));
				stat1.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat1.setStat_valtype("计算道路扬尘");
				stat1.setStat_factor(factor3.getFactor()+""+factor4.getFactor()+""+factor5.getFactor()+""+factor6.getFactor()+""+factor7.getFactor()+""+factor8.getFactor());
				stat1.setStat_value(stat.getCgPitch()+"");
				stat1.setStat_dsrate(0);
				resList.add(stat1);
			}
			if(factor11.getFactor()>0&&factor12.getFactor()>0&&factor13.getFactor()>0&&factor14.getFactor()>0&&factor15.getFactor()>0&&factor16.getFactor()>0){
				GovStat stat2=new GovStat();
				stat2.setCity(stat.getCity());
				stat2.setFillyear(stat.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(stat.getProvince());
				stat2.setTown(stat.getTown());
				stat2.setStattype("roaddust");
				double res2=(factor11.getFactor()*(Math.pow(factor12.getFactor(), 0.91))*(Math.pow(factor13.getFactor(), 1.02)))*(1-factor16.getFactor()/(4*factor14.getFactor()))*factor15.getFactor()*factor14.getFactor()*24*stat.getCgPitch()/1000;
				stat2.setStattype3("次干道");
				stat2.setStatvalue(res2/Math.pow(10, 6));
				stat2.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat2.setStat_valtype("计算道路扬尘");
				stat2.setStat_factor(factor11.getFactor()+""+factor12.getFactor()+""+factor13.getFactor()+""+factor14.getFactor()+""+factor15.getFactor()+""+factor16.getFactor());
				stat2.setStat_value(stat.getCgPitch()+"");
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
			
			//支路
			if(factor3.getFactor()>0&&factor4.getFactor()>0&&factor5.getFactor()>0&&factor6.getFactor()>0&&factor7.getFactor()>0&&factor8.getFactor()>0){
				GovStat stat1=new GovStat();
				stat1.setCity(stat.getCity());
				stat1.setFillyear(stat.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(stat.getProvince());
				stat1.setTown(stat.getTown());
				stat1.setStattype("roaddust");
				double res=(factor3.getFactor()*(Math.pow(factor4.getFactor(), 0.91))*(Math.pow(factor5.getFactor(), 1.02)))*(1-factor8.getFactor()/(4*factor6.getFactor()))*factor7.getFactor()*factor6.getFactor()*24*stat.getzPitch()/1000;
				stat1.setStattype3("支路");
				stat1.setStatvalue(res/Math.pow(10, 6));
				stat1.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat1.setStat_valtype("计算道路扬尘");
				stat1.setStat_factor(factor3.getFactor()+""+factor4.getFactor()+""+factor5.getFactor()+""+factor6.getFactor()+""+factor7.getFactor()+""+factor8.getFactor());
				stat1.setStat_value(stat.getzPitch()+"");
				stat1.setStat_dsrate(0);
				resList.add(stat1);
			}
			if(factor11.getFactor()>0&&factor12.getFactor()>0&&factor13.getFactor()>0&&factor14.getFactor()>0&&factor15.getFactor()>0&&factor16.getFactor()>0){
				GovStat stat2=new GovStat();
				stat2.setCity(stat.getCity());
				stat2.setFillyear(stat.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(stat.getProvince());
				stat2.setTown(stat.getTown());
				stat2.setStattype("roaddust");
				double res2=(factor11.getFactor()*(Math.pow(factor12.getFactor(), 0.91))*(Math.pow(factor13.getFactor(), 1.02)))*(1-factor16.getFactor()/(4*factor14.getFactor()))*factor15.getFactor()*factor14.getFactor()*24*stat.getzPitch()/1000;
				stat2.setStattype3("支路");
				stat2.setStatvalue(res2/Math.pow(10, 6));
				stat2.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat2.setStat_valtype("计算道路扬尘");
				stat2.setStat_factor(factor11.getFactor()+""+factor12.getFactor()+""+factor13.getFactor()+""+factor14.getFactor()+""+factor15.getFactor()+""+factor16.getFactor());
				stat2.setStat_value(stat.getzPitch()+"");
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
			//未铺设道路
			if(factor3.getFactor()>0&&factor4.getFactor()>0&&factor5.getFactor()>0&&factor6.getFactor()>0&&factor7.getFactor()>0&&factor8.getFactor()>0){
				GovStat stat1=new GovStat();
				stat1.setCity(stat.getCity());
				stat1.setFillyear(stat.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(stat.getProvince());
				stat1.setTown(stat.getTown());
				stat1.setStattype("roaddust");
				double res=(factor3.getFactor()*(Math.pow(factor4.getFactor(), 0.91))*(Math.pow(factor5.getFactor(), 1.02)))*(1-factor8.getFactor()/(4*factor6.getFactor()))*factor7.getFactor()*factor6.getFactor()*24*stat.getKsNotShop()/1000;
				stat1.setStattype3("未铺设道路");
				stat1.setStatvalue(res/Math.pow(10, 6));
				stat1.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat1.setStat_valtype("计算道路扬尘");
				stat1.setStat_factor(factor3.getFactor()+""+factor4.getFactor()+""+factor5.getFactor()+""+factor6.getFactor()+""+factor7.getFactor()+""+factor8.getFactor());
				stat1.setStat_value(stat.getKsNotShop()+"");
				stat1.setStat_dsrate(0);
				resList.add(stat1);
			}
			if(factor11.getFactor()>0&&factor12.getFactor()>0&&factor13.getFactor()>0&&factor14.getFactor()>0&&factor15.getFactor()>0&&factor16.getFactor()>0){
				GovStat stat2=new GovStat();
				stat2.setCity(stat.getCity());
				stat2.setFillyear(stat.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(stat.getProvince());
				stat2.setTown(stat.getTown());
				stat2.setStattype("roaddust");
				double res2=(factor11.getFactor()*(Math.pow(factor12.getFactor(), 0.91))*(Math.pow(factor13.getFactor(), 1.02)))*(1-factor16.getFactor()/(4*factor14.getFactor()))*factor15.getFactor()*factor14.getFactor()*24*stat.getKsNotShop()/1000;
				stat2.setStattype3("未铺设道路");
				stat2.setStatvalue(res2/Math.pow(10, 6));
				stat2.setStat_exp("E=[k(sL)^0.91*w^1.02](1-P/4N)*Q*N*24*L/1000");
				stat2.setStat_valtype("计算道路扬尘");
				stat2.setStat_factor(factor11.getFactor()+""+factor12.getFactor()+""+factor13.getFactor()+""+factor14.getFactor()+""+factor15.getFactor()+""+factor16.getFactor());
				stat2.setStat_value(stat.getKsNotShop()+"");
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
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
