package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import com.xf.entity.gov.Boat;
import com.xf.entity.gov.Cleaner;
import com.xf.entity.gov.Construction;
import com.xf.entity.gov.DumpField;
import com.xf.entity.gov.EnergySell;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.Oildepot;
import com.xf.entity.gov.Pesticide;
import com.xf.service.stat.NumerationService;

public class Numeration {
	private static final String TYPE_NAME_BOAT = "boat";
	private static final String TRAVELLER_NUM = "旅客周转量";
	private static final String CARGO_NUM = "货物周转量";
	private static final String OILDEPOT_WAY = "发油台发油方式";
	private static final String OILDEPOT_CONVEY = "油品输送类型";

	public void runstart(NumerationService theService, int fillyear,int typeid) {
		try {
			double efactor = 0;
			double ffactor = 0;
			GovStat cov = new GovStat();
			List<GovFactor> govfactor = theService.getGovfactor("laundry");
			if (govfactor != null) {
				for (GovFactor gli : govfactor) {
					if (gli.getId() == 9) {
						if (gli.getType_x().equals("排放系数"))
							efactor = gli.getFactor();
						if (gli.getType_x().equals("校正因子"))
							ffactor = gli.getFactor();
						cov.setPollutantId(gli.getId());
						cov.setStattype(gli.getTypename());
					}
				}
			}
			List<GovStat> clist = new ArrayList<GovStat>();
			List<Cleaner> numlist = null;
			if(typeid==1){
				numlist = theService.getCleanerNumber2(fillyear);
			}else if(typeid==2){
				numlist = theService.getCleanerNumber(fillyear);
			}
			if (numlist != null) {
				for (Cleaner num : numlist) {
					GovStat covs = new GovStat();
					double number = num.getCleanerNumber();
					covs.setStatvalue(number * efactor * ffactor);
					covs.setPollutantId(cov.getPollutantId());
					covs.setProvince(num.getProvince());
					covs.setCity(num.getCity());
					covs.setTown(num.getTown());
					covs.setStattype(cov.getStattype());
					covs.setFillyear(fillyear);
					covs.setStat_exp("E=干洗店数量*排放系数*校正因子");
					covs.setStat_valtype("计算干洗店污染排放");
					covs.setStat_value("E=" + number + "*" + efactor + "*"
							+ ffactor);
					covs.setStat_factor("排放系数:" + efactor + "校正因子:" + ffactor);
					clist.add(covs);
				}
			}
			if (clist.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:clist) {
					if(count > 0 && count % 100 == 0) {
						 if(typeid==1){
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
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
			        	   theService.addGovStat2(newlist);
						}else if(typeid==2){
							theService.addGovStat(newlist);
						}
				}
			}
				

			List<GovStat> blist = new ArrayList<GovStat>();
			HashSet<Integer> set = new LinkedHashSet<Integer>();
			List<Boat> boat = null;
            if(typeid==1){
            	boat = theService.getBoatNumber2(fillyear);
			}else if(typeid==2){
				boat = theService.getBoatNumber(fillyear);
			}
			if (boat != null) {
				for (Boat bo : boat) {
					if (bo.getMeasuretype().equals(TRAVELLER_NUM)
							|| bo.getMeasuretype().equals(CARGO_NUM))
						set.add(bo.getCity());
				}
			}

			List<GovFactor> boatfactor = theService
					.getGovfactor(TYPE_NAME_BOAT);
			for (Integer it : set) {
				List<Double> cargovalue = new ArrayList<Double>();
				List<Double> travevalue = new ArrayList<Double>();
				int city = it;
				for (Boat bo : boat) {
					if (city == bo.getCity()) {
						if (bo.getMeasuretype().equals(CARGO_NUM)) {
							cargovalue.add(bo.getM1());
							cargovalue.add(bo.getM2());
							cargovalue.add(bo.getM3());
							cargovalue.add(bo.getM4());
							cargovalue.add(bo.getM5());
							cargovalue.add(bo.getM6());
							cargovalue.add(bo.getM7());
							cargovalue.add(bo.getM8());
							cargovalue.add(bo.getM9());
							cargovalue.add(bo.getM10());
							cargovalue.add(bo.getM11());
							cargovalue.add(bo.getM12());
						}
						if (bo.getMeasuretype().equals(TRAVELLER_NUM)) {
							travevalue.add(bo.getM1());
							travevalue.add(bo.getM2());
							travevalue.add(bo.getM3());
							travevalue.add(bo.getM4());
							travevalue.add(bo.getM5());
							travevalue.add(bo.getM6());
							travevalue.add(bo.getM7());
							travevalue.add(bo.getM8());
							travevalue.add(bo.getM9());
							travevalue.add(bo.getM10());
							travevalue.add(bo.getM11());
							travevalue.add(bo.getM12());
						}
					}
				}

				for (int i = 0; i < 12; i++) {
					if (boatfactor != null) {
						double yx = 0;
						double ef = 0;
						GovStat covs = new GovStat();
						for (GovFactor fac : boatfactor) {
							if (fac.getId() == 3) {
								if (fac.getType_x().equals("油耗系数YX")) {
									yx = fac.getFactor();
								}
								if (fac.getType_x().equals("EF因子")) {
									ef = fac.getFactor();
								}
								covs.setPollutantId(fac.getId());
								covs.setProvince(1);
								covs.setCity(it);
								covs.setFillyear(fillyear);
								covs.setMonths(i + 1);
								covs.setStat_exp("E=(((0.065*乘客数+货物数)*油耗系数)*排放系数)*10^(-6)");
								covs.setStat_valtype("计算船舶污染排放");
								covs.setStat_value("E=" + "(((" + "0.065*"
										+ travevalue.get(i) + "+"
										+ cargovalue.get(i) + ")*" + yx + ")*"
										+ ef + ")*" + "10^(-6)");
								covs.setStat_factor("排放系数:" + ef + "油耗系数:" + yx);
								double statv = (((0.065 * travevalue.get(i) + cargovalue
										.get(i)) * yx) * ef);
								double n = Math.pow(10, -6);
								if (statv > 0)
									covs.setStatvalue(n * statv);
								covs.setStattype(TYPE_NAME_BOAT);
							}
						}
						blist.add(covs);

						yx = 0;
						ef = 0;
						GovStat c = new GovStat();
						for (GovFactor fac : boatfactor) {
							if (fac.getId() == 2) {
								if (fac.getType_x().equals("油耗系数YX")) {
									yx = fac.getFactor();
								}
								if (fac.getType_x().equals("EF因子")) {
									ef = fac.getFactor();
								}
								c.setPollutantId(fac.getId());
								c.setProvince(1);
								c.setCity(it);
								c.setFillyear(fillyear);
								c.setMonths(i + 1);
								c.setStat_exp("E=(((0.065*乘客数+货物数)*油耗系数)*排放系数)*10^(-6)");
								c.setStat_valtype("计算船舶污染排放");
								c.setStat_value("E=" + "(((" + "0.065*"
										+ travevalue.get(i) + "+"
										+ cargovalue.get(i) + ")*" + yx + ")*"
										+ ef + ")*" + "10^(-6)");
								c.setStat_factor("排放系数:" + ef + "油耗系数:" + yx);
								double statv = (((0.065 * travevalue.get(i) + cargovalue
										.get(i)) * yx) * ef);
								double n = Math.pow(10, -6);
								if (statv > 0)
									c.setStatvalue(n * statv);
								c.setStattype(TYPE_NAME_BOAT);
							}
						}
						blist.add(c);

						yx = 0;
						ef = 0;
						GovStat co = new GovStat();
						for (GovFactor fac : boatfactor) {
							if (fac.getId() == 4) {
								if (fac.getType_x().equals("油耗系数YX")) {
									yx = fac.getFactor();
								}
								if (fac.getType_x().equals("EF因子")) {
									ef = fac.getFactor();
								}
								co.setPollutantId(fac.getId());
								co.setProvince(1);
								co.setCity(it);
								co.setFillyear(fillyear);
								co.setMonths(i + 1);
								co.setStat_exp("E=(((0.065*乘客数+货物数)*油耗系数)*排放系数)*10^(-6)");
								co.setStat_valtype("计算船舶污染排放");
								co.setStat_value("E=" + "(((" + "0.065*"
										+ travevalue.get(i) + "+"
										+ cargovalue.get(i) + ")*" + yx + ")*"
										+ ef + ")*" + "10^(-6)");
								co.setStat_factor("排放系数:" + ef + "油耗系数:" + yx);
								double statv = (((0.065 * travevalue.get(i) + cargovalue
										.get(i)) * yx) * ef);
								double n = Math.pow(10, -6);
								if (statv > 0)
									co.setStatvalue(n * statv);
								co.setStattype(TYPE_NAME_BOAT);
							}
						}
						blist.add(co);

						yx = 0;
						ef = 0;
						GovStat covst = new GovStat();
						for (GovFactor fac : boatfactor) {
							if (fac.getId() == 6) {
								if (fac.getType_x().equals("油耗系数YX")) {
									yx = fac.getFactor();
								}
								if (fac.getType_x().equals("EF因子")) {
									ef = fac.getFactor();
								}
								covst.setPollutantId(fac.getId());
								covst.setProvince(1);
								covst.setCity(it);
								covst.setFillyear(fillyear);
								covst.setMonths(i + 1);
								covst.setStat_exp("E=(((0.065*乘客数+货物数)*油耗系数)*排放系数)*10^(-6)");
								covst.setStat_valtype("计算船舶污染排放");
								covst.setStat_value("E=" + "(((" + "0.065*"
										+ travevalue.get(i) + "+"
										+ cargovalue.get(i) + ")*" + yx + ")*"
										+ ef + ")*" + "10^(-6)");
								covst.setStat_factor("排放系数:" + ef + "油耗系数:"
										+ yx);
								double statv = (((0.065 * travevalue.get(i) + cargovalue
										.get(i)) * yx) * ef);
								double n = Math.pow(10, -6);
								if (statv > 0)
									covst.setStatvalue(n * statv);
								covst.setStattype(TYPE_NAME_BOAT);
							}
						}
						blist.add(covst);

						yx = 0;
						ef = 0;
						GovStat covsta = new GovStat();
						for (GovFactor fac : boatfactor) {
							if (fac.getType_y().equals("HC")) {
								if (fac.getType_x().equals("油耗系数YX")) {
									yx = fac.getFactor();
								}
								if (fac.getType_x().equals("EF因子")) {
									ef = fac.getFactor();
								}
								covsta.setPollutantId(9);
								covsta.setProvince(1);
								covsta.setCity(it);
								covsta.setFillyear(fillyear);
								covsta.setMonths(i + 1);
								covsta.setStat_exp("E=(((0.065*乘客数+货物数)*油耗系数)*排放系数)*10^(-6)");
								covsta.setStat_valtype("计算船舶污染排放");
								covsta.setStat_value("E=" + "(((" + "0.065*"
										+ travevalue.get(i) + "+"
										+ cargovalue.get(i) + ")*" + yx + ")*"
										+ ef + ")*" + "10^(-6)");
								covsta.setStat_factor("排放系数:" + ef + "油耗系数:"
										+ yx);
								double statv = (((0.065 * travevalue.get(i) + cargovalue
										.get(i)) * yx) * ef);
								double n = Math.pow(10, -6);
								if (statv > 0)
									covsta.setStatvalue(n * statv);
								covsta.setStattype(TYPE_NAME_BOAT);
							}
						}
						blist.add(covsta);
					}
				}

			}
			if (blist.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:blist) {
					if(count > 0 && count % 100 == 0) {
						 if(typeid==1){
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
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
			        	   theService.addGovStat2(newlist);
						}else if(typeid==2){
							theService.addGovStat(newlist);
						}
				}
			}
				

			double waydi = 0;
			double waydin = 0;
			double convey = 0;
			double conveyg = 0;
			double conveyt = 0;
			double xisu = 0;
			List<GovFactor> oilfac = theService.getGovfactor("oildepot");
			if (oilfac != null) {
				for (GovFactor fa : oilfac) {
					if (fa.getType_x().equals(OILDEPOT_WAY)) {
						if (fa.getType_x2().equals("底部装油")) {
							waydi = fa.getFactor();
						}
						if (fa.getType_x2().equals("顶部装油")) {
							waydin = fa.getFactor();
						}
					}
					if (fa.getType_x().equals(OILDEPOT_CONVEY)) {
						if (fa.getType_x2().equals("公路")) {
							convey = fa.getFactor();
						}
						if (fa.getType_x2().equals("铁路")) {
							conveyt = fa.getFactor();
						}
						if (fa.getType_x2().equals("管道")) {
							conveyg = fa.getFactor();
						}
					}
					if (fa.getType_x().equals("排放系数")) {
						xisu = fa.getFactor();
					}
				}
			}

			List<Oildepot> oil = null;
	           if(typeid==1){
	        	   oil = theService.getOildepotNumber2(fillyear);
				}else if(typeid==2){
					oil = theService.getOildepotNumber(fillyear);
				}
			List<GovStat> olist = new ArrayList<GovStat>();
			for (Oildepot o : oil) {
				double statv = 0;
				double s = 0;
				GovStat covsta = new GovStat();

				int way = o.getWay();
				int recy = o.getRecycleDevice();
				int conveytype = o.getConveyType();
				double gasoil = o.getGasolineGross();
				double rec = o.getRecovery() / 100;
				double die = o.getDieselGross();

				// 底部装油
				if (way == 0) {
					// 回收装置：有
					if (recy == 1) {
						// 公路
						if (conveytype == 0) {
							statv = (gasoil / 2) * waydi * (1 - rec * 1) * xisu
									+ (die / 2) * waydi * (1 - rec * 1) * xisu
									+ (gasoil / 2) * convey + (die / 2)
									* convey;
							s += statv;
						}
						// 地铁
						if (conveytype == 1) {
							statv = (gasoil / 2) * waydi * (1 - rec * 1) * xisu
									+ (die / 2) * waydi * (1 - rec * 1) * xisu
									+ (gasoil / 2) * conveyt + (die / 2)
									* conveyt;
							s += statv;
						}
						// 管道
						if (conveytype == 2) {
							statv = (gasoil / 2) * waydi * (1 - rec * 1) * xisu
									+ (die / 2) * waydi * (1 - rec * 1) * xisu
									+ (gasoil / 2) * conveyg + (die / 2)
									* conveyg;
							s += statv;
						}

					}
					// 回收装置：无
					if (recy == 0) {
						if (conveytype == 0) {
							statv = (gasoil / 2) * waydi * (1 - rec * 0) * xisu
									+ (die / 2) * waydi * (1 - rec * 0) * xisu
									+ (gasoil / 2) * convey + (die / 2)
									* convey;
							s += statv;
						}
						if (conveytype == 1) {
							statv = (gasoil / 2) * waydi * (1 - rec * 0) * xisu
									+ (die / 2) * waydi * (1 - rec * 0) * xisu
									+ (gasoil / 2) * conveyt + (die / 2)
									* conveyt;
							s += statv;
						}
						if (conveytype == 2) {
							statv = (gasoil / 2) * waydi * (1 - rec * 0) * xisu
									+ (die / 2) * waydi * (1 - rec * 0) * xisu
									+ (gasoil / 2) * conveyg + (die / 2)
									* conveyg;
							s += statv;
						}
					}

				}
				// 顶部装油
				if (way == 1) {
					if (recy == 1) {
						if (conveytype == 0) {
							statv = (gasoil / 2) * waydin * (1 - rec * 1)
									* xisu + (die / 2) * waydin * (1 - rec * 1)
									* xisu + (gasoil / 2) * convey + (die / 2)
									* convey;
							s += statv;
						}
						if (conveytype == 1) {
							statv = (gasoil / 2) * waydin * (1 - rec * 1)
									* xisu + (die / 2) * waydin * (1 - rec * 1)
									* xisu + (gasoil / 2) * conveyt + (die / 2)
									* conveyt;
							s += statv;

						}
						if (conveytype == 2) {
							statv = (gasoil / 2) * waydin * (1 - rec * 1)
									* xisu + (die / 2) * waydin * (1 - rec * 1)
									* xisu + (gasoil / 2) * conveyg + (die / 2)
									* conveyg;
							s += statv;

						}
					}
					if (recy == 0) {
						if (conveytype == 0) {
							statv = (gasoil / 2) * waydin * (1 - rec * 0)
									* xisu + (die / 2) * waydin * (1 - rec * 0)
									* xisu + (gasoil / 2) * convey + (die / 2)
									* convey;
							s += statv;
						}
						if (conveytype == 1) {
							statv = (gasoil / 2) * waydin * (1 - rec * 0)
									* xisu + (die / 2) * waydin * (1 - rec * 0)
									* xisu + (gasoil / 2) * conveyt + (die / 2)
									* conveyt;
							s += statv;
						}
						if (conveytype == 2) {
							statv = (gasoil / 2) * waydin * (1 - rec * 0)
									* xisu + (die / 2) * waydin * (1 - rec * 0)
									* xisu + (gasoil / 2) * conveyg + (die / 2)
									* conveyg;
							s += statv;
						}
					}
				}

				double wayu = 0;
				double conveytypeu = 0;

				if (o.getWay() == 1) {
					wayu = waydin;
				} else {
					wayu = waydi;
				}
				if (o.getConveyType() == 1) {
					conveytypeu = conveyt;
				} else if (o.getConveyType() == 2) {
					conveytypeu = conveyg;
				} else {
					conveytypeu = convey;
				}

				covsta.setCity(o.getCity());
				covsta.setStat_valtype(o.getOildepotName());
				covsta.setProvince(o.getProvince());
				covsta.setPollutantId(oilfac.get(0).getId());
				covsta.setTown(o.getTown());
				covsta.setFillyear(fillyear);
				covsta.setStattype("oildepot");
				covsta.setStatvalue(s);
				covsta.setStat_exp("E=(汽油年吞吐量（吨）/2)*发油台发油方式（上下装）*(1-有无油气回收装置*油气回收率（%）)*排放系数+(柴油年吞吐量（吨）/2)*发油台发油方式（上下装）*(1-有无油气回收装置*油气回收率（%）)*排放系数+(汽油年吞吐量（吨）/2)*油品输送类型+(柴油年吞吐量（吨）/2)*油品输送类型");
				covsta.setStat_value("E=(" + gasoil + "/2)*" + wayu + "*(1-"
						+ recy + "*" + rec + ")*" + xisu + "+(" + die + "/2)*"
						+ wayu + "*(1-" + recy + "*" + rec + ")*" + xisu + "+("
						+ gasoil + "/2)*" + conveytypeu + "+(" + die + "/2)*"
						+ conveytypeu);
				covsta.setStat_factor("排放系数:" + xisu + "底部装油:" + waydi
						+ "顶部装油:" + waydin + "公路:" + convey + "铁路:" + conveyt
						+ "管道:" + conveyg);
				olist.add(covsta);
			}

			if (olist.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:olist) {
					if(count > 0 && count % 100 == 0) {
						 if(typeid==1){
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
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
			        	   theService.addGovStat2(newlist);
						}else if(typeid==2){
							theService.addGovStat(newlist);
						}
				}
			}
				
			
			// 农药用量VOCs计算
			double didi = 0;
			double leguo = 0;
			double juzhi = 0;
			double baicao = 0;
			double duojun = 0;
			double caogan = 0;
			double daowen = 0;
			int pollutantid = 0;
			List<GovFactor> pesFactor = theService.getGovfactor("pesticide");
			for (GovFactor gli : pesFactor) {
				pollutantid = gli.getId();
				if (gli.getType_x2() != null) {
					if (gli.getType_x2().equals("敌敌畏"))
						didi = gli.getFactor();
					if (gli.getType_x2().equals("氧化乐果"))
						leguo = gli.getFactor();
					if (gli.getType_x2().equals("氯氰菊脂"))
						juzhi = gli.getFactor();
					if (gli.getType_x2().equals("百草枯"))
						baicao = gli.getFactor();
					if (gli.getType_x2().equals("多菌灵"))
						duojun = gli.getFactor();
					if (gli.getType_x2().equals("草甘膦"))
						caogan = gli.getFactor();
					if (gli.getType_x2().equals("稻瘟净"))
						daowen = gli.getFactor();
				}
			}

			HashSet<Integer> setpes = new LinkedHashSet<Integer>();
			List<Pesticide> pesNumber = null;
	           if(typeid==1){
	        	   pesNumber = theService.getPesticideNumber2(fillyear);
				}else if(typeid==2){
					pesNumber = theService.getPesticideNumber(fillyear);
				}
			for (Pesticide pli : pesNumber) {
				setpes.add(pli.getCity());
			}

			List<GovStat> peslist = new ArrayList<GovStat>();
			for (Integer s : setpes) {
				for (Pesticide pli : pesNumber) {
					if (s == pli.getCity()) {
						GovStat pgov = new GovStat();
						double ss = pli.getWorm_ddt() * didi
								+ pli.getWorm_juzhi() * juzhi
								+ pli.getWorm_leguo() * leguo
								+ pli.getGrass_baicao() * baicao
								+ pli.getGrass_ganlin() * caogan
								+ pli.getGerm_duojun() * duojun
								+ pli.getGerm_daowen() * daowen;
						pgov.setStatvalue(ss);
						pgov.setCity(s);
						pgov.setFillyear(pli.getFillyear());
						pgov.setProvince(1);
						pgov.setStattype("pesticide");
						pgov.setStattype3(pli.getCrop());
						pgov.setPollutantId(pollutantid);
						pgov.setStat_exp("E=各类杀虫剂排放系数*农作物各类用量+各类除草剂排放系数*农作物各类用量+杀菌剂*农作物用量");
						pgov.setStat_valtype("计算农药使用污染排放");
						pgov.setStat_factor("排放系数：敌敌畏：" + didi + "氧化乐果："
								+ leguo + "氯氰菊脂：" + juzhi + "百草枯：" + baicao
								+ "多菌灵：" + duojun + "草甘膦：" + caogan + "稻瘟净："
								+ daowen);
						pgov.setStat_value("E=" + pli.getWorm_ddt() + "*" + didi + "+" + 
								pli.getWorm_juzhi() + "*" + juzhi + "+" + 
								pli.getWorm_leguo() + "*" + leguo + "+" + 
								pli.getGrass_baicao() + "*" + baicao + "+" + 
								pli.getGerm_duojun() + "*" + duojun + "+" + 
								pli.getGrass_ganlin() + "*" + caogan + "+" + 
								pli.getGerm_daowen() + "*" + daowen);
						peslist.add(pgov);
					}
				}
			}

			String type[] = { "energysell", "energyconsume" };

			for (int i = 0; i < type.length; i++) {

				double indusecoal = 0;
				double indusegas = 0;
				double indusepetroleum = 0;
				double indusefuel = 0;
				double civiliancoal = 0;
				double civiliangas = 0;
				double civilianpetroleum = 0;
				double civilianfuel = 0;
				int p = 0;
				List<GovFactor> energysell = theService.getX2factor(type[i]);
				for (GovFactor en : energysell) {
					p = en.getId();
					if (en.getType_x() != null && en.getType_x().equals("工业使用")) {
						if (en.getType_y().equals("煤炭排放系数（千克/吨）"))
							indusecoal = en.getFactor();
						if (en.getType_y().equals("天然气排放系数（千克/万立方米）"))
							indusegas = en.getFactor();
						if (en.getType_y().equals("液化石油气排放系数（千克/万立方米）"))
							indusepetroleum = en.getFactor();
						if (en.getType_y().equals("燃料油排放系数（千克/吨）"))
							indusefuel = en.getFactor();
					}
					if (en.getType_x() != null && en.getType_x().equals("民用")) {
						if (en.getType_y().equals("煤炭排放系数（千克/吨）"))
							civiliancoal = en.getFactor();
						if (en.getType_y().equals("天然气排放系数（千克/万立方米）"))
							civiliangas = en.getFactor();
						if (en.getType_y().equals("液化石油气排放系数（千克/万立方米）"))
							civilianpetroleum = en.getFactor();
						if (en.getType_y().equals("燃料油排放系数（千克/吨）"))
							civilianfuel = en.getFactor();
					}
				}
				List<EnergySell> energylist = null;
				if (type[i].equals("energysell")) {
					
			           if(typeid==1){
			        	   energylist = theService.getEnergysell2(fillyear);
						}else if(typeid==2){
							energylist = theService.getEnergysell(fillyear);
						}
				} else {
					
			           if(typeid==1){
			        	   energylist = theService.getEnergyconsume2(fillyear);
						}else if(typeid==2){
							energylist = theService.getEnergyconsume(fillyear);
						}
				}
				HashSet<Integer> cityset = new LinkedHashSet<Integer>();
				for (EnergySell li : energylist) {
					cityset.add(li.getCity());
				}

				List<GovStat> enylist = new ArrayList<GovStat>();
				for (Integer cir : cityset) {
					for (EnergySell li : energylist) {
						if (cir == li.getCity()) {
							GovStat energy = new GovStat();
							double tal = 0;
							if (li.getPurpose().equals("工业使用")) {
								tal = (li.getCoal() * indusecoal + li.getGas()
										* indusegas + li.getLiqgas()
										* indusepetroleum + li.getOil()
										* indusefuel) * 0.001;
								energy.setStat_factor("工业使用排放系数：煤炭："
										+ indusecoal + "天然气：" + indusegas
										+ "液化石油气：" + indusepetroleum + "燃料油："
										+ indusefuel);
								energy.setStat_value("E=(" + li.getCoal() + "*"
										+ indusecoal + "+" + li.getGas() + "*"
										+ indusegas + "+" + li.getLiqgas()
										+ "*" + indusepetroleum + "+"
										+ li.getOil() + "*" + indusefuel + ")*"
										+ 0.001);
							}
							if (li.getPurpose().equals("民用")) {
								tal = (li.getCoal() * civiliancoal
										+ li.getGas() * civiliangas
										+ li.getLiqgas() * civilianpetroleum + li
										.getOil() * civilianfuel) * 0.001;
								energy.setStat_factor("民用排放系数：煤炭："
										+ civiliancoal + "天然气：" + civiliangas
										+ "液化石油气：" + civilianpetroleum + "燃料油："
										+ civilianfuel);
								energy.setStat_value("E=(" + li.getCoal() + "*"
										+ civiliancoal + "+" + li.getGas()
										+ "*" + civiliangas + "+"
										+ li.getLiqgas() + "*"
										+ civilianpetroleum + "+" + li.getOil()
										+ "*" + civilianfuel + ")*" + 0.001);
							}
							energy.setProvince(1);
							energy.setPollutantId(p);
							energy.setCity(cir);
							energy.setFillyear(li.getFillyear());
							energy.setStattype(type[i]);
							energy.setStattype3(li.getPurpose());
							energy.setStatvalue(tal);
							energy.setStat_exp("E=各类销量*对应排放系数/1000再相加");
							if (type[i].equals("energysell")) {
								energy.setStat_valtype("计算能源销售污染排放");
							} else {
								energy.setStat_valtype("计算能源消耗污染排放");
							}
							enylist.add(energy);
						}
					}
				}
				if (enylist.size() > 0){
					int count=0;
					List<GovStat> newlist=new ArrayList<GovStat>();
					for(GovStat sp:enylist) {
						if(count > 0 && count % 100 == 0) {
							 if(typeid==1){
					        	   theService.addGovStat2(newlist);
								}else if(typeid==2){
									theService.addGovStat(newlist);
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
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
							}
					}
				}
					
			}

			double incineration = 0;
			double landfill = 0;
			double compost = 0;
			double sewage = 0;
			int poll = 0;
			List<GovFactor> wastefactor = theService.getXfactor("waste");
			for (GovFactor wasli : wastefactor) {
				poll = wasli.getId();
				if (wasli.getType_y() != null) {
					if (wasli.getType_y().equals("垃圾焚烧排放系数（千克/吨）"))
						incineration = wasli.getFactor();
					if (wasli.getType_y().equals("垃圾填埋排放系数（千克/吨）"))
						landfill = wasli.getFactor();
					if (wasli.getType_y().equals("垃圾堆肥排放系数（千克/吨）"))
						compost = wasli.getFactor();
					if (wasli.getType_y().equals("污水处理排放系数（千克/吨）"))
						sewage = wasli.getFactor();
				}
			}

			List<DumpField> dumplist = null;
	           if(typeid==1){
	        	   dumplist = theService.getDumpField2(fillyear);
				}else if(typeid==2){
					dumplist = theService.getDumpField(fillyear);
				}
			HashSet<Integer> dumpset = new LinkedHashSet<Integer>();
			for (DumpField dli : dumplist) {
				dumpset.add(dli.getCity());
			}

			for (Integer ds : dumpset) {
				for (DumpField dli : dumplist) {
					if (ds == dli.getCity()) {
						GovStat dumpgov = new GovStat();
						double dump = (incineration * dli.getRubbish_burn()
								+ landfill * dli.getRubbish_bury()
								+ dli.getRubbish_hill() * compost + sewage
								* dli.getSewerage_total()) * 10;
						dumpgov.setPollutantId(poll);
						dumpgov.setProvince(1);
						dumpgov.setCity(ds);
						dumpgov.setFillyear(dli.getFillyear());
						dumpgov.setStattype("waste");
						dumpgov.setStattype3(dli.getFactorytype());
						dumpgov.setStatvalue(dump);
						dumpgov.setStat_exp("E=各排放系数*各处理量*10");
						dumpgov.setStat_valtype("计算垃圾场污水厂污染排放");
						dumpgov.setStat_factor("排放系数：垃圾焚烧：" + incineration
								+ "垃圾填埋：" + landfill + "垃圾堆肥：" + compost
								+ "污水处理：" + sewage);
						dumpgov.setStat_value("E=(" + incineration + "*"
								+ dli.getRubbish_burn() + "+" + landfill + "*"
								+ dli.getRubbish_bury() + "+"
								+ dli.getRubbish_hill() + "*" + compost + "+"
								+ sewage + "*" + dli.getSewerage_total() + ")*"
								+ 10);
						peslist.add(dumpgov);
					}
				}
			}
			if (peslist.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:peslist) {
					if(count > 0 && count % 100 == 0) {
						 if(typeid==1){
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
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
			        	   theService.addGovStat2(newlist);
						}else if(typeid==2){
							theService.addGovStat(newlist);
						}
				}
			}
				

			List<GovStat> pes = new ArrayList<GovStat>();
			double A = 0;
			double B = 0;
			double C = 0;
			double EF1 = 0;
			double D = 0;
			double E = 0;
			double F = 0;
			double EF2 = 0;
			double QA = 0;
			double QB = 0;
			double QEF = 0;
			int pos = 0;
			List<GovFactor> buildfactor = theService.getXfactor("build");
			for (GovFactor li : buildfactor) {
				pos = li.getId();
				if (li.getType_y() != null) {
					if (li.getType_y().equals("内墙面积换算系数"))
						A = li.getFactor();
					if (li.getType_y().equals("单位内墙面积涂料用量（吨/平方米）"))
						B = li.getFactor();
					if (li.getType_y().equals("内墙涂料利用率（%）"))
						C = li.getFactor();
					if (li.getType_y().equals("内墙VOCs排放系数（t/t）"))
						EF1 = li.getFactor();
					if (li.getType_y().equals("外墙面积换算系数"))
						D = li.getFactor();
					if (li.getType_y().equals("单位外墙面积涂料用量（吨/平方米）"))
						E = li.getFactor();
					if (li.getType_y().equals("外墙涂料利用率（%）"))
						F = li.getFactor();
					if (li.getType_y().equals("外墙涂料VOCs排放系数（t/t）"))
						EF2 = li.getFactor();
					if (li.getType_y().equals("沥青用量转换系数1（沥青铺路高度m）"))
						QA = li.getFactor();
					if (li.getType_y().equals("沥青用量转换系数2（沥青密度kg/m3）"))
						QB = li.getFactor();
					if (li.getType_y().equals("单位沥青使用量VOCs排放系数（kg/kg沥青）"))
						QEF = li.getFactor();
				}
			}

			List<Construction> construclist = null;
	           if(typeid==1){
	        	   construclist = theService.getConstruction2(fillyear);
				}else if(typeid==2){
					construclist = theService.getConstruction(fillyear);
				}
			for (Construction cli : construclist) {
				GovStat csumgov = new GovStat();

				double s = cli.getCompleteArea() * A * B * EF1 / C;
				double sc = cli.getCompleteArea() * D * E * EF2 / F;
				double csum = cli.getAsphaltRoadArea() * QA * QB * QEF * 0.001;

				if (C > 0 && F > 0)
					csum = csum + s + sc;
				else if (C > 0 && F <= 0)
					csum = csum + s;
				else if (C <= 0 && F > 0)
					csum = csum + sc;

				csumgov.setPollutantId(pos);
				csumgov.setProvince(1);
				csumgov.setCity(cli.getCity());
				csumgov.setFillyear(cli.getFillyear());
				csumgov.setStattype("build");
				csumgov.setStatvalue(csum);
				csumgov.setStat_exp("E=内墙涂料VOCs排放量+外墙涂料VOCs排放量+沥青铺路挥发性VOCs排放量");
				csumgov.setStat_valtype("计算房屋建筑污染排放");
				csumgov.setStat_factor("内墙面积换算系数:" + A + "单位内墙面积涂料用量（吨/平方米）:"
						+ B + "内墙涂料利用率（%）:" + C + "内墙VOCs排放系数（t/t）:" + EF1
						+ "外墙面积换算系数:" + D + "单位外墙面积涂料用量（吨/平方米）:" + E
						+ "外墙涂料利用率（%）:" + F + "外墙涂料VOCs排放系数（t/t）:" + EF2
						+ "沥青用量转换系数1（沥青铺路高度m）:" + QA + "沥青用量转换系数2（沥青密度kg/m3）:"
						+ QB + "单位沥青使用量VOCs排放系数（kg/kg沥青）:" + QEF);
				csumgov.setStat_value("E=" + cli.getCompleteArea() + "*" + A
						+ "*" + B + "*" + EF1 + "/" + C + "+"
						+ cli.getCompleteArea() + "*" + D + "*" + E + "*" + EF2
						+ "/" + F + "+" + cli.getAsphaltRoadArea() + "*" + QA
						+ "*" + QB + "*" + QEF + "*" + 0.001);
				pes.add(csumgov);
			}

			if (pes.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:pes) {
					if(count > 0 && count % 100 == 0) {
						 if(typeid==1){
				        	   theService.addGovStat2(newlist);
							}else if(typeid==2){
								theService.addGovStat(newlist);
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
			        	   theService.addGovStat2(newlist);
						}else if(typeid==2){
							theService.addGovStat(newlist);
						}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
