package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.HouseholdFuel;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeHouseholdFuel {

	public void compute(ComputeService computeService,
			GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeHouseholdFuel");
		List<HouseholdFuel> list = null;
		if(typeid==1){
			list = computeService.getAllHouseholdFuel2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllHouseholdFuel(fillyear);
		}
		List<GovFactor> factorList = govFactorService.getByTypeName("dfuel");
		GovFactor factor1 = null;
		GovFactor factor2 = null;
		GovFactor factor3 = null;
		GovFactor factor4 = null;
		GovFactor factor5 = null;
		GovFactor factor6 = null;
		GovFactor factor7 = null;
		GovFactor factor8 = null;
		GovFactor factor9 = null;
		GovFactor factor10 = null;
		GovFactor factor11 = null;
		GovFactor factor12 = null;
		for(GovFactor g:factorList){
			if((g.getType_x()+g.getType_y()).equals("SO2煤炭")){
				factor1=g;
			}else if((g.getType_x()+g.getType_y()).equals("NOx煤炭")){
				factor2=g;
			}else if((g.getType_x()+g.getType_y()).equals("VOCs煤炭")){
				factor3=g;
			}else if((g.getType_x()+g.getType_y()).equals("CO煤炭")){
				factor4=g;
			}else if((g.getType_x()+g.getType_y()).equals("PM2.5煤炭")){
				factor5=g;
			}else if((g.getType_x()+g.getType_y()).equals("PM10煤炭")){
				factor6=g;
			}else if((g.getType_x()+g.getType_y()).equals("SO2天然气")){
				factor7=g;
			}else if((g.getType_x()+g.getType_y()).equals("NOx天然气")){
				factor8=g;
			}else if((g.getType_x()+g.getType_y()).equals("VOCs天然气")){
				factor9=g;
			}else if((g.getType_x()+g.getType_y()).equals("CO天然气")){
				factor10=g;
			}else if((g.getType_x()+g.getType_y()).equals("PM2.5天然气")){
				factor11=g;
			}else if((g.getType_x()+g.getType_y()).equals("PM10天然气")){
				factor12=g;
			}
		}
		

		List<GovStat> resList = new ArrayList<GovStat>();
		for (HouseholdFuel fuel : list) {
			double so2 = 0;
			double nox = 0;
			double vocs = 0;
			double co = 0;
			double pm2 = 0;
			double pm10 = 0;
			double gso2 = 0;
			double gnox = 0;
			double gvocs = 0;
			double gco = 0;
			double gpm2 = 0;
			double gpm10 = 0;
			
			//燃气
			gso2 = fuel.getLifeNatgas() * factor7.getFactor() / 100;
			GovStat govStat7 = new GovStat();
			govStat7.setCity(fuel.getCity());
			govStat7.setFillyear(fuel.getFillyear());
			govStat7.setPollutantId(1);
			govStat7.setProvince(fuel.getProvince());
			govStat7.setStattype("dfuel");
			govStat7.setStatvalue(gso2);
			govStat7.setTown(fuel.getTown());
			govStat7.setStattype2(21003);
			govStat7.setStat_exp("E=天然气消费量*EF/100");
			govStat7.setStat_valtype("计算民用天然气的排放量");
			govStat7.setStat_factor(factor7.getFactor()+"");
			govStat7.setStat_value(fuel.getLifeNatgas() +" X "+ factor7.getFactor() +" / "+ 100);
			govStat7.setStat_dsrate(0);
			resList.add(govStat7);
			
			gnox = fuel.getLifeNatgas() * factor8.getFactor() / 100;
			GovStat govStat8 = new GovStat();
			govStat8.setCity(fuel.getCity());
			govStat8.setFillyear(fuel.getFillyear());
			govStat8.setPollutantId(2);
			govStat8.setProvince(fuel.getProvince());
			govStat8.setStattype("dfuel");
			govStat8.setStatvalue(gnox);
			govStat8.setTown(fuel.getTown());
			govStat8.setStattype2(21003);
			govStat8.setStat_exp("E=天然气消费量*EF/100");
			govStat8.setStat_valtype("计算民用天然气的排放量");
			govStat8.setStat_factor(factor8.getFactor()+"");
			govStat8.setStat_value(fuel.getLifeNatgas() +" X "+ factor8.getFactor() +" / "+ 100);
			govStat8.setStat_dsrate(0);
			resList.add(govStat8);
			
			gvocs = fuel.getLifeNatgas() * factor9.getFactor() / 100;
			GovStat govStat9 = new GovStat();
			govStat9.setCity(fuel.getCity());
			govStat9.setFillyear(fuel.getFillyear());
			govStat9.setPollutantId(9);
			govStat9.setProvince(fuel.getProvince());
			govStat9.setStattype("dfuel");
			govStat9.setStatvalue(gvocs);
			govStat9.setTown(fuel.getTown());
			govStat9.setStattype2(21003);
			govStat9.setStat_exp("E=天然气消费量*EF/100");
			govStat9.setStat_valtype("计算民用天然气的排放量");
			govStat9.setStat_factor(factor9.getFactor()+"");
			govStat9.setStat_value(fuel.getLifeNatgas() +" X "+ factor9.getFactor() +" / "+ 100);
			govStat9.setStat_dsrate(0);
			resList.add(govStat9);
			
			gco = fuel.getLifeNatgas() * factor10.getFactor() / 100;
			GovStat govStat10 = new GovStat();
			govStat10.setCity(fuel.getCity());
			govStat10.setFillyear(fuel.getFillyear());
			govStat10.setPollutantId(3);
			govStat10.setProvince(fuel.getProvince());
			govStat10.setStattype("dfuel");
			govStat10.setStatvalue(gco);
			govStat10.setTown(fuel.getTown());
			govStat10.setStattype2(21003);
			govStat10.setStat_exp("E=天然气消费量*EF/100");
			govStat10.setStat_valtype("计算民用天然气的排放量");
			govStat10.setStat_factor(factor10.getFactor()+"");
			govStat10.setStat_value(fuel.getLifeNatgas() +" X "+ factor10.getFactor() +" / "+ 100);
			govStat10.setStat_dsrate(0);
			resList.add(govStat10);
			
			gpm2 = fuel.getLifeNatgas() * factor11.getFactor() / 100;
			GovStat govStat11 = new GovStat();
			govStat11.setCity(fuel.getCity());
			govStat11.setFillyear(fuel.getFillyear());
			govStat11.setPollutantId(6);
			govStat11.setProvince(fuel.getProvince());
			govStat11.setStattype("dfuel");
			govStat11.setStatvalue(gpm2);
			govStat11.setTown(fuel.getTown());
			govStat11.setStattype2(21003);
			govStat11.setStat_exp("E=天然气消费量*EF/100");
			govStat11.setStat_valtype("计算民用天然气的排放量");
			govStat11.setStat_factor(factor11.getFactor()+"");
			govStat11.setStat_value(fuel.getLifeNatgas() +" X "+ factor11.getFactor() +" / "+ 100);
			govStat11.setStat_dsrate(0);
			resList.add(govStat11);
			
			gpm10 = fuel.getLifeNatgas() * factor12.getFactor() / 100;
			GovStat govStat12 = new GovStat();
			govStat12.setCity(fuel.getCity());
			govStat12.setFillyear(fuel.getFillyear());
			govStat12.setPollutantId(4);
			govStat12.setProvince(fuel.getProvince());
			govStat12.setStattype("dfuel");
			govStat12.setStatvalue(gpm10);
			govStat12.setTown(fuel.getTown());
			govStat12.setStattype2(21003);
			govStat12.setStat_exp("E=天然气消费量*EF/100");
			govStat12.setStat_valtype("计算民用天然气的排放量");
			govStat12.setStat_factor(factor12.getFactor()+"");
			govStat12.setStat_value(fuel.getLifeNatgas() +" X "+ factor12.getFactor() +" / "+ 100);
			govStat12.setStat_dsrate(0);
			resList.add(govStat12);
			
			//燃煤
			so2 = fuel.getLifeCoal() * fuel.getLifeCoalSulphur() * 16 *10;
			GovStat govStat1 = new GovStat();
			govStat1.setCity(fuel.getCity());
			govStat1.setFillyear(fuel.getFillyear());
			govStat1.setPollutantId(1);
			govStat1.setProvince(fuel.getProvince());
			govStat1.setStattype("dfuel");
			govStat1.setStatvalue(so2);
			govStat1.setTown(fuel.getTown());
			govStat1.setStattype2(21001);
			govStat1.setStat_exp("E=煤炭消费量(万吨)*含硫量(%)*16*10");
			govStat1.setStat_valtype("计算民用燃料燃煤的SO2排放量(无因子)");
			govStat1.setStat_factor("无因子");
			govStat1.setStat_value(fuel.getLifeCoal() +" X ("+ fuel.getLifeCoalSulphur()+") X "+ 16 +" X "+ 10);
			govStat1.setStat_dsrate(0);
			resList.add(govStat1);
			
			nox = fuel.getLifeCoal() * factor2.getFactor() * 10;
			GovStat govStat2 = new GovStat();
			govStat2.setCity(fuel.getCity());
			govStat2.setFillyear(fuel.getFillyear());
			govStat2.setPollutantId(2);
			govStat2.setProvince(fuel.getProvince());
			govStat2.setStattype("dfuel");
			govStat2.setStatvalue(nox);
			govStat2.setTown(fuel.getTown());
			govStat2.setStattype2(21001);
			govStat2.setStat_exp("E=煤炭消费量*EF*10");
			govStat2.setStat_valtype("计算民用燃料煤炭的排放量");
			govStat2.setStat_factor(factor2.getFactor()+"");
			govStat2.setStat_value(fuel.getLifeCoal() +" X "+ factor2.getFactor() +" X "+ 10);
			govStat2.setStat_dsrate(0);
			resList.add(govStat2);
			
			vocs = fuel.getLifeCoal() * factor3.getFactor() * 10;
			GovStat govStat3 = new GovStat();
			govStat3.setCity(fuel.getCity());
			govStat3.setFillyear(fuel.getFillyear());
			govStat3.setPollutantId(9);
			govStat3.setProvince(fuel.getProvince());
			govStat3.setStattype("dfuel");
			govStat3.setStatvalue(vocs);
			govStat3.setTown(fuel.getTown());
			govStat3.setStattype2(21001);
			govStat3.setStat_exp("E=煤炭消费量*EF*10");
			govStat3.setStat_valtype("计算民用燃料煤炭的排放量");
			govStat3.setStat_factor(factor3.getFactor()+"");
			govStat3.setStat_value(fuel.getLifeCoal() +" X "+ factor3.getFactor() +" X "+ 10);
			govStat3.setStat_dsrate(0);
			resList.add(govStat3);
			
			co = fuel.getLifeCoal() * factor4.getFactor() * 10;
			GovStat govStat4 = new GovStat();
			govStat4.setCity(fuel.getCity());
			govStat4.setFillyear(fuel.getFillyear());
			govStat4.setPollutantId(3);
			govStat4.setProvince(fuel.getProvince());
			govStat4.setStattype("dfuel");
			govStat4.setStatvalue(co);
			govStat4.setTown(fuel.getTown());
			govStat4.setStattype2(21001);
			govStat4.setStat_exp("E=煤炭消费量*EF*10");
			govStat4.setStat_valtype("计算民用燃料煤炭的排放量");
			govStat4.setStat_factor(factor4.getFactor()+"");
			govStat4.setStat_value(fuel.getLifeCoal() +" X "+ factor4.getFactor() +" X "+ 10);
			govStat4.setStat_dsrate(0);
			resList.add(govStat4);
			
			pm2 = fuel.getLifeCoal() * factor5.getFactor() * 10;
			GovStat govStat5 = new GovStat();
			govStat5.setCity(fuel.getCity());
			govStat5.setFillyear(fuel.getFillyear());
			govStat5.setPollutantId(6);
			govStat5.setProvince(fuel.getProvince());
			govStat5.setStattype("dfuel");
			govStat5.setStatvalue(pm2);
			govStat5.setTown(fuel.getTown());
			govStat5.setStattype2(21001);
			govStat5.setStat_exp("E=煤炭消费量*EF*10");
			govStat5.setStat_valtype("计算民用燃料煤炭的排放量");
			govStat5.setStat_factor(factor5.getFactor()+"");
			govStat5.setStat_value(fuel.getLifeCoal() +" X "+ factor5.getFactor() +" X "+ 10);
			govStat5.setStat_dsrate(0);
			resList.add(govStat5);
			
			pm10 = fuel.getLifeCoal() * factor6.getFactor() * 10;
			GovStat govStat6 = new GovStat();
			govStat6.setCity(fuel.getCity());
			govStat6.setFillyear(fuel.getFillyear());
			govStat6.setPollutantId(4);
			govStat6.setProvince(fuel.getProvince());
			govStat6.setStattype("dfuel");
			govStat6.setStatvalue(pm10);
			govStat6.setTown(fuel.getTown());
			govStat6.setStattype2(21001);
			govStat6.setStat_exp("E=煤炭消费量*EF*10");
			govStat6.setStat_valtype("计算民用燃料煤炭的排放量");
			govStat6.setStat_factor(factor6.getFactor()+"");
			govStat6.setStat_value(fuel.getLifeCoal() +" X "+ factor6.getFactor() +" X "+ 10);
			govStat6.setStat_dsrate(0);
			resList.add(govStat6);
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
