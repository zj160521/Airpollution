package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.security.LoginManage;
import com.xf.service.stat.YLFuelStatService;
import com.xf.vo.YLFuelStat;
import com.xf.vo.YLFuelStatVo;


@Scope("prototype")
@Controller
@RequestMapping(value = "/ylfuelstat")
public class YLFuelStatController {
	
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private YLFuelStatService service;
	
	@RequestMapping(value = "/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object stat(@PathVariable int year,HttpServletRequest request) {
		
		 if (loginManage.isUserLogin(request)) {
				
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		 
		 List<YLFuelStat> list = service.getData(year);
		 List listvo=new ArrayList();
		 if(list.size()>0){
			 for(YLFuelStat lis:list){
				 YLFuelStatVo vo= new YLFuelStatVo();
				 vo.setFuelid(lis.getFuelid());
				 vo.setFuelname(lis.getFuelname());
				 List li=new ArrayList();
				 for(int i=0;i<12;i++){
					Map mp=new HashMap();
	        		mp.put("month", i+1);
	        		if(i==0) mp.put("value", lis.getM1());
	        		if(i==1) mp.put("value", lis.getM2());
	        		if(i==2) mp.put("value", lis.getM3());
	        		if(i==3) mp.put("value", lis.getM4());
	        		if(i==4) mp.put("value", lis.getM5());
	        		if(i==5) mp.put("value", lis.getM6());
	        		if(i==6) mp.put("value", lis.getM7());
	        		if(i==7) mp.put("value", lis.getM8());
	        		if(i==8) mp.put("value", lis.getM9());
	        		if(i==9) mp.put("value", lis.getM10());
	        		if(i==10) mp.put("value", lis.getM11());
	        		if(i==11) mp.put("value", lis.getM12());
	        		li.add(mp);
				 }
				 vo.setList(li);
				 listvo.add(vo);
			 }
			 
			 result.put("list", listvo);
			 return result.setStatus(0, "ok");

//		 List li=new ArrayList();
//		 
//		 HashSet<Map> set=new HashSet<Map>();
//		 
//		 for(YLFuelStat stat:list){
//			 Map map=new HashMap();
//				map.put(stat.getFuelid(),stat.getFuelname());
//			 set.add(map);
//		 }
//		 
//		 for(Map map:set){
//			 YLFuelStatVo vo=new YLFuelStatVo();
//			 List listvo=new ArrayList();
//			 Set keySet = map.keySet();
//		        Iterator it = keySet.iterator();  
//		        while(it.hasNext()){  
//		            int k = (Integer) it.next(); 
//		            String v = (String) map.get(k);
//			        vo.setFuelid(k);
//			        vo.setFuelname(v);
//		        }
//		        for(YLFuelStat lis:list){
//		        	if(lis.getFuelid()==vo.getFuelid()){
//		        		Map mp=new HashMap();
//		        		mp.put("m1", lis.getM1());
//		        		mp.put("m2", lis.getM2());
//		        		mp.put("m3", lis.getM3());
//		        		mp.put("m4", lis.getM4());
//		        		mp.put("m5", lis.getM5());
//		        		mp.put("m6", lis.getM6());
//		        		mp.put("m7", lis.getM7());
//		        		mp.put("m8", lis.getM8());
//		        		mp.put("m9", lis.getM9());
//		        		mp.put("m10", lis.getM10());
//		        		mp.put("m11", lis.getM11());
//		        		mp.put("m12", lis.getM12());
//		        		listvo.add(mp);
//		        	}
//		        	
//		        }
//		        vo.setList(listvo);
//		        li.add(vo);
//		 }
//		 result.put("list", li);
//		 return result.setStatus(0, "ok");
		 }else{
			 return result.setStatus(-1, "无数据");
		 }
	}
	
	

}
