package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.entity.gov.MotorStat;
import com.xf.entity.gov.MotorVehicleDb;
import com.xf.entity.gov.VehicleFactor;
import com.xf.security.LocalYear;
import com.xf.service.gov.MotorVehicleService;
import com.xf.service.stat.MotorStandartService;
import com.xf.vo.MotorCount;

public class ComputeMotor implements Runnable{
	private static int flag = 0;
	private static String msg;
	private static ComputeMotor computeMotor;
	private static MotorStandartService motorService;
	private static MotorVehicleService mvService;
	private static int fillyear;
	private static int typeid;
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int f) {
		flag = f;
	}

	public static String getMsg() {
		return msg;
	}
	

	public void run() {
		try {
			LocalYear curyear = MyDispatcherServlet.getYear();
			curyear.set(fillyear);
			flag = 10;
			if(typeid==1){
				motorService.deleteAll2(fillyear);
			}else if(typeid==2){
				motorService.deleteAll(fillyear);
			}
			flag = 20;
			List<MotorCount> countlist=motorService.getMotorCount(fillyear);
			flag = 30;
			List<VehicleFactor> factorlist=motorService.getMotorFactor();
			List<MotorVehicleDb> mvlist=mvService.getByYear(fillyear);
			if(typeid==1){
				mvlist=mvService.getByYear2(fillyear);
			}else if(typeid==2){
				mvlist=mvService.getByYear(fillyear);
			}
			flag = 40;
			List<MotorStat> reslist=new ArrayList<MotorStat>();
			for(MotorCount mc:countlist){
				for(VehicleFactor vf:factorlist){
					if(mc.getStandard()==vf.getStandardId()&&mc.getVehiclemodel()==vf.getVehiclemodel()){
						if(vf.getPollutantId()==15){
							int count=0;
							for(MotorVehicleDb m:mvlist){
								if(mc.getCity()==m.getCity()&&mc.getStandard()==m.getStandard()&&mc.getVehiclemodel()==m.getVehiclemodel()){
									count=m.getHoldings();
									break;
								}
							}
							double value = (vf.getFactor()*mc.getAvgmile()*10000/30)*count*Math.pow(10, -6);
							MotorStat ms = new MotorStat();
							ms.setCity(mc.getCity());
							ms.setFillyear(mc.getFillyear());
							ms.setPollutantId(vf.getPollutantId());
							ms.setProvince(mc.getProvince());
							ms.setStandard(mc.getStandard());
							ms.setTown(mc.getTown());
							ms.setVehiclemodel(mc.getVehiclemodel());
							ms.setStatvalue(value);
							ms.setStat_exp("(行驶过程蒸发排放因子 X S(年行驶里程km) / 30) X C(机动车数量) X 10的-6次方");
							ms.setStat_valtype("计算机动车尾气污染物的排放量");
							ms.setStat_factor(vf.getFactor() + "");
							ms.setStat_value("( "+vf.getFactor() + " X "
									+ mc.getAvgmile()+" X " +10000+" / 30 )"+ " X " + count
									+ " X " + Math.pow(10, -6));
							ms.setStat_dsrate(0);
							reslist.add(ms);
						}else if(vf.getPollutantId()==16){
							int count=0;
							for(MotorVehicleDb m:mvlist){
								if(mc.getCity()==m.getCity()&&mc.getStandard()==m.getStandard()&&mc.getVehiclemodel()==m.getVehiclemodel()){
									count=m.getHoldings();
									break;
								}
							}
							double value = (vf.getFactor()*365)*count*Math.pow(10, -6);
							MotorStat ms = new MotorStat();
							ms.setCity(mc.getCity());
							ms.setFillyear(mc.getFillyear());
							ms.setPollutantId(vf.getPollutantId());
							ms.setProvince(mc.getProvince());
							ms.setStandard(mc.getStandard());
							ms.setTown(mc.getTown());
							ms.setVehiclemodel(mc.getVehiclemodel());
							ms.setStatvalue(value);
							ms.setStat_exp("(驻车过程蒸发排放因子 X 365) X C(机动车数量) X 10的-6次方");
							ms.setStat_valtype("计算机动车尾气污染物的排放量");
							ms.setStat_factor(vf.getFactor() + "");
							ms.setStat_value("( "+vf.getFactor() + " X " + " 365 )"+ " X " + count + " X " + Math.pow(10, -6));
							ms.setStat_dsrate(0);
							reslist.add(ms);
						}else{
							int count=0;
							for(MotorVehicleDb m:mvlist){
								if(mc.getCity()==m.getCity()&&mc.getStandard()==m.getStandard()&&mc.getVehiclemodel()==m.getVehiclemodel()){
									count=m.getHoldings();
									break;
								}
							}
							double value = count * mc.getAvgmile()
									* vf.getFactor() * Math.pow(10, -2);
							MotorStat ms = new MotorStat();
							ms.setCity(mc.getCity());
							ms.setFillyear(mc.getFillyear());
							ms.setPollutantId(vf.getPollutantId());
							ms.setProvince(mc.getProvince());
							ms.setStandard(mc.getStandard());
							ms.setTown(mc.getTown());
							ms.setVehiclemodel(mc.getVehiclemodel());
							ms.setStatvalue(value);
							ms.setStat_exp("EF(排放因子) X C(机动车数量) X S(年行驶里程) X 10的-2次方");
							ms.setStat_valtype("计算机动车尾气污染物的排放量");
							ms.setStat_factor(vf.getFactor() + "");
							ms.setStat_value(vf.getFactor() + " X "
									+ count + " X " + mc.getAvgmile()
									+ " X " + Math.pow(10, -2));
							ms.setStat_dsrate(0);
							reslist.add(ms);
						}
					}
				}
			}
			//vocs加总
			List<MotorStat> reslist2=new ArrayList<MotorStat>();
			Set<String> set=new HashSet<String>();
			for(MotorStat m:reslist){
				set.add(m.getCity()+" "+m.getStandard()+" "+m.getVehiclemodel());
			}
			for(String s:set){
				MotorStat ms = new MotorStat();
				String exp="";
				String stat_value="";
				String factor="";
				double value=0;
				for(MotorStat m:reslist){
					if((m.getCity()+" "+m.getStandard()+" "+m.getVehiclemodel()).equals(s)&&(m.getPollutantId()==9||m.getPollutantId()==15||m.getPollutantId()==16)){
						ms.setCity(m.getCity());
						ms.setFillyear(m.getFillyear());
						ms.setPollutantId(9);
						ms.setProvince(m.getProvince());
						ms.setStandard(m.getStandard());
						ms.setTown(m.getTown());
						ms.setVehiclemodel(m.getVehiclemodel());
						ms.setStat_valtype(m.getStat_valtype());
						ms.setStat_dsrate(0);
						exp=exp+" + "+m.getStat_exp();
						factor=factor+" "+m.getStat_factor();
						stat_value=stat_value+" + "+m.getStat_value();
						value=value+m.getStatvalue();
					}else if((m.getCity()+" "+m.getStandard()+" "+m.getVehiclemodel()).equals(s)&&m.getPollutantId()!=9&&m.getPollutantId()!=15&&m.getPollutantId()!=16){
						reslist2.add(m);
					}
				}
				ms.setStatvalue(value);
				ms.setStat_exp(exp);
				ms.setStat_factor(factor);
				ms.setStat_value(stat_value);
				reslist2.add(ms);
			}
			flag = 70;
			if(reslist2.size()>0){
				int count=0;
				List<MotorStat> newlist=new ArrayList<MotorStat>();
				for(MotorStat sp:reslist2) {
					if(count > 0 && count % 500 == 0) {
						if(typeid==1){
							motorService.addMotorres2(newlist);
						}else if(typeid==2){
							motorService.addMotorres(newlist);
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
						motorService.addMotorres2(newlist);
					}else if(typeid==2){
						motorService.addMotorres(newlist);
					}
					
				}
			}
			flag = 100;
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			flag = -1;
		}
	}

	public static ComputeMotor getInstance(MotorStandartService motorService1,MotorVehicleService mvService1,int fillyear1,int typeid1) {
		if (computeMotor == null) {
			synchronized (ComputeMotor.class) {
				if (computeMotor == null) {
					computeMotor = new ComputeMotor();
					motorService = motorService1;
					mvService=mvService1;
					fillyear=fillyear1;
					typeid=typeid1;
				}
			}
		}
		else {
			if (flag >= 100 || flag <= 0) {
				motorService = motorService1;
				mvService=mvService1;
				fillyear=fillyear1;
				typeid=typeid1;
			}
		}
		return computeMotor;
	}
}
