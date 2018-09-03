package com.xf.controller.stat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.gov.GovStat;
import com.xf.security.LoginManage;
import com.xf.service.stat.DetailOfComputeAllService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/detailsOfCompute")
public class DetailOfComputeAllController {
	@Autowired
	private DetailOfComputeAllService theService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	@RequestMapping(value = "/getdetails/{year}/{did}/{govname}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getDetails(@PathVariable int year, @PathVariable int did,
			@PathVariable String govname, @PathVariable int typeid, HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		System.out.println(year + " " + govname + " " + did);
		String tableName = "";
		if (govname.equals("畜禽养殖")) {
			tableName = "animal";
		} else if (govname.equals("氮肥施用")) {
			tableName = "nfertigation";
		} else if (govname.equals("秸秆和薪柴")) {
			tableName = "firewood";
		} else if (govname.equals("民用生活燃料")) {
			tableName = "dfuel";
		} else if (govname.equals("加油站")) {
			tableName = "gasstation";
		} else if (govname.equals("油库")) {
			tableName = "oildepot";
		} else if (govname.equals("汽修")) {
			tableName = "garage";
		} else if (govname.equals("干洗")) {
			tableName = "laundry";
		} else if (govname.equals("餐饮")) {
			tableName = "restaurant";
		} else if (govname.equals("道路扬尘")) {
			tableName = "roaddust";
		} else if (govname.equals("施工扬尘")) {
			tableName = "constructiondust";
		} else if (govname.equals("工程机械")) {
			tableName = "constructionmachine";
		} else if (govname.equals("农业机械")) {
			tableName = "agriculturemachine";
		} else if (govname.equals("船舶")) {
			tableName = "boat";
		} else if (govname.equals("飞机")) {
			tableName = "airplane";
		}else if (govname.equals("农药用量")) {
			tableName = "pesticide";
		}else if (govname.equals("垃圾场污水厂")) {
			tableName = "waste";
		}else if (govname.equals("房屋建筑")) {
			tableName = "build";
		}else if (govname.equals("能源消耗")) {
			tableName = "energyconsume";
		}

		if (!tableName.equals("")) {
			List<GovStat> list = null;
			if(typeid==1){
				list = theService.getDetails2(year, did, tableName);
			}else if(typeid==2){
				list = theService.getDetails(year, did, tableName);
			}
			result.put("res", list);
		} else {
			return result.setStatus(-2, "参数错误！");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/moving/{year}/{did}/{govname}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	private Object getMoveingDetails(@PathVariable int year,
			@PathVariable int did, @PathVariable String govname, @PathVariable int typeid,
			HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			String tableName = "";
			if (govname.equals("船舶")) {
				tableName = "boat";
			} else if (govname.equals("飞机")) {
				tableName = "airplane";
			} else if (govname.equals("工程机械")) {
				tableName = "constructionmachine";
			} else if (govname.equals("农业机械")) {
				tableName = "agriculturemachine";
			}
			
			List<GovStat> list=null;
			if(typeid==1){
				if (!tableName.equals("")) list = theService.getDetails2(year, did, tableName);
				if (govname.equals("机动车")) list = theService.moveDetails2(year, did);
			}else if(typeid==2){
				if (!tableName.equals("")) list = theService.getDetails(year, did, tableName);
				if (govname.equals("机动车")) list = theService.moveDetails(year, did);
			}
			
			result.put("res", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "Exception");
		}
		return result.setStatus(0, "ok");
	}
}
