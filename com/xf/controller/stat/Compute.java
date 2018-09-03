package com.xf.controller.stat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.entity.Pollutant;
import com.xf.security.LocalYear;
import com.xf.service.PollutantService;
import com.xf.service.stat.ComputeService;
import com.xf.vo.Fuelres;
import com.xf.vo.ProdFuelstat;
import com.xf.vo.Prodres;
import com.xf.vo.StatDevice;
import com.xf.vo.StatProd;
public class Compute implements Runnable {
	private static int flag = 0;
	private static Compute compute;
	private static String msg;
	public static ComputeService computeService;
	public static PollutantService pollutantService;
	public static int fillyear;
	Logger logger=Logger.getRootLogger();

	public int getFlag() {
		return flag;
	}
	
	public void setFlag(int f) {
		flag = f;
	}

	public static String getMsg() {
		return msg;
	}
	
	private int getGroupid(List<Pollutant> polist,int pollutantid){
		for(Pollutant p:polist){
			if(p.getId()==pollutantid){
				return p.getGroupid();
			}
		}
		return pollutantid;
	}
	
	private void invode(List<StatDevice> statlist, Fuelres f) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = f.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(f));
				if (valMx > 0) {
					StatDevice stat = new StatDevice();
					stat.setFillyear(f.getFillyear());
					stat.setPollutantId(1);
					stat.setDeviceid(f.getDeviceid());
					stat.setCompanyid(f.getEnterpriceId());
					stat.setDevclass(f.getDevClass());
					stat.setFuelgroupid(f.getPid());
					if (f.getPid() == 2001) {
						double so2 = valMx * f.getScontent() * 17 * (1 - f.getDsrate()) / 1000;
						stat.setFuelgroupname("燃煤");
						stat.setStat_exp("燃煤消耗量（吨）X平均含硫量（%）X 17 X（1-脱硫效率）/1000");
						stat.setStat_valtype("计算锅炉燃煤的SO2排放量(无因子)");
						stat.setStat_factor("无因子");
						stat.setStat_value(valMx +" X "+ f.getScontent() +" X "+ 17 +" X ("+ 1 +" - "+ f.getDsrate()+") /"+ 1000);
						stat.setStatvalue(so2);
					}
					if (f.getPid() == 2002) {
						stat.setFuelgroupname("燃油");
						double so2 = valMx * f.getScontent() * 20 * (1 - f.getDsrate()) / 1000;
						stat.setFuelgroupname("燃煤");
						stat.setStat_exp("燃油消耗量（吨）X平均含硫量（%）X 20 X（1-脱硫效率）/1000");
						stat.setStat_valtype("计算锅炉燃煤的SO2排放量(无因子)");
						stat.setStat_factor("无因子");
						stat.setStat_value(valMx +" X "+ f.getScontent() +" X "+ 20 +" X ("+ 1 +" - "+ f.getDsrate()+") /"+ 1000);
						stat.setStatvalue(so2);
					}
					if (f.getPid() == 2003) {
						stat.setFuelgroupname("燃气");
					}
					if (f.getPid() == 2004) {
						stat.setFuelgroupname("生物燃料");
					}
					if (f.getPid() == 2005) {
						stat.setFuelgroupname("生活垃圾");
					}
					if (f.getPid() == 2006) {
						stat.setFuelgroupname("电");
					}
					stat.setFuelid(f.getFuelid());
					stat.setFuelname(f.getName());
					stat.setFuelunit(f.getUnit());
					stat.setPollutantName(f.getPollutantName());
					stat.setStatmonth(i);
					
					stat.setStat_dsrate(f.getDsrate());
					statlist.add(stat);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} 
	}

	private void invode1(List<StatDevice> statlist, Fuelres f) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = f.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(f));
				if (valMx > 0) {
					double pm = valMx * f.getAshcontent() * f.getFactor() * (1 - f.getDsrate()) / 1000;
					StatDevice stat = new StatDevice();
					stat.setFillyear(f.getFillyear());
					stat.setStatvalue(pm);
					stat.setPollutantId(f.getPollutantId());
					stat.setDeviceid(f.getDeviceid());
					stat.setCompanyid(f.getEnterpriceId());
					stat.setDevclass(f.getDevClass());
					stat.setFuelgroupid(f.getPid());
					stat.setFuelid(f.getFuelid());
					if (f.getPid() == 2001) {
						stat.setFuelgroupname("燃煤");
					}
					if (f.getPid() == 2002) {
						stat.setFuelgroupname("燃油");
					}
					if (f.getPid() == 2003) {
						stat.setFuelgroupname("燃气");
					}
					if (f.getPid() == 2004) {
						stat.setFuelgroupname("生物燃料");
					}
					if (f.getPid() == 2005) {
						stat.setFuelgroupname("生活垃圾");
					}
					if (f.getPid() == 2006) {
						stat.setFuelgroupname("电");
					}
					stat.setFuelname(f.getName());
					stat.setFuelunit(f.getUnit());
					stat.setPollutantName(f.getPollutantName());
					stat.setStatmonth(i);
					stat.setStat_exp("燃煤消耗量（吨）× 灰分 x 飞灰比系数 ×（1-去除效率）/1000");
					stat.setStat_valtype("计算锅炉燃煤的PM10/PM2.5排放量");
					stat.setStat_factor(f.getFactor()+"");
					stat.setStat_value(valMx +" X "+f.getAshcontent()+" X "+ f.getFactor() +" X ("+ 1 +" - "+ f.getDsrate()+") /"+  1000);
					stat.setStat_dsrate(f.getDsrate());
					statlist.add(stat);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	private void invode2(List<StatDevice> statlist, Fuelres f) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = f.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(f));
				if (valMx > 0) {
					double pm = valMx * f.getFactor() * (1 - f.getDsrate()) / 1000;
					StatDevice stat = new StatDevice();
					stat.setFillyear(f.getFillyear());
					stat.setStatvalue(pm);
					stat.setPollutantId(f.getPollutantId());
					stat.setDeviceid(f.getDeviceid());
					stat.setCompanyid(f.getEnterpriceId());
					stat.setDevclass(f.getDevClass());
					stat.setFuelgroupid(f.getPid());
					stat.setFuelid(f.getFuelid());
					if (f.getPid() == 2001) {
						stat.setFuelgroupname("燃煤");
					}
					if (f.getPid() == 2002) {
						stat.setFuelgroupname("燃油");
					}
					if (f.getPid() == 2003) {
						stat.setFuelgroupname("燃气");
					}
					if (f.getPid() == 2004) {
						stat.setFuelgroupname("生物燃料");
					}
					if (f.getPid() == 2005) {
						stat.setFuelgroupname("生活垃圾");
					}
					if (f.getPid() == 2006) {
						stat.setFuelgroupname("电");
					}
					stat.setFuelname(f.getName());
					stat.setFuelunit(f.getUnit());
					stat.setPollutantName(f.getPollutantName());
					stat.setStatmonth(i);
					stat.setStat_exp("燃料（煤）消耗量（吨）X 排放因子 X（1-去除效率）/1000");
					stat.setStat_valtype("计算锅炉燃其它燃料所有污染物（或燃煤的其它污染物）排放量");
					stat.setStat_factor(f.getFactor()+"");
					stat.setStat_value(valMx +" X "+ f.getFactor() +" X ("+ 1 +" - "+ f.getDsrate()+") /"+ 1000);
					stat.setStat_dsrate(f.getDsrate());
					statlist.add(stat);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	private void invode5(List<StatDevice> statlist, Fuelres f) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = f.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(f));
				if (valMx > 0) {
					double pm = valMx * f.getFactor() * (1 - f.getDsrate()) / 100;
					StatDevice stat = new StatDevice();
					stat.setFillyear(f.getFillyear());
					stat.setStatvalue(pm);
					stat.setPollutantId(f.getPollutantId());
					stat.setDeviceid(f.getDeviceid());
					stat.setCompanyid(f.getEnterpriceId());
					stat.setDevclass(f.getDevClass());
					stat.setFuelgroupid(f.getPid());
					stat.setFuelid(f.getFuelid());
					if (f.getPid() == 2001) {
						stat.setFuelgroupname("燃煤");
					}
					if (f.getPid() == 2002) {
						stat.setFuelgroupname("燃油");
					}
					if (f.getPid() == 2003) {
						stat.setFuelgroupname("燃气");
					}
					if (f.getPid() == 2004) {
						stat.setFuelgroupname("生物燃料");
					}
					if (f.getPid() == 2005) {
						stat.setFuelgroupname("生活垃圾");
					}
					if (f.getPid() == 2006) {
						stat.setFuelgroupname("电");
					}
					stat.setFuelname(f.getName());
					stat.setFuelunit(f.getUnit());
					stat.setPollutantName(f.getPollutantName());
					stat.setStatmonth(i);
					stat.setStat_exp("燃气量（万立方米) X 排放因子 X（1-去除效率）/100");
					stat.setStat_valtype("计算燃气锅炉燃料所有污染物排放量");
					stat.setStat_factor(f.getFactor()+"");
					stat.setStat_value(valMx +" X "+ f.getFactor() +" X ("+ 1 +" - "+ f.getDsrate()+") /"+ 100);
					stat.setStat_dsrate(f.getDsrate());
					statlist.add(stat);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	private void invode3(List<StatProd> pstatlist, ProdFuelstat pf,Prodres p) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = pf.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(pf));
				if (valMx > 0) {
					double res1 = 0;
					res1 = valMx * p.getFactor() * (1 - p.getDsrate()) / 1000;
					StatProd sp1 = new StatProd();
					sp1.setCompanyid(pf.getEnterpriceId());
					sp1.setFillyear(pf.getFillyear());
					sp1.setPollutantId(pf.getPollutantId());
					sp1.setProductid(pf.getFuelid());
					sp1.setStatvalue(res1);
					sp1.setStatmonth(i);
					sp1.setFuelid(pf.getFuelid());
					sp1.setStat_exp("燃料（煤）消耗量（吨）X 排放因子 X（1-去除效率）/1000");
					sp1.setStat_valtype("计算窑炉产品中燃料污染物的排放量");
					sp1.setStat_factor(p.getFactor()+"");
					sp1.setStat_value(valMx +" X "+ p.getFactor() +" X ("+ 1 +" - "+ p.getDsrate()+") /"+ 1000);
					sp1.setStat_dsrate(p.getDsrate());
					pstatlist.add(sp1);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	private void invode4(List<StatProd> pstatlist,Prodres p) throws Exception {

		try {
			@SuppressWarnings("rawtypes")
			Class clazz = p.getClass(); 
			for(int i = 1; i <= 12; i++) {
				@SuppressWarnings("unchecked")
				Method getMx = clazz.getDeclaredMethod("getM" + i);

				double valMx = (Double)(getMx.invoke(p));
				if (valMx > 0) {
					StatProd sp = new StatProd();
					double res = 0;
					// 计算产品产量污染物
					res = valMx * p.getFactor() * (1 - p.getDsrate()) / 1000;
					sp.setCompanyid(p.getEnterpriceId());
					sp.setFillyear(p.getFillyear());
					sp.setPollutantId(p.getPollutantId());
					sp.setProductid(p.getProductid());
					sp.setStatmonth(i);
					sp.setStatvalue(res);
					sp.setStat_exp("产品产量（吨）X 排放因子 X（1-去除效率）/1000");
					sp.setStat_valtype("计算窑炉产品中产量污染物的排放量");
					sp.setStat_factor(p.getFactor()+"");
					sp.setStat_value(valMx +" X "+ p.getFactor() +" X ("+ 1 +" - "+ p.getDsrate()+") /"+ 1000);
					sp.setStat_dsrate(p.getDsrate());
					pstatlist.add(sp);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			
			LocalYear curyear = MyDispatcherServlet.getYear();
			curyear.set(fillyear);
			computeService.deleteProdDevice(fillyear);
			flag = 10;
			// 得到所有燃料信息
			List<Fuelres> flist = computeService.computefuel(fillyear);
			// 得到燃料去除效率
			List<Fuelres> drelist = computeService.computefuel1();
			// 得到污染物对应表
			List<Pollutant> polist=pollutantService.getAll();
			
			for(Fuelres f:flist){
				f.setPollutantId2(getGroupid(polist,f.getPollutantId()));
			}
			
			// 将去除效率赋给燃料
			for(Fuelres f:flist){
				for(Fuelres dre:drelist){
					if(f.getDeviceid()==dre.getDeviceid()&&f.getPollutantId2()==dre.getPollutantId()){
						if(f.getPollutantId2()==4){
							if(f.getPollutantId()==5){
								f.setDsrate(dre.getDsrate2());
							}else if(f.getPollutantId()==6){
								f.setDsrate(dre.getDsrate());
							}
						}else{
							f.setDsrate(dre.getDsrate());
						}
					}
				}
			}
			// 匹配污染物与因子进行计算
			List<StatDevice> statlist = new ArrayList<StatDevice>();
			for (Fuelres f : flist) {
				//计算燃油的SO2排放
				if(f.getPollutantId()==1&&f.getPid()==2002){
					invode(statlist, f);
				}
				
				// 计算煤的so2排放
				else if (f.getPollutantId() == 1 && f.getPid() == 2001) {
					invode(statlist, f);
				}
				
				// 计算煤的pm2.5/pm2.5-10
				else if ((f.getPollutantId() == 5 || f.getPollutantId()==6 )&& f.getPid() == 2001) {
					invode1(statlist, f);
				}

				// 计算煤其它的排放
				else if (f.getPollutantId() != 5 &&f.getPollutantId() != 6&& f.getPollutantId() != 1
						&& f.getPid() == 2001) {
					invode2(statlist, f);
				}

				//计算燃气锅炉的排放
				else if (f.getPid() == 2003) {
					invode5(statlist, f);
				}
				// 计算其它燃料的所有排放
				else {
					invode2(statlist, f);
				}

			}
			flag = 50;
			
			//去掉查出的pm10污染物
			List<StatDevice> pmlist = new ArrayList<StatDevice>();
			for(StatDevice sd:statlist){
				if(sd.getPollutantId()==4){
					pmlist.add(sd);
				}
			}
			statlist.removeAll(pmlist);
			
			List<StatDevice> tlist = new ArrayList<StatDevice>();
			if(statlist.size()>0){
				//计算燃料污染物pm10
				for(StatDevice sd:statlist){
					for(StatDevice sd1:statlist){
						if(sd.getDeviceid()==sd1.getDeviceid()&&sd.getCompanyid()==sd1.getCompanyid()&&sd.getFuelid()==sd1.getFuelid()&&sd.getPollutantId()==5&&sd1.getPollutantId()==6&&sd.getStatmonth()==sd1.getStatmonth()){
							double statvalue=sd.getStatvalue()+sd1.getStatvalue();
							StatDevice stat = new StatDevice();
							stat.setFillyear(sd.getFillyear());
							stat.setStatvalue(statvalue);
							stat.setPollutantId(4);
							stat.setDeviceid(sd.getDeviceid());
							stat.setCompanyid(sd.getCompanyid());
							stat.setDevclass(sd.getDevclass());
							stat.setFuelgroupid(sd.getFuelgroupid());
							stat.setFuelid(sd.getFuelid());
							stat.setFuelgroupname(sd.getFuelgroupname());
							stat.setFuelname(sd.getFuelname());
							stat.setFuelunit(sd.getFuelunit());
							stat.setPollutantName("pm10");
							stat.setStatmonth(sd.getStatmonth());
							stat.setStat_exp("pm10=pm2.5+pm2.5-10");
							stat.setStat_valtype("计算锅炉燃煤的PM10排放量");
							stat.setStat_factor("无因子");
							stat.setStat_value(sd.getStatvalue()+" + "+sd1.getStatvalue());
							stat.setStat_dsrate(0);
							tlist.add(stat);
						}
					}
				}
				statlist.addAll(tlist);
				int count=0;
				List<StatDevice> newlist=new ArrayList<StatDevice>();
				for(StatDevice sp:statlist) {
					if(count > 0 && count % 500 == 0) {
						computeService.addFuelres(newlist);
						newlist.clear();
					}
					else {
						newlist.add(sp);
					}
					count++;
				}
				if(newlist.size() > 0){
					computeService.addFuelres(newlist);
				}
			}

			// 得到所有产品信息
			List<Prodres> plist = computeService.computeprod1(fillyear);
			// 得到所有去除效率
			List<Prodres> derlist = computeService.computeprod2(fillyear);
			// 得到所有原料信息
			List<Prodres> merlist = computeService.computeprod3(fillyear);
			
			for(Prodres p:plist){
				p.setPollutantId2(getGroupid(polist,p.getPollutantId()));
			}
			flag=60;
			
			List<StatProd> pstatlist = new ArrayList<StatProd>();
			//把去除效率赋给产品
			for(Prodres p:plist){
				for(Prodres dre:derlist){
					if(p.getProductid()==dre.getProductid()&&p.getPollutantId2()==dre.getPollutantId()&&p.getPfillid()==dre.getPfillid()){
						if(p.getPollutantId2()==4){
							if(p.getPollutantId()==5){
								p.setDsrate(dre.getDsrate2());
							}else if(p.getPollutantId()==6){
								p.setDsrate(dre.getDsrate());
							}
						}else{
							p.setDsrate(dre.getDsrate());
						}
					}
				}
			}
			
			//计算产品污染物
			for(Prodres p:plist){
				invode4(pstatlist,p);
			}
			
			//把去除效率赋给原材料
			for(Prodres m:merlist){
				for(Prodres dre:derlist){
					if(m.getProductid()==dre.getProductid()&&m.getPollutantId()==dre.getPollutantId()&&m.getPfillid()==dre.getPfillid()){
						m.setDsrate(dre.getDsrate());
					}
				}
			}
			
			//计算原材料污染物
			for(Prodres m:merlist){
				StatProd sp = new StatProd();
				double res = 0;
				res = m.getConsumeOfYear() * m.getFactor() * (1 - m.getDsrate()) / 1000;
				sp.setCompanyid(m.getEnterpriceId());
				sp.setFillyear(fillyear);
				sp.setPollutantId(m.getPollutantId());
				sp.setProductid(m.getProductid());
				sp.setMaterialid(m.getMaterialId());
				sp.setStatvalue(res);
				sp.setStat_exp("原辅料用量（吨）X 排放因子 X（1-去除效率）/1000");
				sp.setStat_valtype("计算窑炉产品中原辅料污染物的排放量");
				sp.setStat_factor(m.getFactor()+"");
				sp.setStat_value(m.getConsumeOfYear() +" X "+ m.getFactor() +" X ("+ 1 +" - "+ m.getDsrate()+") /"+ 1000);
				sp.setStat_dsrate(m.getDsrate());
				pstatlist.add(sp);
			}

			flag = 90;
			
			//去掉查出的pm10污染物
			List<StatProd> pmlist2 = new ArrayList<StatProd>();
			for(StatProd sd:pstatlist){
				if(sd.getPollutantId()==4){
					pmlist2.add(sd);
				}
			}
			pstatlist.removeAll(pmlist2);
			
			List<StatProd> pslist = new ArrayList<StatProd>();
			if(pstatlist.size()>0){
				for(StatProd sp:pstatlist){
					for(StatProd sp1:pstatlist){
						if(sp.getCompanyid()==sp1.getCompanyid()&&sp.getProductid()==sp1.getProductid()&&sp.getStatmonth()==sp1.getStatmonth()&&sp.getPollutantId()==5&&sp1.getPollutantId()==6&&sp.getMaterialid()==0&&sp1.getMaterialid()==0){
							StatProd sp2 = new StatProd();
							double res = 0;
							// 计算产品产量污染物pm10
							res = sp.getStatvalue()+sp1.getStatvalue();
							sp2.setCompanyid(sp.getCompanyid());
							sp2.setFillyear(sp.getFillyear());
							sp2.setPollutantId(4);
							sp2.setProductid(sp.getProductid());
							sp2.setStatmonth(sp.getStatmonth());
							sp2.setStatvalue(res);
							sp2.setStat_exp("pm10=pm2.5+pm2.5-10");
							sp2.setStat_valtype("计算窑炉产品中产量污染物的排放量");
							sp2.setStat_factor("无因子");
							sp2.setStat_value(sp.getStatvalue()+" + "+sp1.getStatvalue());
							sp2.setStat_dsrate(0);
							pslist.add(sp2);
							// 计算产品原料污染物pm10
						}else if(sp.getCompanyid()==sp1.getCompanyid()&&sp.getProductid()==sp1.getProductid()&&sp.getPollutantId()==5&&sp1.getPollutantId()==6&&sp.getMaterialid()>0&&sp1.getMaterialid()>0){
							StatProd sp2 = new StatProd();
							double res = 0;
							res = sp.getStatvalue()+sp1.getStatvalue();
							sp2.setCompanyid(sp.getCompanyid());
							sp2.setFillyear(sp.getFillyear());
							sp2.setPollutantId(4);
							sp2.setProductid(sp.getProductid());
							sp2.setMaterialid(sp.getMaterialid());
							sp2.setStatvalue(res);
							sp2.setStat_exp("pm10=pm2.5+pm2.5-10");
							sp2.setStat_valtype("计算窑炉产品中原辅料污染物的排放量");
							sp2.setStat_factor("无因子");
							sp2.setStat_value(sp.getStatvalue()+" + "+sp1.getStatvalue());
							sp2.setStat_dsrate(0);
							pslist.add(sp);
						}
					}
				}
				pstatlist.addAll(pslist);
				int count=0;
				List<StatProd> newlist=new ArrayList<StatProd>();
				for(StatProd sp:pstatlist) {
					System.out.println(sp.getFillyear()+"-"+sp.getStatmonth()+"-"+sp.getCompanyid()+"-"+sp.getProductid()+"-"+sp.getPollutantId()+"-"+sp.getMaterialid());
				}
				
				for(StatProd sp:pstatlist) {
					if(count > 0 && count % 500 == 0) {
						newlist.add(sp);
						computeService.addProdres(newlist);
						newlist.clear();
					}
					else {
						newlist.add(sp);
					}
					count++;
				}
				if(newlist.size() > 0){
					computeService.addProdres(newlist);
				}
			}
			flag = 100;
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
			flag=-1;
		}
	}

	public static Compute getInstance(ComputeService computeService1,int fillyear1,PollutantService pollutantService1) {
		if (compute == null) {
			synchronized (Compute.class) {
				if (compute == null) {
					compute = new Compute();
					computeService = computeService1;
					fillyear=fillyear1;
					pollutantService=pollutantService1;
				}
			}
		}
		else {
			if (flag >= 100 || flag <= 0) {
				computeService = computeService1;
				fillyear=fillyear1;
				pollutantService=pollutantService1;
			}
		}
		return compute;
	}
}
