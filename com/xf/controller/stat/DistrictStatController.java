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
import com.xf.security.LoginManage;
import com.xf.service.DistrictService;
import com.xf.service.stat.DistrictStatService;
import com.xf.vo.DistrictStat;
import com.xf.vo.DistrictStatM;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface/district")
public class DistrictStatController {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private DistrictStatService service;
	@Autowired
	private DistrictService districtService;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{issmall}/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object stat(@PathVariable int year,@PathVariable int issmall,HttpServletRequest request) {
		
		 if (loginManage.isUserLogin(request)) {
		
		 } else {
			 return result.setStatus(-1, "No login.");
		 }
		    int in = 0;
		    if(issmall < 2){
		    	in = 0;
		    }else if(issmall == 2){
		    	in = 1;
		    }
			List<DistrictStat> list = service.getData(year, issmall, in);
			if(list ==null || list.size()<1) return result.setStatus(0, "无统计数据");

		//如果需统计的条件存在，每个市新建一个DistrictStatM对象，装进lists中
		
			List<District> listc = districtService.getByParent(1);
			List<DistrictStatM> listdis =new ArrayList<DistrictStatM>();
			
			if(listc != null && listc.size()>0){
				double so2 = 0;
				double co = 0;
				double nox = 0;
				double pm10 = 0;
				double pm2510 = 0;
				double pm25 = 0;
				double bcec = 0;
				double oc = 0;
				double vocs = 0;
				double nh3 = 0;
				for (District dis : listc) {

					DistrictStatM diam=new DistrictStatM();
					List listm=new ArrayList();
					diam.setId(dis.getId());
					diam.setName(dis.getDistrictName());
					
					//循环所有的查询条件对象，得出单一的返回值，如果属于该市，pollutantid，pollutantname，total，放进DistrictStatM对象的map中
					for(DistrictStat diss:list){
						if(dis.getId() == diss.getId()){

							Map map=new HashMap();
							map.put("pollutantid",diss.getPollutantId() );
							map.put("pollutantname",diss.getPollutantName());
							map.put("total",diss.getTotal());
							listm.add(map);
							if(diss.getPollutantId() == 1) so2 += diss.getTotal();
							if(diss.getPollutantId() == 2) nox += diss.getTotal();
							if(diss.getPollutantId() == 3) co += diss.getTotal();
							if(diss.getPollutantId() == 4) pm10 += diss.getTotal();
							if(diss.getPollutantId() == 5) pm2510 += diss.getTotal();
							if(diss.getPollutantId() == 6) pm25 += diss.getTotal();
							if(diss.getPollutantId() == 7) bcec += diss.getTotal();
							if(diss.getPollutantId() == 8) oc += diss.getTotal();
							if(diss.getPollutantId() == 9) vocs += diss.getTotal();
							if(diss.getPollutantId() == 10) nh3 += diss.getTotal();							
						}
					}

				    diam.setList(listm);

					listdis.add(diam);
					
				}
				DistrictStatM diam=new DistrictStatM();
				diam.setId(1);
				diam.setName("四川省");
				List listm=new ArrayList();
				for(int i = 1; i < 11; i++){
					Map map=new HashMap();
					map.put("pollutantid",i );
					if(i == 1){
						map.put("pollutantname","SO2");
						map.put("total",so2);
					}else if(i == 2){
						map.put("pollutantname","NOx");
						map.put("total",nox);
					}else if(i == 3){
						map.put("pollutantname","CO");
						map.put("total",co);
					}else if(i == 4){
						map.put("pollutantname","PM10");
						map.put("total",pm10);
					}else if(i == 5){
						map.put("pollutantname","PM2.5-10");
						map.put("total",pm2510);
					}else if(i == 6){
						map.put("pollutantname","PM2.5");
						map.put("total",pm25);
					}else if(i == 7){
						map.put("pollutantname","BC/EC");
						map.put("total",bcec);
					}else if(i == 8){
						map.put("pollutantname","OC");
						map.put("total",oc);
					}else if(i == 9){
						map.put("pollutantname","VOCs");
						map.put("total",vocs);
					}else if(i == 10){
						map.put("pollutantname","NH3");
						map.put("total",nh3);
					}
					
					listm.add(map);
				}
			    diam.setList(listm);
				listdis.add(diam);
			}else{
				return result.setStatus(-1, "无城市");
			}

			result.put("list", listdis);

		return result.setStatus(0, "");

	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getyears", method = RequestMethod.GET)
	@ResponseBody
	public Object years(HttpServletRequest request) {
		
		if (!loginManage.isUserLogin(request))
		 return result.setStatus(-1, "No login.");
		
		int startyear = 2015;
		
		Calendar cal=Calendar.getInstance();
		int curyear=cal.get(cal.YEAR);
		
		int num=curyear-startyear;
		
		List<Integer> list=new ArrayList<Integer>();
		
		for(int i=0;i <= num;i++){
			
			int years=2015+i;
			
			list.add(years);
			
		}
		
		result.put("list", list);
		
		return result.setStatus(0, "ok");
		
	}
	
}
