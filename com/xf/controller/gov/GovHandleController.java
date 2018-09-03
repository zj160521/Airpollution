package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.controller.ResultObj;
import com.xf.entity.AccountReport;
import com.xf.entity.User;
import com.xf.entity.gov.VgovTName;


import com.xf.security.LoginManage;
import com.xf.security.SqlSwitch;
import com.xf.service.AccountReportService;
import com.xf.service.gov.GovHandleService;
import com.xf.vo.GovHandle;
import com.xf.vo.GovHandlem;
import com.xf.vo.VgovHande;

@Scope("prototype")
@Controller
@RequestMapping(value = "/workflow")
public class GovHandleController {
	@Autowired
	private AccountReportService reportService;
	@Autowired
	private GovHandleService d;
	@Autowired
	private ResultObj result;
	@Autowired
	private LoginManage loginManage;
	
	@RequestMapping(value = "/gov/hands", method = RequestMethod.POST)
	@ResponseBody
	public Object getAllHand(HttpServletRequest request) {
		
		if (!loginManage.isUserLogin(request)) return result.setStatus(-1, "No login.");
		User u = loginManage.getLoginUser(request);
		
		String name=request.getParameter("name");
		String year=request.getParameter("fillyear");
		int fillyear=Integer.parseInt(year);
		String statu=request.getParameter("status");
		int status=Integer.parseInt(statu);
		
		String condition = "";
		String allName="";
		if (u.getTypeid() > 2 && u.getCity() > 0)
			condition = "and e.city=" + u.getCity();
		
		if(name!=null && name!="")
		allName="and (name like '%"+name+"%' or contact like '%"+name+"%' or (select typename from ap_accountype a where a.id=e.typeid) like '%"+name+"%' or (select districtName from ap_district d where city=d.id) like '%"+name+"%')";
		//得到所有表名
		List<VgovTName> list = d.getTName();
		List<AccountReport> reports=reportService.getAll();
		List<GovHandle> list1 = new ArrayList<GovHandle>();
		String sql = null;
		
		//查询出所有信息存进list
		for (VgovTName v : list) {
			Map<String, String> map = new HashMap<String, String>();
			sql = "select e.id,e.name,e.typeid,e.contact,e.contactNo,e.province,e.city,'" + v.getReportname() + "' as reportname,"
					+ "(select typename from ap_accountype a where a.id=e.typeid) as govname,"
					+ "(select districtName from ap_district d where province=d.id) as provinceName,"
					+ "(select districtName from ap_district d where city=d.id) as cityName,"
					+ "(select reportdesp from ap_account_report where e.typeid=typeid and reportname='"+v.getReportname()+"') as reportdesp,"
					+ "s.status,s.cnt "
					+ "from ap_enterprise e left join (select accountid,status,count(*) as cnt from "+v.getReportname()+" where fillyear="+fillyear+" group by accountid,status) s on e.id=s.accountid "
					+ "where e.typeid = "+v.getTypeid()+" " + condition + allName;
			map.put("sql", sql);

			List<GovHandle> gov = d.getAll(map);

			for(GovHandle gh:gov){
				for(AccountReport ar:reports){
					if(gh.getTypeid()==ar.getTypeid()&&ar.getReportname().equals(v.getReportname())){
						gh.setReportdesp(ar.getReportdesp());
						gh.setReportname(ar.getReportname());
					}
				}
			}
			if (gov.size() > 0) {
				for(GovHandle g:gov){
					list1.add(g);
				}
			}
		}
		
		//统计相同表名不同状态
		List<String> idandtname=new ArrayList<String>();
		for(GovHandle v:list1){
			idandtname.add(v.getId()+v.getReportdesp());
		}
		HashSet<String> itn = new HashSet<String>(idandtname);
		
		List<GovHandlem> listm=new ArrayList<GovHandlem>();
		for(String s:itn){
			GovHandlem gh=new GovHandlem();
			int nocert=0;
			int cert=0;
			for(GovHandle v:list1){
				if(s.equals(v.getId()+v.getReportdesp())){
					gh.setContact(v.getContact());
					gh.setContactNo(v.getContactNo());
					gh.setId(v.getId());
					gh.setName(v.getName());
					gh.setGovname(v.getGovname());
					gh.setCityName(v.getCityName());
					gh.setProvinceName(v.getProvinceName());
					gh.setReportdesp(v.getReportdesp());
					gh.setReportname(v.getReportname());
					gh.setTypeid(v.getTypeid());
					if(v.getStatus()==3){
						cert+=v.getCnt();
					}
					if(v.getStatus()==2){
						nocert+=v.getCnt();
					}
				}
			}
			gh.setCount(cert+nocert);
			gh.setCert(cert);
			gh.setNocert(nocert);
			listm.add(gh);
		}
		
		//创造出json对象
		List<String> list2=new ArrayList<String>();
		for(GovHandlem gm:listm){
			list2.add(gm.getId()+"");
		}
		HashSet<String> hs = new HashSet<String>(list2);
		List<VgovHande> list3=new ArrayList<VgovHande>();
		for(String s:hs){
			VgovHande vgovHande=new VgovHande();
			List<Map<String,String>> listmap=new ArrayList<Map<String,String>>();
			for(GovHandlem gm:listm){
				if(s.equals(gm.getId()+"")){
					Map<String,String> map=new HashMap<String,String>();
					vgovHande.setContact(gm.getContact());
					vgovHande.setContactNo(gm.getContactNo());
					vgovHande.setName(gm.getName());
					vgovHande.setId(gm.getId());
					vgovHande.setProvinceName(gm.getProvinceName());
					vgovHande.setCityName(gm.getCityName());
					vgovHande.setTypeid(gm.getTypeid());
					vgovHande.setGovname(gm.getGovname());
					map.put("reportdesp", gm.getReportdesp());
					map.put("reportname", gm.getReportname());
			        map.put("nocert", gm.getNocert()+"");
			        map.put("cert", gm.getCert()+"");
			        map.put("count", gm.getCount()+"");
			        listmap.add(map);
				}
			}
			vgovHande.setList(listmap);
			list3.add(vgovHande);
		}
		Set<VgovHande> list4=new LinkedHashSet<VgovHande>();
		if(status==0){
			for(VgovHande vgovHande:list3){
				for(Map<String,String> m:vgovHande.getList()){
					for(Map.Entry<String, String> entry : m.entrySet()){
						if(entry.getKey().equals("count")&&entry.getValue().equals("0")){
							list4.add(vgovHande);
							break;
						}
					}
				}
			}
		}else if(status==1){
			for(VgovHande vgovHande:list3){
				for(Map<String,String> m:vgovHande.getList()){
					for(Map.Entry<String, String> entry : m.entrySet()){
						if(entry.getKey().equals("nocert")&&!entry.getValue().equals("0")){
							list4.add(vgovHande);
							break;
						}
					}
				}
			}
		}else if(status==2){
			for(VgovHande vgovHande:list3){
				for(Map<String,String> m:vgovHande.getList()){
					for(Map.Entry<String, String> entry : m.entrySet()){
						if(entry.getKey().equals("cert")&&!entry.getValue().equals("0")){
							list4.add(vgovHande);
							break;
						}
					}
				}
			}
		}
		result.put("data", list4);
		return result.setStatus(0, "");
	}
}
