package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.EquipmentFarm;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeEquipmentfarm {

	private void setValue(List<GovStat> resList,GovFactor gf,EquipmentFarm ef,int pollutantid){
		GovStat stat1 = new GovStat();
		stat1.setCity(ef.getCity());
		stat1.setFillyear(ef.getFillyear());
		stat1.setPollutantId(pollutantid);
		stat1.setProvince(ef.getProvince());
		stat1.setStattype("agriculturemachine");
		stat1.setTown(ef.getTown());
		stat1.setStatvalue(ef.getFuelconsume() * gf.getFactor() * Math.pow(10, -3));
		stat1.setStattype3(ef.getFarmtype());
		stat1.setStat_exp("E=Q*EF*0.001");
		stat1.setStat_valtype("计算农业机械的排放量("+gf.getType_y()+")");
		stat1.setStat_factor(gf.getFactor()+"");
		stat1.setStat_value(ef.getFuelconsume() +" X "+ gf.getFactor() +" X "+ Math.pow(10, -3));
		stat1.setStat_dsrate(0);
		resList.add(stat1);
	}
	
	public void compute(ComputeService computeService,
			GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeEquipmentfarm");
		List<EquipmentFarm> list = null;
        if(typeid==1){
        	list = computeService.getAllEquipmentfarm2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllEquipmentfarm(fillyear);
		}
		List<GovFactor> factorList = govFactorService.getByTypeName("agriculturemachine");
		
		List<GovStat> resList = new ArrayList<GovStat>();
		for(GovFactor gf:factorList){
			if(gf.getFactor()>0){
				for(EquipmentFarm ef:list){
					//机耕
					if("PM".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田作业-机耕".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机耕".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,103);
					}
					//机播
					else if("PM".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田作业-机播".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机播".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,103);
					}
					//机收
					else if("PM".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田作业-机收".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"机收".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,103);
					}
					//植保
					else if("PM".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田作业-植保".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&"植保".equals(ef.getFarmtype2())){
						setValue(resList,gf,ef,103);
					}
					//其它
					else if("PM".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田作业-其它".equals(gf.getType_y())&&"农田作业".equals(ef.getFarmtype())&&(ef.getFarmtype2()==null||ef.getFarmtype2().equals(""))){
						setValue(resList,gf,ef,103);
					}
					//农田排灌
					else if("PM".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田排灌".equals(gf.getType_y())&&"农田排灌".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
					//农田基本建设
					else if("PM".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农田基本建设".equals(gf.getType_y())&&"农田基本建设".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
					//畜牧业生产
					else if("PM".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"畜牧业生产".equals(gf.getType_y())&&"畜牧业生产".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
					//农产品初加工
					else if("PM".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农产品初加工".equals(gf.getType_y())&&"农产品初加工".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
					//农业运输
					else if("PM".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"农业运输".equals(gf.getType_y())&&"农业运输".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
					//其它
					else if("PM".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,4);
						setValue(resList,gf,ef,6);
					}else if("SO2".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,1);
					}else if("NO".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,101);
					}else if("NO2".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,102);
					}else if("THC".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,9);
					}else if("CO".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,3);
					}else if("CO2".equals(gf.getType_x())&&"其它".equals(gf.getType_y())&&!"农业运输".equals(ef.getFarmtype())&&!"农产品初加工".equals(ef.getFarmtype())&&!"畜牧业生产".equals(ef.getFarmtype())&&!"农田基本建设".equals(ef.getFarmtype())&&!"农田排灌".equals(ef.getFarmtype())&&!"农田作业".equals(ef.getFarmtype())){
						setValue(resList,gf,ef,103);
					}
				}
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
