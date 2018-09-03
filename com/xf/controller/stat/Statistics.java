package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.District;
import com.xf.entity.gov.AnimalsFarm;
import com.xf.entity.gov.AnimalsWild;
import com.xf.entity.gov.CanyinStat;
import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.Equipment;
import com.xf.entity.gov.RoadDust;
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.ConstructionDustService;
import com.xf.service.gov.EquipmentService;
import com.xf.service.gov.RoadDustService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface")
public class Statistics {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private CanyinStatService cyservice;
	@Autowired
	private AnimalsFarmService afservice;
	@Autowired
	private AnimalsWildService awservice;
	@Autowired
	private DistrictService disservice;
	@Autowired
	private RoadDustService roadservice;
	@Autowired
	private ConstructionDustService cdservice;
	@Autowired
	private EquipmentService eqservice;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cateringtype/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object canyin(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}
		List<CanyinStat> cylist = null;
		if(typeid==1){
			cylist = cyservice.statAll_pc(year);
		}else if(typeid==2){
			cylist = cyservice.statAll(year);
		}
		
		List<Map> list = new ArrayList<Map>();
		int middle = 0;
		int big = 0;
		int small = 0;
		int res = 0;
		if (cylist.size() >= 0) {
			for (CanyinStat cs : cylist) {
				big += cs.getCanguan_huge();
				big += cs.getCanguan_big();
				middle += cs.getCanguan_mid();
				small += cs.getCanguan_small();
				small += cs.getFastfood();
				small += cs.getSnack();
				res += cs.getShitang_gongdi();
				res += cs.getShitang_school();
				res += cs.getShitang_shiye();

			}
			Map map1 = new HashMap();
			Map map2 = new HashMap();
			Map map3 = new HashMap();
			Map map4 = new HashMap();
			map1.put("name", "大型餐馆");
			map1.put("count", big);
			map2.put("name", "中餐馆");
			map2.put("count", middle);
			map3.put("name", "小餐馆");
			map3.put("count", small);
			map4.put("name", "食堂");
			map4.put("count", res);
			list.add(map1);
			list.add(map2);
			list.add(map3);
			list.add(map4);
		} else {
			return result.setStatus(-2, "无数据");
		}
		result.put("list", list);

		return result.setStatus(0, "ok");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/poultrytype/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object xuqin(@PathVariable int typeid,@PathVariable int year, HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Map> list = new ArrayList<Map>();
		List<AnimalsFarm> listf = null;
		List<AnimalsWild> listw = null;
		if(typeid==1){
			listf = afservice.statAll2(year);
			listw = awservice.statAll2(year);
		}else if(typeid==2){
			listf = afservice.statAll(year);
			listw = awservice.statAll(year);
		}
		
		List<Map> listfm = new ArrayList<Map>();
		List<Map> listfw = new ArrayList<Map>();
		List<Map> listsum = new ArrayList<Map>();
        
		Map nium2 = new HashMap();
		Map yangm2 = new HashMap();
		Map rzhum2 = new HashMap();
		Map mzhum2 = new HashMap();
		Map jim2 = new HashMap();
		Map yam2 = new HashMap();
		Map em2 = new HashMap();
		Map tum2 = new HashMap();
		Map mam2 = new HashMap();
		int niu2 = 0;
		int yang2 = 0;
		int rzhu2 = 0;
		int mzhu2 = 0;
		int ji2 = 0;
		int ya2 = 0;
		int e2 = 0;
		int tu2 = 0;
		int ma2 = 0;
		
		if (listf.size() >= 0) {
			Map nium = new HashMap();
			Map yangm = new HashMap();
			Map rzhum = new HashMap();
			Map mzhum = new HashMap();
			Map jim = new HashMap();
			Map yam = new HashMap();
			Map em = new HashMap();
			Map tum = new HashMap();
			Map mam = new HashMap();
			int niu = 0;
			int yang = 0;
			int rzhu = 0;
			int mzhu = 0;
			int ji = 0;
			int ya = 0;
			int e = 0;
			int tu = 0;
			int ma = 0;
			for (AnimalsFarm af : listf) {
				niu = niu + af.getBeef() + af.getCow();
				yang = yang + af.getGoat() + af.getSheep();
				rzhu += af.getPig();
				mzhu += af.getSow();
				ji = ji + af.getChicken() + af.getHen();
				ya = ya + af.getDuck() + af.getLayingduck();
				e = e + af.getGoose() + af.getLayinggoose();
				tu += af.getRabbit();
				ma += af.getHorse();
			}
			niu2 += niu;
			yang2 += yang;
			rzhu2 += rzhu;
			mzhu2 += mzhu;
			ji2 += ji;
			ya2 += ya;
			e2 += e;
			tu2 += tu;
			ma2 += ma;
			nium.put("name", "牛");
			nium.put("statvalue", niu);
			yangm.put("name", "羊");
			yangm.put("statvalue", yang);
			rzhum.put("name", "肉猪");
			rzhum.put("statvalue", rzhu);
			mzhum.put("name", "母猪");
			mzhum.put("statvalue", mzhu);
			jim.put("name", "鸡");
			jim.put("statvalue", ji);
			yam.put("name", "鸭");
			yam.put("statvalue", ya);
			em.put("name", "鹅");
			em.put("statvalue", e);
			tum.put("name", "兔");
			tum.put("statvalue", tu);
			mam.put("name", "马");
			mam.put("statvalue", ma);
			listfm.add(nium);
			listfm.add(yangm);
			listfm.add(rzhum);
			listfm.add(mzhum);
			listfm.add(jim);
			listfm.add(yam);
			listfm.add(em);
			listfm.add(tum);
			listfm.add(mam);
		}

		if (listw.size() >= 0) {
			Map nium = new HashMap();
			Map yangm = new HashMap();
			Map rzhum = new HashMap();
			Map mzhum = new HashMap();
			Map jim = new HashMap();
			Map yam = new HashMap();
			Map em = new HashMap();
			Map tum = new HashMap();
			Map mam = new HashMap();
			int niu = 0;
			int yang = 0;
			int rzhu = 0;
			int mzhu = 0;
			int ji = 0;
			int ya = 0;
			int e = 0;
			int tu = 0;
			int ma = 0;

			nium.clear();
			yangm.clear();
			rzhum.clear();
			mzhum.clear();
			jim.clear();
			yam.clear();
			em.clear();
			tum.clear();
			mam.clear();

			for (AnimalsWild af : listw) {
				niu = niu + af.getBeef() + af.getCow();
				yang = yang + af.getGoat() + af.getSheep();
				rzhu += af.getPig();
				mzhu += af.getSow();
				ji = ji + af.getChicken() + af.getHen();
				ya = ya + af.getDuck() + af.getLayingduck();
				e = e + af.getGoose() + af.getLayinggoose();
				tu += af.getRabbit();
				ma += af.getHorse();
			}
			niu2 += niu;
			yang2 += yang;
			rzhu2 += rzhu;
			mzhu2 += mzhu;
			ji2 += ji;
			ya2 += ya;
			e2 += e;
			tu2 += tu;
			ma2 += ma;
			nium.put("name", "牛");
			nium.put("statvalue", niu);
			yangm.put("name", "羊");
			yangm.put("statvalue", yang);
			rzhum.put("name", "肉猪");
			rzhum.put("statvalue", rzhu);
			mzhum.put("name", "母猪");
			mzhum.put("statvalue", mzhu);
			jim.put("name", "鸡");
			jim.put("statvalue", ji);
			yam.put("name", "鸭");
			yam.put("statvalue", ya);
			em.put("name", "鹅");
			em.put("statvalue", e);
			tum.put("name", "兔");
			tum.put("statvalue", tu);
			mam.put("name", "马");
			mam.put("statvalue", ma);
			listfw.add(nium);
			listfw.add(yangm);
			listfw.add(rzhum);
			listfw.add(mzhum);
			listfw.add(jim);
			listfw.add(yam);
			listfw.add(em);
			listfw.add(tum);
			listfw.add(mam);
		}
		nium2.put("name", "牛");
		nium2.put("statvalue", niu2);
		yangm2.put("name", "羊");
		yangm2.put("statvalue", yang2);
		rzhum2.put("name", "肉猪");
		rzhum2.put("statvalue", rzhu2);
		mzhum2.put("name", "母猪");
		mzhum2.put("statvalue", mzhu2);
		jim2.put("name", "鸡");
		jim2.put("statvalue", ji2);
		yam2.put("name", "鸭");
		yam2.put("statvalue", ya2);
		em2.put("name", "鹅");
		em2.put("statvalue", e2);
		tum2.put("name", "兔");
		tum2.put("statvalue", tu2);
		mam2.put("name", "马");
		mam2.put("statvalue", ma2);
		listsum.add(nium2);
		listsum.add(yangm2);
		listsum.add(rzhum2);
		listsum.add(mzhum2);
		listsum.add(jim2);
		listsum.add(yam2);
		listsum.add(em2);
		listsum.add(tum2);
		listsum.add(mam2);
		
		Map fm = new HashMap();
		Map fw = new HashMap();
		Map sum = new HashMap();
		fm.put("name", "规模化养殖");
		fm.put("list", listfm);
		fw.put("name", "散养");
		fw.put("list", listfw);
		sum.put("name", "合计");
		sum.put("list", listsum);

		list.add(fm);
		list.add(fw);
		list.add(sum);

		result.put("list", list);

		return result.setStatus(0, "ok");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/poultrycity/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object xuqin2(@PathVariable int typeid,@PathVariable int year, HttpServletRequest request) {
		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Map> list = new ArrayList<Map>();
		List<AnimalsFarm> listf = null;
		List<AnimalsWild> listw = null;
        if(typeid==1){
        	listf = afservice.statAll2(year);
        	listw = awservice.statAll2(year);
        }else if(typeid==2){
        	listf = afservice.statAll(year);
        	listw = awservice.statAll(year);
        }
		List<Map> listfm = new ArrayList<Map>();
		List<Map> listfw = new ArrayList<Map>();
		List<Map> listsum = new ArrayList<Map>();
        
		Map nium2 = new HashMap();
		Map yangm2 = new HashMap();
		Map rzhum2 = new HashMap();
		Map mzhum2 = new HashMap();
		Map jim2 = new HashMap();
		Map yam2 = new HashMap();
		Map em2 = new HashMap();
		Map tum2 = new HashMap();
		Map mam2 = new HashMap();
		int niu2 = 0;
		int yang2 = 0;
		int rzhu2 = 0;
		int mzhu2 = 0;
		int ji2 = 0;
		int ya2 = 0;
		int e2 = 0;
		int tu2 = 0;
		int ma2 = 0;
		List<District> listdis = disservice.getByLevel(1);
        District dist = new District();
        dist.setDistrictName("四川省");
        dist.setId(1);
        
		listdis.add(0,dist);
		
		if (listf.size() >= 0) {
			for (District dis : listdis) {
				if(dis.getId() == 1){
					Map nium = new HashMap();
					Map yangm = new HashMap();
					Map rzhum = new HashMap();
					Map mzhum = new HashMap();
					Map jim = new HashMap();
					Map yam = new HashMap();
					Map em = new HashMap();
					Map tum = new HashMap();
					Map mam = new HashMap();
					int niu = 0;
					int yang = 0;
					int rzhu = 0;
					int mzhu = 0;
					int ji = 0;
					int ya = 0;
					int e = 0;
					int tu = 0;
					int ma = 0;
					Map mapdis = new HashMap();
					mapdis.put("id", dis.getId());
					mapdis.put("name", dis.getDistrictName());
					List<Map> listm = new ArrayList<Map>();
					for (AnimalsFarm af : listf) {
						niu = niu + af.getBeef() + af.getCow();
						yang = yang + af.getGoat() + af.getSheep();
						rzhu += af.getPig();
						mzhu += af.getSow();
						ji = ji + af.getChicken() + af.getHen();
						ya = ya + af.getDuck() + af.getLayingduck();
						e = e + af.getGoose() + af.getLayinggoose();
						tu += af.getRabbit();
						ma += af.getHorse();		
					}
					nium.put("name", "牛");
					nium.put("value", niu);
					yangm.put("name", "羊");
					yangm.put("value", yang);
					rzhum.put("name", "肉猪");
					rzhum.put("value", rzhu);
					mzhum.put("name", "母猪");
					mzhum.put("value", mzhu);
					jim.put("name", "鸡");
					jim.put("value", ji);
					yam.put("name", "鸭");
					yam.put("value", ya);
					em.put("name", "鹅");
					em.put("value", e);
					tum.put("name", "兔");
					tum.put("value", tu);
					mam.put("name", "马");
					mam.put("value", ma);
					listm.add(nium);
					listm.add(yangm);
					listm.add(rzhum);
					listm.add(mzhum);
					listm.add(jim);
					listm.add(yam);
					listm.add(em);
					listm.add(tum);
					listm.add(mam);
					mapdis.put("list", listm);
					listfm.add(mapdis);
				}else{
					Map nium = new HashMap();
					Map yangm = new HashMap();
					Map rzhum = new HashMap();
					Map mzhum = new HashMap();
					Map jim = new HashMap();
					Map yam = new HashMap();
					Map em = new HashMap();
					Map tum = new HashMap();
					Map mam = new HashMap();
					int niu = 0;
					int yang = 0;
					int rzhu = 0;
					int mzhu = 0;
					int ji = 0;
					int ya = 0;
					int e = 0;
					int tu = 0;
					int ma = 0;
					Map mapdis = new HashMap();
					mapdis.put("id", dis.getId());
					mapdis.put("name", dis.getDistrictName());
					List<Map> listm = new ArrayList<Map>();
					for (AnimalsFarm af : listf) {
						if (af.getCity() == dis.getId()) {
							niu = niu + af.getBeef() + af.getCow();
							yang = yang + af.getGoat() + af.getSheep();
							rzhu += af.getPig();
							mzhu += af.getSow();
							ji = ji + af.getChicken() + af.getHen();
							ya = ya + af.getDuck() + af.getLayingduck();
							e = e + af.getGoose() + af.getLayinggoose();
							tu += af.getRabbit();
							ma += af.getHorse();
						}
					}
					nium.put("name", "牛");
					nium.put("value", niu);
					yangm.put("name", "羊");
					yangm.put("value", yang);
					rzhum.put("name", "肉猪");
					rzhum.put("value", rzhu);
					mzhum.put("name", "母猪");
					mzhum.put("value", mzhu);
					jim.put("name", "鸡");
					jim.put("value", ji);
					yam.put("name", "鸭");
					yam.put("value", ya);
					em.put("name", "鹅");
					em.put("value", e);
					tum.put("name", "兔");
					tum.put("value", tu);
					mam.put("name", "马");
					mam.put("value", ma);
					listm.add(nium);
					listm.add(yangm);
					listm.add(rzhum);
					listm.add(mzhum);
					listm.add(jim);
					listm.add(yam);
					listm.add(em);
					listm.add(tum);
					listm.add(mam);
					mapdis.put("list", listm);
					listfm.add(mapdis);
				}
				
			}
		}
		if (listw.size() >= 0) {
			for (District dis : listdis) {
				if(dis.getId() == 1){
					Map nium = new HashMap();
					Map yangm = new HashMap();
					Map rzhum = new HashMap();
					Map mzhum = new HashMap();
					Map jim = new HashMap();
					Map yam = new HashMap();
					Map em = new HashMap();
					Map tum = new HashMap();
					Map mam = new HashMap();
					int niu = 0;
					int yang = 0;
					int rzhu = 0;
					int mzhu = 0;
					int ji = 0;
					int ya = 0;
					int e = 0;
					int tu = 0;
					int ma = 0;
					Map mapdis = new HashMap();
					mapdis.put("id", dis.getId());
					mapdis.put("name", dis.getDistrictName());
					List<Map> listm = new ArrayList<Map>();
					for (AnimalsWild af : listw) {
						niu = niu + af.getBeef() + af.getCow();
						yang = yang + af.getGoat() + af.getSheep();
						rzhu += af.getPig();
						mzhu += af.getSow();
						ji = ji + af.getChicken() + af.getHen();
						ya = ya + af.getDuck() + af.getLayingduck();
						e = e + af.getGoose() + af.getLayinggoose();
						tu += af.getRabbit();
						ma += af.getHorse();		
					}
					nium.put("name", "牛");
					nium.put("value", niu);
					yangm.put("name", "羊");
					yangm.put("value", yang);
					rzhum.put("name", "肉猪");
					rzhum.put("value", rzhu);
					mzhum.put("name", "母猪");
					mzhum.put("value", mzhu);
					jim.put("name", "鸡");
					jim.put("value", ji);
					yam.put("name", "鸭");
					yam.put("value", ya);
					em.put("name", "鹅");
					em.put("value", e);
					tum.put("name", "兔");
					tum.put("value", tu);
					mam.put("name", "马");
					mam.put("value", ma);
					listm.add(nium);
					listm.add(yangm);
					listm.add(rzhum);
					listm.add(mzhum);
					listm.add(jim);
					listm.add(yam);
					listm.add(em);
					listm.add(tum);
					listm.add(mam);
					mapdis.put("list", listm);
					listfw.add(mapdis);
				}else{
					Map nium = new HashMap();
					Map yangm = new HashMap();
					Map rzhum = new HashMap();
					Map mzhum = new HashMap();
					Map jim = new HashMap();
					Map yam = new HashMap();
					Map em = new HashMap();
					Map tum = new HashMap();
					Map mam = new HashMap();
					int niu = 0;
					int yang = 0;
					int rzhu = 0;
					int mzhu = 0;
					int ji = 0;
					int ya = 0;
					int e = 0;
					int tu = 0;
					int ma = 0;
					Map mapdis = new HashMap();
					mapdis.put("id", dis.getId());
					mapdis.put("name", dis.getDistrictName());
					List<Map> listm = new ArrayList<Map>();
					for (AnimalsWild af : listw) {
						if (af.getCity() == dis.getId()) {
							niu = niu + af.getBeef() + af.getCow();
							yang = yang + af.getGoat() + af.getSheep();
							rzhu += af.getPig();
							mzhu += af.getSow();
							ji = ji + af.getChicken() + af.getHen();
							ya = ya + af.getDuck() + af.getLayingduck();
							e = e + af.getGoose() + af.getLayinggoose();
							tu += af.getRabbit();
							ma += af.getHorse();
						}
					}
					nium.put("name", "牛");
					nium.put("value", niu);
					yangm.put("name", "羊");
					yangm.put("value", yang);
					rzhum.put("name", "肉猪");
					rzhum.put("value", rzhu);
					mzhum.put("name", "母猪");
					mzhum.put("value", mzhu);
					jim.put("name", "鸡");
					jim.put("value", ji);
					yam.put("name", "鸭");
					yam.put("value", ya);
					em.put("name", "鹅");
					em.put("value", e);
					tum.put("name", "兔");
					tum.put("value", tu);
					mam.put("name", "马");
					mam.put("value", ma);
					listm.add(nium);
					listm.add(yangm);
					listm.add(rzhum);
					listm.add(mzhum);
					listm.add(jim);
					listm.add(yam);
					listm.add(em);
					listm.add(tum);
					listm.add(mam);
					mapdis.put("list", listm);
					listfw.add(mapdis);
				}
			}
		}
		for(District dis : listdis){
			if(dis.getId() != 1){
				Map nium = new HashMap();
				Map yangm = new HashMap();
				Map rzhum = new HashMap();
				Map mzhum = new HashMap();
				Map jim = new HashMap();
				Map yam = new HashMap();
				Map em = new HashMap();
				Map tum = new HashMap();
				Map mam = new HashMap();
				int niu = 0;
				int yang = 0;
				int rzhu = 0;
				int mzhu = 0;
				int ji = 0;
				int ya = 0;
				int e = 0;
				int tu = 0;
				int ma = 0;
				Map mapdis = new HashMap();
				mapdis.put("id", dis.getId());
				mapdis.put("name", dis.getDistrictName());
				List<Map> listm = new ArrayList<Map>();
				if(listf.size() > 0){
					for (AnimalsFarm af : listf) {
						if (af.getCity() == dis.getId()) {
							niu = niu + af.getBeef() + af.getCow();
							yang = yang + af.getGoat() + af.getSheep();
							rzhu += af.getPig();
							mzhu += af.getSow();
							ji = ji + af.getChicken() + af.getHen();
							ya = ya + af.getDuck() + af.getLayingduck();
							e = e + af.getGoose() + af.getLayinggoose();
							tu += af.getRabbit();
							ma += af.getHorse();
						}
					}
				}
				if(listw.size() > 0){
					for (AnimalsWild af : listw) {
						if (af.getCity() == dis.getId()) {
							niu = niu + af.getBeef() + af.getCow();
							yang = yang + af.getGoat() + af.getSheep();
							rzhu += af.getPig();
							mzhu += af.getSow();
							ji = ji + af.getChicken() + af.getHen();
							ya = ya + af.getDuck() + af.getLayingduck();
							e = e + af.getGoose() + af.getLayinggoose();
							tu += af.getRabbit();
							ma += af.getHorse();
						}
					}
				}
				niu2 += niu;
				yang2 += yang;
				rzhu2 += rzhu;
				mzhu2 += mzhu;
				ji2 += ji;
				ya2 += ya;
				e2 += e;
				tu2 += tu;
				ma2 += ma;
				nium.put("name", "牛");
				nium.put("value", niu);
				yangm.put("name", "羊");
				yangm.put("value", yang);
				rzhum.put("name", "肉猪");
				rzhum.put("value", rzhu);
				mzhum.put("name", "母猪");
				mzhum.put("value", mzhu);
				jim.put("name", "鸡");
				jim.put("value", ji);
				yam.put("name", "鸭");
				yam.put("value", ya);
				em.put("name", "鹅");
				em.put("value", e);
				tum.put("name", "兔");
				tum.put("value", tu);
				mam.put("name", "马");
				mam.put("value", ma);
				listm.add(nium);
				listm.add(yangm);
				listm.add(rzhum);
				listm.add(mzhum);
				listm.add(jim);
				listm.add(yam);
				listm.add(em);
				listm.add(tum);
				listm.add(mam);
				mapdis.put("list", listm);
				listsum.add(mapdis);
			}
		
		}
		Map mapdis = new HashMap();
		mapdis.put("id", 1);
		mapdis.put("name", "四川省");
		List<Map> listm = new ArrayList<Map>();
		nium2.put("name", "牛");
		nium2.put("value", niu2);
		yangm2.put("name", "羊");
		yangm2.put("value", yang2);
		rzhum2.put("name", "肉猪");
		rzhum2.put("value", rzhu2);
		mzhum2.put("name", "母猪");
		mzhum2.put("value", mzhu2);
		jim2.put("name", "鸡");
		jim2.put("value", ji2);
		yam2.put("name", "鸭");
		yam2.put("value", ya2);
		em2.put("name", "鹅");
		em2.put("value", e2);
		tum2.put("name", "兔");
		tum2.put("value", tu2);
		mam2.put("name", "马");
		mam2.put("value", ma2);
		listm.add(nium2);
		listm.add(yangm2);
		listm.add(rzhum2);
		listm.add(mzhum2);
		listm.add(jim2);
		listm.add(yam2);
		listm.add(em2);
		listm.add(tum2);
		listm.add(mam2);
		mapdis.put("list", listm);
		listsum.add(0,mapdis);
		
		Map fm = new HashMap();
		Map fw = new HashMap();
		Map sum = new HashMap();
		fm.put("name", "规模化养殖");
		fm.put("value", listfm);
		fw.put("name", "散养");
		fw.put("value", listfw);
		sum.put("name", "合计");
		sum.put("value", listsum);

		list.add(fm);
		list.add(fw);
		list.add(sum);

		result.put("list", list);

		return result.setStatus(0, "ok");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/roaddustingcity/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object roadDust(@PathVariable int year, HttpServletRequest request) {
		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		List<RoadDust> listr = roadservice.statAll(year);
		List<District> listdis = disservice.getByLevel(1);
		List<Map> list = new ArrayList<Map>();

		if (listr.size() >= 0) {
			for (District dis : listdis) {
				Map kspm = new HashMap();
				Map kscm = new HashMap();
				Map ksnm = new HashMap();
				Map zgpm = new HashMap();
				Map zgcm = new HashMap();
				Map zgnm = new HashMap();
				Map cgpm = new HashMap();
				Map cgcm = new HashMap();
				Map cgnm = new HashMap();
				Map zpm = new HashMap();
				Map zcm = new HashMap();
				Map znm = new HashMap();
				kspm.put("name", "沥青路");
				kscm.put("name", "水泥路");
				ksnm.put("name", "未铺装");
				zgpm.put("name", "沥青路");
				zgcm.put("name", "水泥路");
				zgnm.put("name", "未铺装");
				cgpm.put("name", "沥青路");
				cgcm.put("name", "水泥路");
				cgnm.put("name", "未铺装");
				zpm.put("name", "沥青路");
				zcm.put("name", "水泥路");
				znm.put("name", "未铺装");
				Map ksm = new HashMap();
				Map zgm = new HashMap();
				Map cgm = new HashMap();
				Map zm = new HashMap();
				ksm.put("name", "快速路");
				zgm.put("name", "主干路");
				cgm.put("name", "次干路");
				zm.put("name", "支路");
				int ksp = 0;
				int ksc = 0;
				int ksn = 0;
				int zgp = 0;
				int zgc = 0;
				int zgn = 0;
				int cgp = 0;
				int cgc = 0;
				int cgn = 0;
				int zp = 0;
				int zc = 0;
				int zn = 0;
				Map mapdis = new HashMap();
				mapdis.put("id", dis.getId());
				mapdis.put("name", dis.getDistrictName());
				List<Map> listm = new ArrayList<Map>();
				for (RoadDust rd : listr) {
					if (rd.getCity() == dis.getId()) {
						ksp+=rd.getKsPitch();
						ksc+=rd.getKsCement();
						ksn+=rd.getKsNotShop();
						zgp+=rd.getZgPitch();
						zgc+=rd.getZgCement();
						zgn+=rd.getZgNotShop();
						cgp+=rd.getCgPitch();
						cgc+=rd.getCgCement();
						cgn+=rd.getCgNotShop();
						zp+=rd.getzPitch();
						zc+=rd.getzCement();
						zn+=rd.getzNotShop();
					}
				}
				kspm.put("value", ksp);
				kscm.put("value", ksc);
				ksnm.put("value", ksn);
				zgpm.put("value", zgp);
				zgcm.put("value", zgc);
				zgnm.put("value", zgn);
				cgpm.put("value", cgp);
				cgcm.put("value", cgc);
				cgnm.put("value", cgn);
				zpm.put("value", zp);
				zcm.put("value", zc);
				znm.put("value", zn);
				for (int i = 0; i < 4; i++) {
					List<Map> listm2 = new ArrayList<Map>();
					if(i==0) {
						listm2.add(kspm);
						listm2.add(kscm);
						listm2.add(ksnm);
						ksm.put("list", listm2);
					}
					if(i==1) {
						listm2.add(zgpm);
						listm2.add(zgcm);
						listm2.add(zgnm);
						zgm.put("list", listm2);
					}
					if(i==2) {
						listm2.add(cgpm);
						listm2.add(cgcm);
						listm2.add(cgnm);
						cgm.put("list", listm2);
					}
					if(i==3) {
						listm2.add(zpm);
						listm2.add(zcm);
						listm2.add(znm);
						zm.put("list", listm2);
					}
					
				}
				listm.add(ksm);
				listm.add(zgm);
				listm.add(cgm);
				listm.add(zm);
				mapdis.put("list", listm);
				list.add(mapdis);
			}

		}
		result.put("list", list);

		return result.setStatus(0, "ok");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/constructioncity/stat/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object allCityType(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {
		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}
		List<ConstructionDust> listcd = null;
		if(typeid==1){
			listcd = cdservice.statAll2(year);
		}else if(typeid==2){
			listcd = cdservice.statAll(year);
		}
		
		List<District> listdis = disservice.getByLevel(1);
		List<Map> list = new ArrayList<Map>();
		
		if(listcd.size() > 0){
			for (District dis : listdis) {
				Map mapdis = new HashMap();
				mapdis.put("id", dis.getId());
				mapdis.put("name", dis.getDistrictName());
				List<Map> listm = new ArrayList<Map>();
				List<Map> listm2 = new ArrayList<Map>();
				double startedarea4=0;
				int startednum4=0;
				double noarea4=0;
				int nonum4=0;
				double startedarea8=0;
				int startednum8=0;
				double noarea8=0;
				int nonum8=0;
				double startedarea12=0;
				int startednum12=0;
				double noarea12=0;
				int nonum12=0;
				double startedarea=0;
				int startednum=0;
				double noarea=0;
				int nonum=0;
				Map aa = new HashMap();
				Map ba = new HashMap();
				Map ca = new HashMap();
				Map da = new HashMap();
				Map an = new HashMap();
				Map bn = new HashMap();
				Map cn = new HashMap();
				Map dn = new HashMap();
				Map aan = new HashMap();
				Map ban = new HashMap();
				Map can = new HashMap();
				Map dan = new HashMap();
				Map ann = new HashMap();
				Map bnn = new HashMap();
				Map cnn = new HashMap();
				Map dnn = new HashMap();
				aa.put("area", ">12000");
				ba.put("area", "8000~12000");
				ca.put("area", "4000~8000");
				da.put("area", "≤4000");
				an.put("num", ">12000");
				bn.put("num", "8000~12000");
				cn.put("num", "4000~8000");
				dn.put("num", "≤4000");
				aan.put("area", ">12000");
				ban.put("area", "8000~12000");
				can.put("area", "4000~8000");
				dan.put("area", "≤4000");
				ann.put("num", ">12000");
				bnn.put("num", "8000~12000");
				cnn.put("num", "4000~8000");
				dnn.put("num", "≤4000");
				for(ConstructionDust cd:listcd){
					if(cd.getCity() == dis.getId() && cd.getArea() == 15001){
						startedarea=cd.getHasStartedArea();
						startednum=cd.getHasStartNumber();
						noarea=cd.getNotStartArea();
						nonum=cd.getNotStartNumber();
					}
					else if(cd.getCity() == dis.getId() && cd.getArea() == 15002){
						startedarea4=cd.getHasStartedArea();
						startednum4=cd.getHasStartNumber();
						noarea4=cd.getNotStartArea();
						nonum4=cd.getNotStartNumber();
					}
					else if(cd.getCity() == dis.getId() && cd.getArea() == 15003){
						startedarea8=cd.getHasStartedArea();
						startednum8=cd.getHasStartNumber();
						noarea8=cd.getNotStartArea();
						nonum8=cd.getNotStartNumber();
					}
					else if(cd.getCity() == dis.getId() && cd.getArea() == 15004){
						startedarea12+=cd.getHasStartedArea();
						startednum12+=cd.getHasStartNumber();
						noarea12+=cd.getNotStartArea();
						nonum12+=cd.getNotStartNumber();
					}
				}
				aa.put("value", startedarea12);
				ba.put("value", startedarea8);
				ca.put("value", startedarea4);
				da.put("value", startedarea);
				an.put("value2", startednum12);
				bn.put("value2", startednum8);
				cn.put("value2", startednum4);
				dn.put("value2", startednum);
				aan.put("value", noarea12);
				ban.put("value", noarea8);
				can.put("value", noarea4);
				dan.put("value", noarea);
				ann.put("value2", nonum12);
				bnn.put("value2", nonum8);
				cnn.put("value2", nonum4);
				dnn.put("value2", nonum);
				listm.add(aa);
				listm.add(ba);
				listm.add(ca);
				listm.add(da);
				listm.add(an);
				listm.add(bn);
				listm.add(cn);
				listm.add(dn);
				listm2.add(aan);
				listm2.add(ban);
				listm2.add(can);
				listm2.add(dan);
				listm2.add(ann);
				listm2.add(bnn);
				listm2.add(cnn);
				listm2.add(dnn);
				mapdis.put("list", listm);
				mapdis.put("list2", listm2);
				list.add(mapdis);
			}
		}

		
		result.put("list", list);

		return result.setStatus(0, "ok");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/equipment/getdata/{year}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object equipment(@PathVariable int year,@PathVariable int typeid, HttpServletRequest request) {
		
		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		List<Equipment> listeq = null;
		if(typeid==1){
			listeq = eqservice.statAll2(year);
		}else if(typeid==2){
			listeq = eqservice.statAll(year);
		}
		List<District> listdis = disservice.getByLevel(1);
		List<Map> list = new ArrayList<Map>();
		List<Map> list2 = new ArrayList<Map>();
		
		if(listeq.size() >= 0){
			for (District dis : listdis) {
				Map mapdis = new HashMap();
				mapdis.put("id", dis.getId());
				mapdis.put("name", dis.getDistrictName());
				List<Map> listm = new ArrayList<Map>();
				Map a = new HashMap();				
				Map b = new HashMap();
				Map c = new HashMap();
				Map d = new HashMap();
				Map e = new HashMap();
				Map f = new HashMap();
				Map g = new HashMap();
				Map h = new HashMap();
				Map i = new HashMap();
				a.put("name", "挖掘机");
				b.put("name", "推土机");
				c.put("name", "装载机");
				d.put("name", "叉车");
				e.put("name", "超重机");
				f.put("name", "混凝土搅拌机");
				g.put("name", "压路机");
				h.put("name", "摊铺机");
				i.put("name", "平地机");
				for(int j=0;j<9;j++){
					List<Map> listm2 = new ArrayList<Map>();
					Map aa = new HashMap();
					Map bb = new HashMap();
					Map cc = new HashMap();
					Map dd = new HashMap();
					aa.put("name", "≤4000");
					bb.put("name", "4000~8000");
					cc.put("name", "8000~12000");
					dd.put("name", ">12000");
					int num1=0;
					int num2=0;
					int num3=0;
					int num4=0;
					
					for( Equipment eq:listeq){
						if(eq.getCity()==dis.getId() && eq.getArea()==15001 && eq.getEtype()==(14001+j)){
							num1+=eq.getEnumber();
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15002 && eq.getEtype()==(14001+j)){
							num2+=eq.getEnumber();
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15003 && eq.getEtype()==(14001+j)){
							num3+=eq.getEnumber();
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15004 && eq.getEtype()==(14001+j)){
							num4+=eq.getEnumber();
						}
					}	
					aa.put("num", num1);
					bb.put("num", num2);
					cc.put("num", num3);
					dd.put("num", num4);
					listm2.add(aa);
					listm2.add(bb);
					listm2.add(cc);
					listm2.add(dd);
					
					if(j==0) a.put("list", listm2);
					if(j==1) b.put("list", listm2);
					if(j==2) c.put("list", listm2);
					if(j==3) d.put("list", listm2);
					if(j==4) e.put("list", listm2);
					if(j==5) f.put("list", listm2);
					if(j==6) g.put("list", listm2);
					if(j==7) h.put("list", listm2);
					if(j==8) i.put("list", listm2);
				}
				listm.add(a);
				listm.add(b);
				listm.add(c);
				listm.add(d);
				listm.add(e);
				listm.add(f);
				listm.add(g);
				listm.add(h);
				listm.add(i);
				mapdis.put("list", listm);
				list.add(mapdis);
			}
		}
		if(listeq.size() >= 0){
					for (District dis : listdis) {
						Map mapdis = new HashMap();
						mapdis.put("id", dis.getId());
						mapdis.put("name", dis.getDistrictName());
						List<Map> listm = new ArrayList<Map>();
						Map a = new HashMap();				
						Map b = new HashMap();
						Map c = new HashMap();
						Map d = new HashMap();
						Map e = new HashMap();
						Map f = new HashMap();
						Map g = new HashMap();
						Map h = new HashMap();
						Map i = new HashMap();
						a.put("name", "挖掘机");
						b.put("name", "推土机");
						c.put("name", "装载机");
						d.put("name", "叉车");
						e.put("name", "超重机");
						f.put("name", "混凝土搅拌机");
						g.put("name", "压路机");
						h.put("name", "摊铺机");
						i.put("name", "平地机");
				for(int j=0;j<9;j++){
					List<Map> listm3 = new ArrayList<Map>();
					Map aaa = new HashMap();
					Map bbb = new HashMap();
					Map ccc = new HashMap();
					Map ddd = new HashMap();

					aaa.put("name", "≤4000");
					bbb.put("name", "4000~8000");
					ccc.put("name", "8000~12000");
					ddd.put("name", ">12000");
					double hours1=0;
					double hours2=0;
					double hours3=0;
					double hours4=0;
					for( Equipment eq:listeq){
						if(eq.getCity()==dis.getId() && eq.getArea()==15001 && eq.getEtype()==(14001+j)){
							hours1=hours1+eq.getEduration()*365;
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15002 && eq.getEtype()==(14001+j)){
							hours2=hours1+eq.getEduration()*365;
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15003 && eq.getEtype()==(14001+j)){
							hours3=hours1+eq.getEduration()*365;
						}else if(eq.getCity()==dis.getId() && eq.getArea()==15004 && eq.getEtype()==(14001+j)){
							hours4=hours1+eq.getEduration()*365;
						}
					}	
					aaa.put("hours", hours1);
					bbb.put("hours", hours2);
					ccc.put("hours", hours3);
					ddd.put("hours", hours4);
					
					listm3.add(aaa);
					listm3.add(bbb);
					listm3.add(ccc);
					listm3.add(ddd);
					
					if(j==0) a.put("list", listm3);
					if(j==1) b.put("list", listm3);
					if(j==2) c.put("list", listm3);
					if(j==3) d.put("list", listm3);
					if(j==4) e.put("list", listm3);
					if(j==5) f.put("list", listm3);
					if(j==6) g.put("list", listm3);
					if(j==7) h.put("list", listm3);
					if(j==8) i.put("list", listm3);
				}
				listm.add(a);
				listm.add(b);
				listm.add(c);
				listm.add(d);
				listm.add(e);
				listm.add(f);
				listm.add(g);
				listm.add(h);
				listm.add(i);
				mapdis.put("list", listm);
				list2.add(mapdis);
			}
		}

		result.put("list", list);
		result.put("list2", list2);
		return result.setStatus(0, "ok");
	}
}
