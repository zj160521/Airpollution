package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Pollutant;
import com.xf.entity.Static;
import com.xf.entity.gov.AnimalsFarm;
import com.xf.entity.gov.AnimalsParam;
import com.xf.entity.gov.AnimalsWild;
import com.xf.entity.gov.CanyinStat;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.entity.gov.Plane;
import com.xf.entity.gov.VehicleRepairing;
import com.xf.service.PollutantService;
import com.xf.service.StaticService;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsParamService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.GovFactorService;
import com.xf.service.gov.PlaneService;
import com.xf.service.gov.VehicleRepairingService;
import com.xf.service.stat.NumerationService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/calculate")
public class Calculate {

	@RequestMapping(value = "/value", method = RequestMethod.GET)
	@ResponseBody
	public void cal(NumerationService theService, GovFactorService gfservice,
			VehicleRepairingService vrservice, PlaneService planeservice,
			PollutantService pollservice, CanyinStatService cyservice,
			AnimalsFarmService afservice, AnimalsWildService awservice,
			AnimalsParamService apservice, StaticService staticservice, int fillYear,int typeid) {
		double f1 = 0;
		double f2 = 0;
		double f3 = 0;
		double value = 0;
		Set<String> set = new LinkedHashSet<String>();
		Set<String> set2 = new LinkedHashSet<String>();
		Set<Integer> set3 = new HashSet<Integer>();
		Set<Integer> set4 = new HashSet<Integer>();
		Set<Integer> set5 = new HashSet<Integer>();
		Set<Integer> set6 = new HashSet<Integer>();
		List<GovStat> clist = new ArrayList<GovStat>();
		List<GovStat> clistgarage = new ArrayList<GovStat>();
		List<GovStat> clistairplane = new ArrayList<GovStat>();
		List<GovStat> clistrestaurant = new ArrayList<GovStat>();

		// 汽修
		String typename = "garage";
		List<GovFactor> govfactor = gfservice.getByTypeName(typename);
		if (govfactor != null) {
			for (GovFactor gli : govfactor) {
				if (gli.getType_x().equals("EF1"))
					f1 = gli.getFactor();
				if (gli.getType_x().equals("EF2"))
					f2 = gli.getFactor();
				if (gli.getType_x().equals("EF3"))
					f3 = gli.getFactor();
			}
		}

		List<VehicleRepairing> vrlist = null;
		if(typeid==1){
			vrlist = vrservice.getAll2(fillYear);
		}else if(typeid==2){
			vrlist = vrservice.getAll(fillYear);
		}
		for (VehicleRepairing vr : vrlist) {
			for (int i = 0; i < 3; i++) {
				value = 0;
				GovStat gs = new GovStat();
				gs.setCity(vr.getCity());
				gs.setFillyear(vr.getFillyear());
				gs.setPollutantId(9);
				gs.setProvince(vr.getProvince());
				gs.setTown(vr.getTown());
				gs.setStattype(typename);
				gs.setStat_exp("E=汽修企业数*EF");
				gs.setStat_valtype("计算汽修企业VOCs排放量");
				if (i == 0) {
					gs.setStattype2(19001);
					value = vr.getClass1Number() * f1;
					gs.setStatvalue(value);
					gs.setStat_factor(Double.toString(f1));
					gs.setStat_value(Integer.toString(vr.getClass1Number())
							+ "*" + Double.toString(f1));
				}
				if (i == 1) {
					gs.setStattype2(19002);
					value = vr.getClass2Number() * f2;
					gs.setStatvalue(value);
					gs.setStat_factor(Double.toString(f2));
					gs.setStat_value(Integer.toString(vr.getClass2Number())
							+ "*" + Double.toString(f2));
				}
				if (i == 2) {
					gs.setStattype2(19003);
					value = vr.getClass3Number() * f3;
					gs.setStatvalue(value);
					gs.setStat_factor(Double.toString(f3));
					gs.setStat_value(Integer.toString(vr.getClass3Number())
							+ "*" + Double.toString(f3));
				}
				if (value > 0) {
					clistgarage.add(gs);
				}
			}

		}

		// 飞机
		typename = "airplane";
		f1 = 0;
		f2 = 0;
		value = 0;
		govfactor = gfservice.getByTypeName(typename);
		List<Plane> plist = null;
		if(typeid==1){
			plist = planeservice.getAll2(fillYear);
		}else if(typeid==2){
			plist = planeservice.getAll(fillYear);
		}
		for (GovFactor gf : govfactor) {
			set.add(gf.getType_y());
		}
		if (govfactor != null) {
			for (Plane p : plist) {
				for (String poll : set) {
					GovStat gs = new GovStat();
					Pollutant po = pollservice.getByName(poll);
					if (po != null) {
						for (GovFactor gli : govfactor) {
							if (gli.getType_y().equals(poll)) {
								f1 = gli.getFactor();
								gs.setCity(p.getCity());
								gs.setFillyear(p.getFillyear());
								gs.setPollutantId(po.getId());
								gs.setProvince(p.getProvince());
								gs.setTown(p.getTown());
								gs.setStattype(typename);
								gs.setStattype3(p.getAirport());
								gs.setStat_exp("E=(CLTO×EF)×0.001");
								gs.setStat_valtype("计算飞机污染物");
								gs.setStat_factor(Double.toString(f1));
								gs.setStat_value(Double.toString(f1) + "*"
										+ Integer.toString(p.getNumbers())
										+ "* 0.001 / 2");
								value = f1 * p.getNumbers() * 0.001 / 2;
								gs.setStatvalue(value);
								if (value > 0) {
									clistairplane.add(gs);
								}

							}
						}

					}
				}
			}
		}

		// 餐饮
		typename = "restaurant";
		f1 = 0;
		f2 = 0;
		value = 0;
		set.clear();
		set2.clear();
		govfactor = gfservice.getByTypeName(typename);
		List<CanyinStat> cglist = null;
		if(typeid==1){
			cglist = cyservice.statAll2(fillYear);
		}else if(typeid==2){
			cglist = cyservice.statAll(fillYear);
		}
		for (GovFactor gf : govfactor) {
			set.add(gf.getType_y());
		}
		for (GovFactor gf : govfactor) {
			if (gf.getType_x2() == null || gf.getType_x2() == "") {
				set2.add(gf.getType_x());
			} else {
				set2.add(gf.getType_x2());
			}
		}

		for (CanyinStat cy : cglist) {

			for (String poll : set) {
				Pollutant po = pollservice.getByName(poll);
				value = 0;
				GovStat gs = new GovStat();
				gs.setCity(cy.getCity());
				gs.setFillyear(cy.getFillyear());
				gs.setPollutantId(po.getId());
				gs.setProvince(cy.getProvince());
				gs.setTown(cy.getTown());
				gs.setStattype(typename);
				gs.setStat_exp("E=餐馆数量*排放系数*校正因子");
				gs.setStat_valtype("餐饮污染物");
				String factor = "";
				String process = "";
				if (po != null) {
					for (String cg : set2) {
						if (cg.equals("特大型")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getCanguan_huge()+"*"+f1+"*"+f2+"+";
							value += cy.getCanguan_huge() * f1 * f2;
						}

						if (cg.equals("大型")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getCanguan_big()+"*"+f1+"*"+f2+"+";
							value += cy.getCanguan_big() * f1 * f2;
						}

						if (cg.equals("中餐馆")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getCanguan_mid()+"*"+f1+"*"+f2+"+";
							value += cy.getCanguan_mid() * f1 * f2;
						}

						if (cg.equals("小餐馆")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getCanguan_small()+"*"+f1+"*"+f2+"+";
							value += cy.getCanguan_small() * f1 * f2;
						}

						if (cg.equals("小吃店")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getSnack()+"*"+f1+"*"+f2+"+";
							value += cy.getSnack() * f1 * f2;
						}

						if (cg.equals("快餐店")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getFastfood()+"*"+f1+"*"+f2+"+";
							value += cy.getFastfood() * f1 * f2;
						}

						if (cg.equals("鲜奶吧")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getMilk()+"*"+f1+"*"+f2+"+";
							value += cy.getMilk() * f1 * f2;
						}

						if (cg.equals("饮品店")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getDrink()+"*"+f1+"*"+f2+"+";
							value += cy.getDrink() * f1 * f2;
						}

						if (cg.equals("事业单位食堂")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getShitang_shiye()+"*"+f1+"*"+f2+"+";
							value += cy.getShitang_shiye() * f1 * f2;
						}

						if (cg.equals("学校食堂")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getShitang_school()+"*"+f1+"*"+f2+"+";
							value += cy.getShitang_school() * f1 * f2;
						}

						if (cg.equals("建筑工地食堂")) {
							for (GovFactor gf : govfactor) {
								if (gf.getType_y().equals(poll)
										&& (gf.getType_x2() == null || gf
												.getType_x2() == "")
										&& gf.getType_x().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
								if (gf.getType_y().equals(poll)
										&& gf.getType_x2() != null
										&& gf.getType_x2() != ""
										&& gf.getType_x2().equals(cg)) {
									if (gf.getType_y2().equals("排放系数"))
										f1 = gf.getFactor();
									if (gf.getType_y2().equals("校正因子"))
										f2 = gf.getFactor();

								}
							}
							factor = factor.concat(" "+cg).concat("系数：")+f1;
							factor = factor.concat(" "+cg).concat("因子：")+f2;
							process = process+cy.getShitang_gongdi()+"*"+f1+"*"+f2;
							value += cy.getShitang_gongdi() * f1 * f2;
						}

					}
				}

				gs.setStat_factor(factor);
				gs.setStat_value(process);
				gs.setStatvalue(value);
//				for (GovStat gs2 : clist) {
//					if (gs2.getCity() == gs.getCity()
//							&& gs2.getFillyear() == gs.getFillyear()
//							&& gs2.getPollutantId() == gs.getPollutantId()
//							&& gs2.getProvince() == gs.getProvince()
//							&& gs2.getTown() == gs.getTown()
//							&& gs2.getStattype().equals(gs.getStattype()))
//						gs.setStatvalue(gs.getStatvalue() + gs2.getStatvalue());
//				}
				if (value > 0) {
					clistrestaurant.add(gs);
				}

			}
		}

		// 畜禽养殖
		typename = "animal1";//第一张畜禽因子配置表
		set.clear();
		set2.clear();
		
		//规模基础数据
		List<AnimalsFarm> aflist = null;
		if(typeid==1){
			aflist = afservice.statAll2(fillYear);
		}else if(typeid==2){
			aflist = afservice.statAll(fillYear);
		}
		//散养基础数据
		List<AnimalsWild> awlist = null;
        if(typeid==1){
        	awlist = awservice.statAll2(fillYear);
		}else if(typeid==2){
			awlist = awservice.statAll(fillYear);
		}
		//城市数据获取
		for (AnimalsFarm af : aflist) {
			set3.add(af.getCity());
			set4.add(af.getTown());
		}
		for (AnimalsWild aw : awlist) {
			set5.add(aw.getCity());
			set6.add(aw.getTown());
		}
		
        //第一张因子表因子获取
		govfactor = gfservice.getByTypeName(typename);
		double fmuzhu = 0;
		double fma = 0;
		double fdanji = 0;
		double fdanya = 0;
		double fdane = 0;
		double frouji = 0;
		double frouya = 0;
		double froue = 0;
		double ftu = 0;
		double frnd = 0;
		double fnnd = 0;
		double fsyd = 0;
		double fmyd = 0;
		double frzx = 0;
		double fmuzhu2 = 0;
		double fma2 = 0;
		double fdanji2 = 0;
		double fdanya2 = 0;
		double fdane2 = 0;
		double frouji2 = 0;
		double frouya2 = 0;
		double froue2 = 0;
		double ftu2 = 0;
		double frnd2 = 0;
		double fnnd2 = 0;
		double fsyd2 = 0;
		double fmyd2 = 0;
		double frzx2 = 0;
		for (GovFactor gf : govfactor) {
			if (gf.getType_y().equals("规模化养殖")) {
				if (gf.getType_y2().equals("母猪"))
					fmuzhu = gf.getFactor();
				else if (gf.getType_y2().equals("马"))
					fma = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鸡"))
					fdanji = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鸭"))
					fdanya = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鹅"))
					fdane = gf.getFactor();
				else if (gf.getType_y2().equals("肉鸡"))
					frouji = gf.getFactor();
				else if (gf.getType_y2().equals("肉鸭"))
					frouya = gf.getFactor();
				else if (gf.getType_y2().equals("肉鹅"))
					froue = gf.getFactor();
				else if (gf.getType_y2().equals("兔"))
					ftu = gf.getFactor();
				else if (gf.getType_y2().equals("肉牛>=1年"))
					frnd = gf.getFactor();
				else if (gf.getType_y2().equals("奶牛>=1年"))
					fnnd = gf.getFactor();
				else if (gf.getType_y2().equals("山羊>=1年"))
					fsyd = gf.getFactor();
				else if (gf.getType_y2().equals("绵羊>=1年"))
					fmyd = gf.getFactor();
				else if (gf.getType_y2().equals("肉猪<=75天"))
					frzx = gf.getFactor();
			} else if (gf.getType_y().equals("散养")) {
				if (gf.getType_y2().equals("母猪"))
					fmuzhu2 = gf.getFactor();
				else if (gf.getType_y2().equals("马"))
					fma2 = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鸡"))
					fdanji2 = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鸭"))
					fdanya2 = gf.getFactor();
				else if (gf.getType_y2().equals("蛋鹅"))
					fdane2 = gf.getFactor();
				else if (gf.getType_y2().equals("肉鸡"))
					frouji2 = gf.getFactor();
				else if (gf.getType_y2().equals("肉鸭"))
					frouya2 = gf.getFactor();
				else if (gf.getType_y2().equals("肉鹅"))
					froue2 = gf.getFactor();
				else if (gf.getType_y2().equals("兔"))
					ftu2 = gf.getFactor();
				else if (gf.getType_y2().equals("肉牛>=1年"))
					frnd2 = gf.getFactor();
				else if (gf.getType_y2().equals("奶牛>=1年"))
					fnnd2 = gf.getFactor();
				else if (gf.getType_y2().equals("山羊>=1年"))
					fsyd2 = gf.getFactor();
				else if (gf.getType_y2().equals("绵羊>=1年"))
					fmyd2 = gf.getFactor();
				else if (gf.getType_y2().equals("肉猪<=75天"))
					frzx2 = gf.getFactor();
			}

		}
		
		//畜禽种类获取
		Static sta = new Static();
		sta.setGroupid(24);
		List<Static> animalList = staticservice.getGroup(sta);
		//第二张因子表获取
		List<GovFactor> GovFactorList = gfservice.getAnimal2Factor();
		//第二张因子表因子定义
		double factor301 = 0;
		double factor302 = 0;
		double factor303 = 0;
		double factor304 = 0;
		double factor305 = 0;
		double factor306 = 0;
		double factor307 = 0;
		double factor308 = 0;
		double factor309 = 0;
		double factor3010 = 0;
		double factor401 = 0;
		double factor402 = 0;
		double factor403 = 0;
		double factor404 = 0;
		
		//参数表获取
		List<AnimalsParam> paraList = apservice.getAll();

//		String test = null;
//		String va = null;
		// 规模化养殖
		if (aflist.size() > 0) {
			for (Integer city : set3) {
					for (Static an : animalList) {
						GovStat gs = new GovStat();
						gs.setCity(city);
						gs.setStattype2(an.getId());
						
						value = 0;
						for (AnimalsFarm af : aflist) {
							if (af.getCity() == city) {
								gs.setFillyear(af.getFillyear());
								gs.setPollutantId(10);
								gs.setProvince(af.getProvince());
								gs.setStattype("animal");
								if (an.getId() == 18001) {
									double ef = 0;
									ef = af.getHorse() * fma / 1000;
									value += ef;
								}
								else if (an.getId() == 18002) {
									double ef = 0;
									double ef2 = 0;
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor306 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor307 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor308 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor309 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor3010 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor402 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor403 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor404 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									AnimalsParam para2 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("规模化养殖")
												&& para.getStattype().equals("肉牛<1年")){
											para1 = para;
										}else if(para.getFeedtype().equals("规模化养殖")
												&& para.getStattype().equals("奶牛<1年")){
											para2 = para;
										}
									}
									if (af.getBeefcycle() >= 365) {
										ef = af.getBeef() * frnd / 1000;
									} else {
										int am = af.getBeef();
										int cyc = af.getBeefcycle();
										double TANin = getTANin(am, cyc,
												"规模化养殖", "肉牛<1年", apservice);
										double TANout = getTANout(am, cyc,
												"规模化养殖", "肉牛<1年", apservice);
										
										double Ehw = TANout
												* factor301 * 1.214
												/ 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1
														.getXliquid()) * 1.214
												/ 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1
														.getXliquid()) - getENjss(
													"规模化养殖", "肉牛<1年",
													apservice, gfservice, TANin))
												* factor401
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"规模化养殖", "肉牛<1年",
													apservice, gfservice, TANin))
												* factor402
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para1.getXliquid())
												- getENjss("规模化养殖",
														"肉牛<1年", apservice,
														gfservice, TANin)
												- getENccs("规模化养殖",
														"肉牛<1年", apservice,
														gfservice, TANin) - getENsss(
													"规模化养殖", "肉牛<1年",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("规模化养殖",
														"肉牛<1年", apservice,
														gfservice, TANin)
												- getENccl("规模化养殖",
														"肉牛<1年", apservice,
														gfservice, TANin) - getENssl(
													"规模化养殖", "肉牛<1年",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}

									if (af.getCowcycle() >= 365) {
										ef2 = af.getCow() * fnnd / 1000;
									} else {
										int am = af.getCow();
										int cyc = af.getCowcycle();
										double TANin = getTANin(am, cyc,
												"规模化养殖", "奶牛<1年", apservice);
										double TANout = getTANout(am, cyc,
												"规模化养殖", "奶牛<1年", apservice);
										double Ehw = TANout
												* factor306 * 1.214
												/ 1000;
										double EjsS = TANin
												* factor307
												* (1 - para2
														.getXliquid()) * 1.214
												/ 1000;
										double EjsL = TANin
												* factor308
												* para2.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para2
														.getXliquid()) - getENjss(
													"规模化养殖", "奶牛<1年",
													apservice, gfservice, TANin))
												* factor403
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para2.getXliquid() - getENjsl(
													"规模化养殖", "奶牛<1年",
													apservice, gfservice, TANin))
												* factor404
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para2
														.getXliquid())
												- getENjss("规模化养殖",
														"奶牛<1年", apservice,
														gfservice, TANin)
												- getENccs("规模化养殖",
														"奶牛<1年", apservice,
														gfservice, TANin) - getENsss(
													"规模化养殖", "奶牛<1年",
													apservice, gfservice, TANin))
												* (1 - para2
														.getRfeed())
												* factor309
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para2.getXliquid()
												- getENjsl("规模化养殖",
														"奶牛<1年", apservice,
														gfservice, TANin)
												- getENccl("规模化养殖",
														"奶牛<1年", apservice,
														gfservice, TANin) - getENssl(
													"规模化养殖", "奶牛<1年",
													apservice, gfservice, TANin))
												* (1 - para2
														.getRfeed())
												* factor3010
												* 1.214
												/ 1000;
										ef2 = ef2 + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value = value + ef + ef2;
//									if(city == 2 ){
//										test = "="+test+" ***ef"+ef+"  ef2"+ef2;
//										va = value+"";
//									}
								}
								else if (an.getId() == 18003) {
									double ef = 0;
									double ef2 = 0;
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor306 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor307 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor308 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor309 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor3010 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor402 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor403 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor404 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									AnimalsParam para2 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("规模化养殖")
												&& para.getStattype().equals("山羊<1年")){
											para1 = para;
										}else if(para.getFeedtype().equals("规模化养殖")
												&& para.getStattype().equals("绵羊<1年")){
											para2 = para;
										}
									}
									if (af.getGoatcycle() >= 365) {
										ef = af.getGoat() * fsyd / 1000;
									} else {
										int am = af.getGoat();
										int cyc = af.getGoatcycle();
										double TANin = getTANin(am, cyc,
												"规模化养殖", "山羊<1年", apservice);
										double TANout = getTANout(am, cyc,
												"规模化养殖", "山羊<1年", apservice);
										double Ehw = TANout
												* factor301 * 1.214
												/ 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1
														.getXliquid()) * 1.214
												/ 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1
														.getXliquid()) - getENjss(
													"规模化养殖", "山羊<1年",
													apservice, gfservice, TANin))
												* factor401
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"规模化养殖", "山羊<1年",
													apservice, gfservice, TANin))
												* factor402
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para1
														.getXliquid())
												- getENjss("规模化养殖",
														"山羊<1年", apservice,
														gfservice, TANin)
												- getENccs("规模化养殖",
														"山羊<1年", apservice,
														gfservice, TANin) - getENsss(
													"规模化养殖", "山羊<1年",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("规模化养殖",
														"山羊<1年", apservice,
														gfservice, TANin)
												- getENccl("规模化养殖",
														"山羊<1年", apservice,
														gfservice, TANin) - getENssl(
													"规模化养殖", "山羊<1年",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									if (af.getSheepcycle() >= 365) {
										ef2 = af.getSheep() * fmyd / 1000;
									} else {
										int am = af.getSheep();
										int cyc = af.getSheepcycle();
										double TANin = getTANin(am, cyc,
												"规模化养殖", "绵羊<1年", apservice);
										double TANout = getTANout(am, cyc,
												"规模化养殖", "绵羊<1年", apservice);
										double Ehw = TANout
												* factor306 * 1.214
												/ 1000;
										double EjsS = TANin
												* factor307
												* (1 - para2
														.getXliquid()) * 1.214
												/ 1000;
										double EjsL = TANin
												* factor308
												* para2.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para2
														.getXliquid()) - getENjss(
													"规模化养殖", "绵羊<1年",
													apservice, gfservice, TANin))
												* factor403
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para2.getXliquid() - getENjsl(
													"规模化养殖", "绵羊<1年",
													apservice, gfservice, TANin))
												* factor404
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para2
														.getXliquid())
												- getENjss("规模化养殖",
														"绵羊<1年", apservice,
														gfservice, TANin)
												- getENccs("规模化养殖",
														"绵羊<1年", apservice,
														gfservice, TANin) - getENsss(
													"规模化养殖", "绵羊<1年",
													apservice, gfservice, TANin))
												* (1 - para2
														.getRfeed())
												* factor309
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para2.getXliquid()
												- getENjsl("规模化养殖",
														"绵羊<1年", apservice,
														gfservice, TANin)
												- getENccl("规模化养殖",
														"绵羊<1年", apservice,
														gfservice, TANin) - getENssl(
													"规模化养殖", "绵羊<1年",
													apservice, gfservice, TANin))
												* (1 - para2
														.getRfeed())
												* factor3010
												* 1.214
												/ 1000;
										ef2 = ef2 + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value = value + ef + ef2;
								}
								else if (an.getId() == 18004) {
									double ef = 0;
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("规模化养殖")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor402 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("规模化养殖")
												&& para.getStattype().equals("肉猪>75天")){
											para1 = para;
										}
									}
									if (af.getPigcycle() <= 75) {
										ef = af.getPig() * frzx / 1000;
									} else {
										int am = af.getPig();
										int cyc = af.getPigcycle();
										double TANin = getTANin(am, cyc,
												"规模化养殖", "肉猪>75天", apservice);
										double TANout = getTANout(am, cyc,
												"规模化养殖", "肉猪>75天", apservice);
										double Ehw = TANout
												* factor301 * 1.214
												/ 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1
														.getXliquid()) * 1.214
												/ 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1
														.getXliquid()) - getENjss(
													"规模化养殖", "肉猪>75天",
													apservice, gfservice, TANin))
												* factor401
												* 1.214 / 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"规模化养殖", "肉猪>75天",
													apservice, gfservice, TANin))
												* factor402
												* 1.214 / 1000;
										double EsfS = (TANin
												* (1 - para1
														.getXliquid())
												- getENjss("规模化养殖",
														"肉猪>75天", apservice,
														gfservice, TANin)
												- getENccs("规模化养殖",
														"肉猪>75天", apservice,
														gfservice, TANin) - getENsss(
													"规模化养殖", "肉猪>75天",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("规模化养殖",
														"肉猪>75天", apservice,
														gfservice, TANin)
												- getENccl("规模化养殖",
														"肉猪>75天", apservice,
														gfservice, TANin) - getENssl(
													"规模化养殖", "肉猪>75天",
													apservice, gfservice, TANin))
												* (1 - para1
														.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value += ef;
								}
								else if (an.getId() == 18005) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getDuck() * frouya / 1000;
									ef2 = af.getLayingduck() * fdanya / 1000;
									value = value+ef+ef2;
								}
								else if (an.getId() == 18006) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getGoose() * froue / 1000;
									ef2 = af.getLayinggoose() * fdane / 1000;
									value = value+ef+ef2;
								}
								else if (an.getId() == 18007) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getChicken() * frouji / 1000;
									ef2 = af.getHen() * fdanji / 1000;
									value = value+ef+ef2;
								}
								else if (an.getId() == 18008) {
									double ef = 0;
									ef = af.getSow() * fmuzhu / 1000;
									value += ef;
								}
								else if (an.getId() == 18009) {
									double ef = 0;
									ef = af.getRabbit() * ftu / 1000;
									value += ef;
								}

							}

						}
						gs.setStatvalue(value);
						gs.setStattype3("规模化养殖");
						
						
						
						if (value > 0) {
							clist.add(gs);
						}
					}
			}
		}

		// 散养
		if (awlist.size() > 0) {
			for (Integer city : set5) {
					for (Static an : animalList) {
						GovStat gs = new GovStat();
						gs.setCity(city);
						gs.setStattype2(an.getId());
						value = 0;
						for (AnimalsWild af : awlist) {
							if (af.getCity() == city) {
								gs.setFillyear(af.getFillyear());
								gs.setPollutantId(10);
								gs.setProvince(af.getProvince());
								gs.setStattype("animal");
								if (an.getId() == 18001) {
									double ef = 0;
									ef = af.getHorse() * fma2 / 1000;
									value += ef;
								}
								if (an.getId() == 18002) {
									double ef = 0;
									double ef2 = 0;
									
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor306 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor307 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor308 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor309 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor3010 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉牛<1年")){
											factor402 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor403 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("奶牛<1年")){
											factor404 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									AnimalsParam para2 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("散养")
												&& para.getStattype().equals("肉牛<1年")){
											para1 = para;
										}else if(para.getFeedtype().equals("散养")
												&& para.getStattype().equals("奶牛<1年")){
											para2 = para;
										}
									}
									if (af.getBeefcycle() >= 365) {
										ef = af.getBeef() * frnd2 / 1000;
									} else {
										int am = af.getBeef();
										int cyc = af.getBeefcycle();
										double TANin = getTANin(am, cyc, "散养",
												"肉牛<1年", apservice);
										double TANout = getTANout(am, cyc,
												"散养", "肉牛<1年", apservice);
										double Ehw = TANout
												* factor301
												* 1.214 / 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1.getXliquid())
												* 1.214 / 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1.getXliquid()) - getENjss(
													"散养", "肉牛<1年",
													apservice, gfservice, TANin))
												* factor401
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"散养", "肉牛<1年",
													apservice, gfservice, TANin))
												* factor402
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para1.getXliquid())
												- getENjss("散养",
														"肉牛<1年", apservice,
														gfservice, TANin)
												- getENccs("散养",
														"肉牛<1年", apservice,
														gfservice, TANin) - getENsss(
													"散养", "肉牛<1年",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("散养",
														"肉牛<1年", apservice,
														gfservice, TANin)
												- getENccl("散养",
														"肉牛<1年", apservice,
														gfservice, TANin) - getENssl(
													"散养", "肉牛<1年",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}

									if (af.getCowcycle() >= 365) {
										ef2 = af.getCow() * fnnd2 / 1000;
									} else {
										int am = af.getCow();
										int cyc = af.getCowcycle();
										double TANin = getTANin(am, cyc, "散养",
												"奶牛<1年", apservice);
										double TANout = getTANout(am, cyc,
												"散养", "奶牛<1年", apservice);
										double Ehw = TANout
												* factor306
												* 1.214 / 1000;
										double EjsS = TANin
												* factor307
												* (1 - para2.getXliquid())
												* 1.214 / 1000;
										double EjsL = TANin
												* factor308
												* para2.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para2.getXliquid()) - getENjss(
													"散养", "奶牛<1年",
													apservice, gfservice, TANin))
												* factor403
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para2.getXliquid() - getENjsl(
													"散养", "奶牛<1年",
													apservice, gfservice, TANin))
												* factor404
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para2.getXliquid())
												- getENjss("散养",
														"奶牛<1年", apservice,
														gfservice, TANin)
												- getENccs("散养",
														"奶牛<1年", apservice,
														gfservice, TANin) - getENsss(
													"散养", "奶牛<1年",
													apservice, gfservice, TANin))
												* (1 - para2.getRfeed())
												* factor309
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para2.getXliquid()
												- getENjsl("散养",
														"奶牛<1年", apservice,
														gfservice, TANin)
												- getENccl("散养",
														"奶牛<1年", apservice,
														gfservice, TANin) - getENssl(
													"散养", "奶牛<1年",
													apservice, gfservice, TANin))
												* (1 - para2.getRfeed())
												* factor3010
												* 1.214
												/ 1000;
										ef2 = ef2 + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value = value + ef + ef2;
								}
								if (an.getId() == 18003) {
									double ef = 0;
									double ef2 = 0;
									
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor306 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor307 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor308 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor309 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor3010 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("山羊<1年")){
											factor402 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor403 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("绵羊<1年")){
											factor404 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									AnimalsParam para2 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("散养")
												&& para.getStattype().equals("山羊<1年")){
											para1 = para;
										}else if(para.getFeedtype().equals("散养")
												&& para.getStattype().equals("绵羊<1年")){
											para2 = para;
										}
									}
									if (af.getGoatcycle() >= 365) {
										ef = af.getGoat() * fsyd2 / 1000;
									} else {
										int am = af.getGoat();
										int cyc = af.getGoatcycle();
										double TANin = getTANin(am, cyc, "散养",
												"山羊<1年", apservice);
										double TANout = getTANout(am, cyc,
												"散养", "山羊<1年", apservice);
										double Ehw = TANout
												* factor301
												* 1.214 / 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1.getXliquid())
												* 1.214 / 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1.getXliquid()) - getENjss(
													"散养", "山羊<1年",
													apservice, gfservice, TANin))
												* factor401
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"散养", "山羊<1年",
													apservice, gfservice, TANin))
												* factor402
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para1.getXliquid())
												- getENjss("散养",
														"山羊<1年", apservice,
														gfservice, TANin)
												- getENccs("散养",
														"山羊<1年", apservice,
														gfservice, TANin) - getENsss(
													"散养", "山羊<1年",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("散养",
														"山羊<1年", apservice,
														gfservice, TANin)
												- getENccl("散养",
														"山羊<1年", apservice,
														gfservice, TANin) - getENssl(
													"散养", "山羊<1年",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									if (af.getSheepcycle() >= 365) {
										ef2 = af.getSheep() * fmyd2 / 1000;
									} else {
										int am = af.getSheep();
										int cyc = af.getSheepcycle();
										double TANin = getTANin(am, cyc, "散养",
												"绵羊<1年", apservice);
										double TANout = getTANout(am, cyc,
												"散养", "绵羊<1年", apservice);
										double Ehw = TANout
												* factor306
												* 1.214 / 1000;
										double EjsS = TANin
												* factor307
												* (1 - para2.getXliquid())
												* 1.214 / 1000;
										double EjsL = TANin
												* factor308
												* para2.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para2.getXliquid()) - getENjss(
													"散养", "绵羊<1年",
													apservice, gfservice, TANin))
												* factor403
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para2.getXliquid() - getENjsl(
													"散养", "绵羊<1年",
													apservice, gfservice, TANin))
												* factor404
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para2.getXliquid())
												- getENjss("散养",
														"绵羊<1年", apservice,
														gfservice, TANin)
												- getENccs("散养",
														"绵羊<1年", apservice,
														gfservice, TANin) - getENsss(
													"散养", "绵羊<1年",
													apservice, gfservice, TANin))
												* (1 - para2.getRfeed())
												* factor309
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para2.getXliquid()
												- getENjsl("散养",
														"绵羊<1年", apservice,
														gfservice, TANin)
												- getENccl("散养",
														"绵羊<1年", apservice,
														gfservice, TANin) - getENssl(
													"散养", "绵羊<1年",
													apservice, gfservice, TANin))
												* (1 - para2.getRfeed())
												* factor3010
												* 1.214
												/ 1000;
										ef2 = ef2 + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value = value + ef + ef2;
								}
								if (an.getId() == 18004) {
									double ef = 0;
									
									for(GovFactor govFactor : GovFactorList){
										if(govFactor.getType_x().equals("户外") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor301 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor302 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("圈舍-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor303 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-固态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor304 = govFactor.getFactor();
										}
										else if(govFactor.getType_x().equals("施肥-液态") 
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor305 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-固态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor401 = govFactor.getFactor();
										}else if(govFactor.getType_x().equals("储存-液态")
												&& govFactor.getType_x2().equals("NH3")
												&& govFactor.getType_y().equals("散养")
												&& govFactor.getType_y2().equals("肉猪>75天")){
											factor402 = govFactor.getFactor();
										}
										
									}
									AnimalsParam para1 = null;
									for(AnimalsParam para:paraList){
										if(para.getFeedtype().equals("散养")
												&& para.getStattype().equals("肉猪>75天")){
											para1 = para;
										}
									}
									if (af.getPigcycle() <= 75) {
										ef = af.getPig() * frzx2 / 1000;
									} else {
										int am = af.getPig();
										int cyc = af.getPigcycle();
										double TANin = getTANin(am, cyc, "散养",
												"肉猪>75天", apservice);
										double TANout = getTANout(am, cyc,
												"散养", "肉猪>75天", apservice);
										double Ehw = TANout
												* factor301
												* 1.214 / 1000;
										double EjsS = TANin
												* factor302
												* (1 - para1.getXliquid())
												* 1.214 / 1000;
										double EjsL = TANin
												* factor303
												* para1.getXliquid()
												* 1.214 / 1000;
										double EccS = (TANin
												* (1 - para1.getXliquid()) - getENjss(
													"散养", "肉猪>75天",
													apservice, gfservice, TANin))
												* factor401
												* 1.214
												/ 1000;
										double EccL = (TANin
												* para1.getXliquid() - getENjsl(
													"散养", "肉猪>75天",
													apservice, gfservice, TANin))
												* factor402
												* 1.214
												/ 1000;
										double EsfS = (TANin
												* (1 - para1.getXliquid())
												- getENjss("散养",
														"肉猪>75天", apservice,
														gfservice, TANin)
												- getENccs("散养",
														"肉猪>75天", apservice,
														gfservice, TANin) - getENsss(
													"散养", "肉猪>75天",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor304
												* 1.214
												/ 1000;
										double EsfL = (TANin
												* para1.getXliquid()
												- getENjsl("散养",
														"肉猪>75天", apservice,
														gfservice, TANin)
												- getENccl("散养",
														"肉猪>75天", apservice,
														gfservice, TANin) - getENssl(
													"散养", "肉猪>75天",
													apservice, gfservice, TANin))
												* (1 - para1.getRfeed())
												* factor305
												* 1.214
												/ 1000;
										ef = ef + Ehw + EjsS + EjsL + EccS
												+ EccL + EsfS + EsfL;
									}
									value += ef;
								}
								if (an.getId() == 18005) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getDuck() * frouya2 / 1000;
									ef2 = af.getLayingduck() * fdanya2 / 1000;
									value = value+ef+ef2;
								}
								if (an.getId() == 18006) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getGoose() * froue2 / 1000;
									ef2 = af.getLayinggoose() * fdane2 / 1000;
									value = value+ef+ef2;
								}
								if (an.getId() == 18007) {
									double ef = 0;
									double ef2 = 0;
									ef = af.getChicken() * frouji2 / 1000;
									ef2 = af.getHen() * fdanji2 / 1000;
									value = value+ef+ef2;
								}
								if (an.getId() == 18008) {
									double ef = 0;
									ef = af.getSow() * fmuzhu2 / 1000;
									value += ef;
								}
								if (an.getId() == 18009) {
									double ef = 0;
									ef = af.getRabbit() * ftu2 / 1000;
									value += ef;
								}

							}
						}
						gs.setStatvalue(value);
						gs.setStattype3("散养");
						
						//重复导入的区的值相加
						if (value > 0) {
							for (GovStat ob : clist) {
								if (ob.getCity() == gs.getCity()
										&& ob.getFillyear() == gs.getFillyear()
										&& ob.getPollutantId() == gs
												.getPollutantId()
										&& ob.getProvince() == gs.getProvince()
										&& ob.getStattype().equals(
												gs.getStattype())
										&& ob.getStattype2() == gs
												.getStattype2()
										&& ob.getStattype3() == gs.getStattype3()
										) {
									gs.setStatvalue(value + ob.getStatvalue());
								}
							}
						}
						clist.add(gs);
					}
			}
		}

		insert(clistrestaurant, theService);
		insert(clistairplane, theService);
		insert(clistgarage, theService);
		insert(clist, theService);

//		System.out.println("========="+clist.size());
//		System.out.println(va);
	}

	private void insert(List<GovStat> clist, NumerationService theService){
		if (clist.size() > 0){
			int count=0;
			List<GovStat> newlist=new ArrayList<GovStat>();
			for(GovStat sp:clist) {
				if(count > 0 && count % 100 == 0) {
					theService.addGovStat(newlist);
					newlist.clear();
				}
				else {
					newlist.add(sp);
				}
				count++;
			}
			if(newlist.size() > 0){
				theService.addGovStat(newlist);
			}
			
		}
	}
	
	// TAN室内
	private double getTANin(int num, int cycle, String feedtype,
			String animaltype, AnimalsParamService apservice) {
		AnimalsParam ap = apservice.getPara(feedtype, animaltype);
		double TANin = 0;
		if (ap != null) {
			TANin = num * cycle * ap.getShitamount() * ap.getNratio()
					* ap.getNH3ratio() * ap.getRoomRatio();
		}
		return TANin;
	}

	// TAN户外
	private double getTANout(int num, int cycle, String feedtype,
			String animaltype, AnimalsParamService apservice) {
		AnimalsParam ap = apservice.getPara(feedtype, animaltype);
		double TANout = 0;
		if (ap != null) {
			TANout = num * cycle * ap.getShitamount() * ap.getNratio()
					* ap.getNH3ratio() * ap.getOutsideRatio();
		}
		return TANout;
	}

	// EN圈舍-固态
	private double getENjss(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double enjss = 0;
		enjss = gfservice.getByThree("animal2", "圈舍-固态", feedtype, animaltype)
				.getFactor()
				* TANin
				* (1 - apservice.getPara(feedtype, animaltype).getXliquid());
		return enjss;
	}

	// EN圈舍-液态
	private double getENjsl(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double enjsl = 0;
		enjsl = gfservice.getByThree("animal2", "圈舍-液态", feedtype, animaltype)
				.getFactor()
				* TANin
				* apservice.getPara(feedtype, animaltype).getXliquid();
		return enjsl;
	}

	// ------------------------------------
	// EN储存-固态
	private double getENccs(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double enccs = 0;
		if (feedtype.equals("规模化养殖")) {
			enccs = (TANin
					* (1 - apservice.getPara(feedtype, animaltype).getXliquid()) - getENjss(feedtype, animaltype, apservice, gfservice, TANin))
					* gfservice.getByFour("animal2", "储存-固态", "NH3", feedtype,
							animaltype).getFactor();
			return enccs;
		} else if (feedtype.equals("散养")) {
			enccs = (TANin
					* (1 - apservice.getPara(feedtype, animaltype).getXliquid()) - getENjss(feedtype, animaltype, apservice, gfservice, TANin))
					* gfservice.getByFour("animal2", "储存-固态", "NH3", feedtype,
							animaltype).getFactor();
			return enccs;
		}
		return enccs;
	}

	// EN储存-液态
	private double getENccl(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double enccl = 0;
		if (feedtype.equals("规模化养殖")) {
			enccl = (TANin
					* apservice.getPara(feedtype, animaltype).getXliquid() - getENjsl(feedtype, animaltype, apservice, gfservice, TANin))
					* gfservice.getByFour("animal2", "储存-液态", "NH3", feedtype,
							animaltype).getFactor();
			return enccl;
		} else if (feedtype.equals("散养")) {
			enccl = (TANin
					* apservice.getPara(feedtype, animaltype).getXliquid() - getENjsl(feedtype, animaltype, apservice, gfservice, TANin))
					* gfservice.getByFour("animal2", "储存-液态", "NH3", feedtype,
							animaltype).getFactor();
			return enccl;
		}
		return enccl;
	}

	// EN损失-固态
	private double getENsss(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double ensss = 0;
		if (feedtype.equals("规模化养殖")) {
			ensss = (TANin
					* (1 - apservice.getPara(feedtype, animaltype).getXliquid()) - getENjss(feedtype, animaltype, apservice, gfservice, TANin))
					* 0.1
					* (gfservice.getByFour("animal2", "储存-固态", "N2O", feedtype,
							animaltype).getFactor()
							+ gfservice.getByFour("animal2", "储存-固态", "NO",
									feedtype, animaltype).getFactor() + gfservice
							.getByFour("animal2", "储存-固态", "N2", feedtype,
									animaltype).getFactor());
			return ensss;
		} else if (feedtype.equals("散养")) {
			ensss = (TANin
					* (1 - apservice.getPara(feedtype, animaltype).getXliquid()) - getENjss(feedtype, animaltype, apservice, gfservice, TANin))
					* 0.1
					* (gfservice.getByFour("animal2", "储存-固态", "N2O", feedtype,
							animaltype).getFactor()
							+ gfservice.getByFour("animal2", "储存-固态", "NO",
									feedtype, animaltype).getFactor() + gfservice
							.getByFour("animal2", "储存-固态", "N2", feedtype,
									animaltype).getFactor());
			return ensss;
		}
		return ensss;
	}

	// EN损失-液态
	private double getENssl(String feedtype,
			String animaltype, AnimalsParamService apservice,
			GovFactorService gfservice, double TANin) {
		double enssl = 0;
		if (feedtype.equals("规模化养殖")) {
			enssl = (TANin
					* apservice.getPara(feedtype, animaltype).getXliquid() - getENjsl(feedtype, animaltype, apservice, gfservice, TANin))
					* (gfservice.getByFour("animal2", "储存-液态", "N2O", feedtype,
							animaltype).getFactor()
							+ gfservice.getByFour("animal2", "储存-液态", "NO",
									feedtype, animaltype).getFactor() + gfservice
							.getByFour("animal2", "储存-液态", "N2", feedtype,
									animaltype).getFactor());
			return enssl;
		} else if (feedtype.equals("散养")) {
			enssl = (TANin
					* apservice.getPara(feedtype, animaltype).getXliquid() - getENjsl(feedtype, animaltype, apservice, gfservice, TANin))
					* (gfservice.getByFour("animal2", "储存-液态", "N2O", feedtype,
							animaltype).getFactor()
							+ gfservice.getByFour("animal2", "储存-液态", "NO",
									feedtype, animaltype).getFactor() + gfservice
							.getByFour("animal2", "储存-液态", "N2", feedtype,
									animaltype).getFactor());
			return enssl;
		}
		return enssl;
	}
}
