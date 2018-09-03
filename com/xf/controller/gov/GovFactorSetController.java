package com.xf.controller.gov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.entity.gov.GovFactor;
import com.xf.service.gov.GovFactorService;
import com.xf.vo.GovFactorVo;

@Scope("prototype")
@Controller
@RequestMapping(value = "/govfactorset")
public class GovFactorSetController {

	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private GovFactorService service;

	private List<GovFactor> check_input(HttpServletRequest request) {
		
		List<GovFactor> list=new ArrayList<GovFactor>();

		for(int i=0;i<100;i++){
			
			if(request.getParameter("list["+i+"][list][0][value][0][type_x]")==null) break;
			
			for(int j=0;j<100;j++){
				if(request.getParameter("list["+i+"][list]["+j+"][value][0][type_x]")==null) break;
				for(int k=0;k<100;k++){
					if(request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][type_x]")==null) break;
					for(int l=0;l<100;l++){
						if(request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][listx2]["+l+"][type_x2]")==null) break;
						GovFactor ret = new GovFactor();
						String s = new String();
						s = request.getParameter("id");
						if (s != null && Tools.isInteger(s)) ret.setId(Integer.parseInt(s));
						ret.setTypename(request.getParameter("typename"));
						
						ret.setType_y(request.getParameter("list["+i+"][type_y]"));
						ret.setType_y2(request.getParameter("list["+i+"][list]["+j+"][type_y2]"));
						
						ret.setType_x(request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][type_x]"));
						
						ret.setType_x2(request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][listx2]["+l+"][type_x2]"));
						s = request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][listx2]["+l+"][factor]");
						if ( s ==null || s=="" || Double.parseDouble(s)<0) {
							ret.setFactor(0.0);
						}else
						if ( s !=null && s!="" && Tools.isNumeric(s) ){
							 ret.setFactor(Double.parseDouble(s));
						}
//						s = request.getParameter("list["+i+"][list]["+j+"][value]["+k+"][listx2]["+l+"][param]");
//						if (s != null && Tools.isNumeric(s)) ret.setParam(Double.parseDouble(s));
						list.add(ret);
					}
				 }

			}

		}
		return list;
	}


	// ==============================================================================


	@RequestMapping(value = "/addup", method = RequestMethod.POST)
	@ResponseBody
	public Object addup(HttpServletRequest request) {
		
		if (loginManage.isUserLogin(request)) {
			
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		
		List<GovFactor> list=check_input(request);
		
		if(list.size()<1) return result.setStatus(-2, "无储存对象");
		for(GovFactor gf:list){
			if(gf.getType_x() != null && gf.getType_y() != null && 			//xy不为空，x2y2为空
					gf.getType_x() != "" && gf.getType_y() != "" 
					&& (gf.getType_y2() ==null || gf.getType_y2()== "")
					&& (gf.getType_x2() ==null ||  gf.getType_x2() =="")){
				String sql1 = "update ap_govfactor set factor=" + gf.getFactor() + ",param="+gf.getParam() +
						" where type_x='"+gf.getType_x()+"' AND typename='"+gf.getTypename()+"' and type_y='"+gf.getType_y()+"'";
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("sql1", sql1);
				
				service.update(map1);

			}else if(gf.getType_x() != null && gf.getType_y() != null && //xyy2不为空，x2为空
					gf.getType_x() != "" && gf.getType_y() != "" 
					&& gf.getType_y2() != null && gf.getType_y2() != ""
					&& (gf.getType_x2() ==null ||  gf.getType_x2() =="")){
				String sql1 = "update ap_govfactor set factor=" + gf.getFactor() + ",param="+gf.getParam() +
						" where type_x='"+gf.getType_x()+"' AND typename='"+gf.getTypename()+"' and type_y='"+
						gf.getType_y()+"' and type_y2='"+gf.getType_y2()+"'";
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("sql1", sql1);
				
				service.update(map1);

			}else if(gf.getType_x() != null && gf.getType_y() != null && //xyx2不为空，y2为空
					gf.getType_x() != "" && gf.getType_y() != "" 
					&& gf.getType_x2() != null && gf.getType_x2() != ""
					&& (gf.getType_y2() ==null ||  gf.getType_y2() =="")){
				String sql1 = "update ap_govfactor set factor=" + gf.getFactor() + ",param="+gf.getParam() +
						" where type_x='"+gf.getType_x()+"' AND typename='"+gf.getTypename()+"' and type_y='"+
						gf.getType_y()+"' and type_x2='"+gf.getType_x2()+"'";
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("sql1", sql1);
				
				service.update(map1);

			}else if(gf.getType_x() != null && gf.getType_y() != null && 
					gf.getType_x() != "" && gf.getType_y() != ""
					&& gf.getType_y2() != null && gf.getType_y2() != ""
					&& gf.getType_x2() !=null &&  gf.getType_x2() !=""){
				String sql1 = "update ap_govfactor set factor=" + gf.getFactor() + ",param="+gf.getParam() +
						" where type_x='"+gf.getType_x()+"' AND typename='"+gf.getTypename()+"' and type_y='"+
						gf.getType_y()+"' and type_y2='"+gf.getType_y2()+"' and type_x2='"+gf.getType_x2()+"'";
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("sql1", sql1);
				
				service.update(map1);

			}else{
				return result.setStatus(-2, "x或y为空");
			}
		}
		return result.setStatus(0, "ok");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getdata/{typename}", method = RequestMethod.GET)
	@ResponseBody
	public Object getData(@PathVariable String typename,HttpServletRequest request) {
		
		if (loginManage.isUserLogin(request)) {
			
		 } else {
		 return result.setStatus(-1, "No login.");
		 }
		
		List<GovFactor> list=service.getByTypeName(typename);
		List<GovFactor> listxx2=service.getTypex(typename);
		List volist=new ArrayList();
		
		if(list != null){
			HashSet<String> set=new LinkedHashSet<String>();
			HashSet<String> set2=new LinkedHashSet<String>();
			for(GovFactor li:list){
					set.add(li.getType_y());
					set2.add(li.getType_y2());
			}
			HashSet<String> set3=new LinkedHashSet<String>();
			for(GovFactor li:listxx2){
					set3.add(li.getType_x());
			}

			if(set.size()<1) return result.setStatus(0, "无数据");
			
			//以type_y、type_y2作为出发点，构建y、y2、x、x2四层结构
			for(String ty:set){
					GovFactorVo vo=new GovFactorVo();  
					vo.setType_y(ty);
					List listvo=new ArrayList();

						for(String ty2:set2){
							Map map1=new HashMap();
							map1.put("type_y2", ty2);
							List listy2=new ArrayList();
							        //第一次list遍历，针对x只有单层结构
									for(GovFactor li:list){
										vo.setTypename(li.getTypename());
										//x2y2为空的情况
										if((li.getType_y2() == null || li.getType_y2() =="") && 
												(li.getType_x2() == null || li.getType_y2() =="")){
											if(li.getType_y().equals(ty)){
												Map mp=new HashMap();
												mp.put("type_x", li.getType_x());
												List listx2=new ArrayList();
												for(GovFactor lix2:list){
													if(lix2.getType_y().equals(ty)
															&& lix2.getType_x().equals(li.getType_x())){
														Map mpx2=new HashMap();
														mpx2.put("type_x2", lix2.getType_x2());
														mpx2.put("factor", lix2.getFactor());
														mpx2.put("param", lix2.getParam());
														listx2.add(mpx2);
													}

												}
												mp.put("listx2", listx2);
												listy2.add(mp);
											}
										}
										//x2为空y2不为空
										if(li.getType_y2() != null && li.getType_y2() !="" 
												&& (li.getType_x2()==null || li.getType_x2()=="")){
											if(li.getType_y().equals(ty) && li.getType_y2().equals(ty2)){
												Map mp=new HashMap();
												mp.put("type_x", li.getType_x());
												List listx2=new ArrayList();
												for(GovFactor lix2:list){
													if(lix2.getType_y().equals(ty) && lix2.getType_y2().equals(ty2)
															&& lix2.getType_x().equals(li.getType_x())){
														Map mpx2=new HashMap();
														mpx2.put("type_x2", lix2.getType_x2());
														mpx2.put("factor", lix2.getFactor());
														mpx2.put("param", lix2.getParam());
														listx2.add(mpx2);
													}

												}
												mp.put("listx2", listx2);
												listy2.add(mp);
											}
										  }
										} 
                                        //第二次遍历list，针对x轴存在两层结构，所以会遍历set3
										for(String typex:set3){
											int num=0;
												for(GovFactor li:list){
													vo.setTypename(li.getTypename());
													if(num==0){
													//x2y2都不为空	
													if(li.getType_y2() != null && li.getType_y2() !="" 
															&& li.getType_x2() != null && li.getType_x2()!= ""){

															if(li.getType_y().equals(ty) && li.getType_y2().equals(ty2) && li.getType_x().equals(typex)){
																Map mp=new HashMap();
																mp.put("type_x", li.getType_x());
																List listx2=new ArrayList();
																for(GovFactor lix2:list){
																	if(lix2.getType_y().equals(ty) && lix2.getType_y2().equals(ty2)
																			&& lix2.getType_x().equals(li.getType_x())){
																		Map mpx2=new HashMap();
																		mpx2.put("type_x2", lix2.getType_x2());
																		mpx2.put("factor", lix2.getFactor());
																		mpx2.put("param", lix2.getParam());
																		listx2.add(mpx2);
																	}

																}
																mp.put("listx2", listx2);
																listy2.add(mp);
																num++;
															}

													   }
													//x2不为空y2为空
													if((li.getType_y2()==null || li.getType_y2()=="")
															&& li.getType_x2()!=null && li.getType_x2()!=""){

																if(li.getType_y().equals(ty) && li.getType_x().equals(typex)){
																	Map mp=new HashMap();
																	mp.put("type_x", li.getType_x());
																	List listx2=new ArrayList();
																	for(GovFactor lix2:list){
																		if(lix2.getType_y().equals(ty)
																				&& lix2.getType_x().equals(li.getType_x())){
																			Map mpx2=new HashMap();
																			mpx2.put("type_x2", lix2.getType_x2());
																			mpx2.put("factor", lix2.getFactor());
																			mpx2.put("param", lix2.getParam());
																			listx2.add(mpx2);
																		}

																	}
																	mp.put("listx2", listx2);
																	listy2.add(mp);
																	num++;
																}
														}
											       }
											 }
										   }

									map1.put("value", listy2);
									listvo.add(map1);
								}
						vo.setList(listvo);
						volist.add(vo);	
							}
	
			}else{
				return result.setStatus(0, "no data!");
			}
		result.put("list", volist);
		result.put("typename", typename);
		return result.setStatus(0, "ok");

	}

}
