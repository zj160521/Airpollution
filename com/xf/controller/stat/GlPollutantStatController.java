package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
import com.xf.entity.Pollutant;
import com.xf.security.LoginManage;
import com.xf.service.PollutantService;
import com.xf.service.stat.GlPollutantStatService;
import com.xf.vo.DistrictStat;
import com.xf.vo.DistrictStatM;

@Scope("prototype")
@Controller
@RequestMapping(value = "/surface/glpollutantstat")
public class GlPollutantStatController {
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private GlPollutantStatService service;
	@Autowired
	private PollutantService polluService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{issmall}/getdata/{year}", method = RequestMethod.GET)
	@ResponseBody
	public Object stat(@PathVariable int year, @PathVariable int issmall, HttpServletRequest request) {

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
		// 由于DistrictStat、DistrictStatM两个VO对象符合此次锅炉污染物统计需求，所以采用他们作对象容器
		List<DistrictStat> list = service.getData(year,issmall,in);
		if (list == null || list.size() < 1)
			return result.setStatus(-2, "无统计数据");

		List<Pollutant> listpollu = polluService.getBasic();
		List<DistrictStatM> listgl = new ArrayList<DistrictStatM>();

		// 如果需统计的条件存在，每个市新建一个DistrictStatM对象，装进lists中

		if (listpollu != null && listpollu.size() > 0) {
			for (Pollutant poll : listpollu) {

				DistrictStatM glvo = new DistrictStatM();
				List listm = new ArrayList();
				glvo.setId(poll.getId());
				glvo.setName(poll.getPollutantName());

				// 循环所有的查询条件对象，得出单一的返回值，如果属于该市，pollutantid，pollutantname，total，放进DistrictStatM对象的map中

				LinkedHashSet<String> set = new LinkedHashSet<String>();
				for (DistrictStat glpo : list) {
					if (poll.getId() == glpo.getPollutantId()
							&& glpo.getId() < 1100) {
						set.add(glpo.getName());
					}
				}


				for (String glname : set) {
					Map map = new HashMap();
					map.put("glname", glname);
					int othertotal = 0;
					if(glname.equals("其它")){
						for (DistrictStat glpo : list) {
							if (poll.getId() == glpo.getPollutantId()
									&& glpo.getId() != 1001 ) {
								if (glpo.getName().equals("其它")
										|| glpo.getId() > 2000) {
									othertotal+=glpo.getTotal();
								}
							}
						}
						map.put("total", othertotal);
						listm.add(map);
					} else {
						for (DistrictStat glpo : list) {

							if (poll.getId() == glpo.getPollutantId()) {
								
								if (glpo.getName().equals(glname)){
									
									if (glpo.getGroupid() == 1 && glpo.getId() < 2000
											&& !glpo.getName().equals("其它")) {
										map.put("pid", glpo.getId());
										map.put("pname", glpo.getName());
										if(glpo.getId() == 1001){
											List<Map> listmap = new ArrayList<Map>();
											for (DistrictStat glpo2 : list) {
												if (poll.getId() == glpo2.getPollutantId()) {
													if (glpo2.getGroupid() == 4 && glpo2.getId() < 2000
															&& !glpo2.getName().equals("其它")) {
														Map map2 = new HashMap();													
														map2.put("pid", glpo2.getPid());
														map2.put("pname", glpo2.getName());
														map2.put("glid", glpo2.getId());
														map2.put("total", glpo2.getTotal());
														listmap.add(map2);
													} 
												}												
											}
											map.put("list", listmap);
										}
									} 

									map.put("glid", glpo.getId());
									map.put("total", glpo.getTotal());
									listm.add(map);
								}
								
								
							}
						}
					}
					
				}

				glvo.setList(listm);

				listgl.add(glvo);
			}
		} else {
			return result.setStatus(-1, "无污染物");
		}

		result.put("list", listgl);

		return result.setStatus(0, "");

	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getyears", method = RequestMethod.GET)
	@ResponseBody
	public Object years(HttpServletRequest request) {

		if (loginManage.isUserLogin(request)) {

		} else {
			return result.setStatus(-1, "No login.");
		}

		int startyear = 2015;

		Calendar cal = Calendar.getInstance();
		int curyear = cal.get(cal.YEAR);

		int num = curyear - startyear;
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i <= num; i++) {

			int years = 2015 + i;

			list.add(years);

		}

		result.put("list", list);

		return result.setStatus(0, "ok");

	}

}
