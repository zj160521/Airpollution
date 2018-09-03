package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.dao.gov.IProportionDao;
import com.xf.entity.Proportion;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.vo.MonthScale;

@Scope("prototype")
@Controller
@RequestMapping(value = "/proportion")
public class ProportionController {

	@Autowired
	private IProportionDao dao;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/setProportion", method = RequestMethod.POST)
	@ResponseBody
	public Object setProportion(HttpServletRequest request,@RequestBody MonthScale rData) {

		if (loginManage.isUserLogin(request)) {
			
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		
		List list = rData.getList();
		if(list.size() == 0){
			return result.setStatus(-3, "no data!");
		}
		List<Proportion> ptList = new ArrayList<Proportion>();
		for(int i=0; i<list.size(); i++){
			JSONObject jb = JSONObject.fromObject(list.get(i));
			String name = jb.getString("name");
			JSONArray ja = jb.getJSONArray("list");
			for(int j=0; j<ja.size(); j++){
				JSONObject jb2 = JSONObject.fromObject(ja.get(j));
				String strValue = jb2.getString("value");
				int value = 0;
				if(Tools.isInteger(strValue))
					value = Integer.parseInt(strValue);
				int month = jb2.getInt("month");
				Proportion pt = new Proportion();
				pt.setMonths(month);
				pt.setName(name);
				pt.setProportion(value);
				ptList.add(pt);
			}
		}
		
		dao.addup(ptList);
		
		return result.setStatus(0, "ok");
		
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {
			
		 } else {
			 return result.setStatus(-1, "No login.");
		 }
		
		List<Proportion> list =  dao.getAll();
		List<String> strList = new ArrayList<String>();
		strList.add("企业源-小散企业");
		strList.add("农业源-畜禽");
		strList.add("农业源-秸秆和薪柴");
		strList.add("农业源-氮肥");
		
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(String str:strList){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("name", str);
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			for(Proportion proportion:list){
				if(str.equals(proportion.getName())){
					Map<String,Object> dataMap = new HashMap<String, Object>();
					dataMap.put("month", proportion.getMonths());
					dataMap.put("value", proportion.getProportion());
					dataList.add(dataMap);
				}
			}
			if(dataList.size() == 0){
				for(int i = 1; i <= 12; i++){
					Map<String,Object> dataMap = new HashMap<String, Object>();
					dataMap.put("month", i);
					dataMap.put("value", 0);
					dataList.add(dataMap);
				}
			}
			map.put("list", dataList);
			mapList.add(map);
		}
		result.put("list", mapList);
		return result.setStatus(0, "ok");
		
	}
}
