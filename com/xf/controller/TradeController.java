package com.xf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import com.xf.entity.Company;
import com.xf.entity.Product;
import com.xf.entity.Trade;
import com.xf.entity.gov.Factor;
import com.xf.service.CompanyService;
import com.xf.service.ProductService;
import com.xf.service.TradeService;
import com.xf.service.gov.FactorService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/trade")
public class TradeController {

	@Autowired
	private TradeService tradeService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FactorService factorService;
	@Autowired
	private ResultObj result;

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable int id, HttpServletRequest request) {

		Trade trade = tradeService.getById(id);
		if (trade == null)
			return result.setStatus(1, "not exist.");
		result.put("trade", trade);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/code/{no}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable String no, HttpServletRequest request) {

		Trade trade = tradeService.getByNo(no);
		if (trade == null)
			return result.setStatus(1, "not exist.");
		result.put("trade", trade);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/parent/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByParent(@PathVariable int pid, HttpServletRequest request) {

		List<Trade> tList = tradeService.getByParent(pid);
		result.put("tradelist", tList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/level/{levelid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByLevel(@PathVariable int levelid,
			HttpServletRequest request) {

		List<Trade> tList = tradeService.getByLevel(levelid);
		result.put("tradelist", tList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/class/{classid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByClass(@PathVariable String classid,
			HttpServletRequest request) {

		List<Trade> tList = tradeService.getByClass(classid);
		result.put("tradelist", tList);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll(HttpServletRequest request) {

		List<Trade> tList = tradeService.getAll();
		result.put("tradelist", tList);

		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public Object getAll2(HttpServletRequest request) {

		
		List<Trade> leavel0 = tradeService.getByLevel(0);
		List<Trade> leavel1 = tradeService.getByLevel(1);
		List<Trade> leavel2 = tradeService.getByLevel(2);
		List<Trade> leavel3 = tradeService.getByLevel(3);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		for(Trade trade0:leavel0){
			Map<String,Object> map0 = new HashMap<String, Object>();
			map0.put("value", trade0.getId());
			map0.put("label", trade0.getTradeName());
			List<Map<String,Object>> leavel0List = new ArrayList<Map<String,Object>>();
			for(Trade trade1:leavel1){
				if(trade1.getParentId() == trade0.getId()){
					Map<String,Object> map1 = new HashMap<String, Object>();
					map1.put("value", trade1.getId());
					map1.put("label", trade1.getTradeName());
					List<Map<String,Object>> leavel1List = new ArrayList<Map<String,Object>>();
					for(Trade trade2:leavel2){
						if(trade2.getParentId() == trade1.getId()){
							Map<String,Object> map2 = new HashMap<String, Object>();
							map2.put("value", trade2.getId());
							map2.put("label", trade2.getTradeName());
							List<Map<String,Object>> leavel2List = new ArrayList<Map<String,Object>>();
							for(Trade trade3:leavel3){
								if(trade3.getParentId() == trade2.getId()){
									Map<String,Object> map3 = new HashMap<String, Object>();
									map3.put("value", trade3.getId());
									map3.put("label", trade3.getTradeName());
									leavel2List.add(map3);
								}								
							}
							map2.put("children", leavel2List);
							leavel1List.add(map2);
						}						
					}
					for(Trade trade3:leavel3){
						if(trade3.getParentId() == trade1.getId()){
							Map<String,Object> map3 = new HashMap<String, Object>();
							map3.put("value", trade3.getId());
							map3.put("label", trade3.getTradeName());
							leavel1List.add(map3);
						}								
					}
					map1.put("children", leavel1List);
					leavel0List.add(map1);
				}				
			}
			map0.put("children", leavel0List);
			list.add(map0);
		}
		
		result.put("list", list);

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/levelProductCount/{levelid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByLevelCount(@PathVariable int levelid,
			HttpServletRequest request) {
		HashSet<Integer> setg = new HashSet<Integer>();
		HashSet<Integer> setp = new HashSet<Integer>();

		List<Factor> fList = factorService.getAll();
		if (fList.size() > 0) {
			for (Factor fl : fList) {
				setp.add(fl.getProductId());
				setg.add(fl.getGroupid());
			}
		}

		List<Trade> tList = tradeService.getByLevel(levelid);
		List<Trade> tra = new ArrayList<Trade>();
		for (Trade tl : tList) {
			Trade t = new Trade();
			int count = 0;
			List<Company> cList = companyService.getCompanyCount(tl.getId());
			if (cList.size() > 0) {
				for (Company cl : cList) {
					List<Product> pList = productService.getByCompany(cl
							.getId());
					int num = 0;
					if (pList.size() > 0) {
						for (Product pl : pList) {
							num++;
							int coo = 0;
							if (setg.size() > 0) {
								for (Integer sg : setg) {
									if (sg == pl.getGroupid()) {
										coo = 1;
									}
								}
							}
							if (setp.size() > 0) {
								for (Integer sp : setp) {
									if (sp == pl.getId()&&pl.getId()==pl.getGroupid()) {
										coo = 1;
									}
								}
							}
							num -= coo;
						}
					}
					count += num;
				}
			}
			
			int countg=0;
			List<Product> gList=productService.getGroupBytrade(tl.getId());
			if(gList.size()>0){
				countg++;
				for(Product gl:gList){
					if (setg.size() > 0) {
						for (Integer sg : setg) {
							if (sg == gl.getGroupid()) {
								countg--;
							}
						}
					}
				}
			}
			t.setId(tl.getId());
			t.setParentId(tl.getParentId());
			t.setTradeClass(tl.getTradeClass());
			t.setTradeLevel(tl.getTradeLevel());
			t.setTradeName(tl.getTradeName());
			t.setTradeNo(tl.getTradeNo());
			t.setProductCount(count);
			t.setGroupCount(countg);
			tra.add(t);
		}
		result.put("tradelist", tra);

		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/parentProductCount/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByParentCount(@PathVariable int pid,
			HttpServletRequest request) {
		HashSet<Integer> setg = new HashSet<Integer>();
		HashSet<Integer> setp = new HashSet<Integer>();

		List<Factor> fList = factorService.getAll();
		List<Company> cList=companyService.getAll(0);
		List<Product> plist=productService.getAll();
		
		if (fList.size() > 0) {
			for (Factor fl : fList) {
				setp.add(fl.getProductId());
				setg.add(fl.getGroupid());
			}
		}

		List<Trade> tList = tradeService.getByParent(pid);
		List<Trade> tra = new ArrayList<Trade>();
		for (Trade tl : tList) {
			Trade t = new Trade();
			int count = 0;
			
			List<Company> comList = getCompany(cList,tl.getId());
			
			if (comList.size() > 0) {
				for (Company cl : comList) {
					List<Product> pList = getProduct(plist,cl.getId());
					int num = 0;
					if (pList.size() > 0) {
						for (Product pl : pList) {
							num++;
							int coo = 0;
							if (setg.size() > 0) {
								for (Integer sg : setg) {
									if (sg == pl.getGroupid()) {
										coo = 1;
									}
								}
							}
							if (setp.size() > 0) {
								for (Integer sp : setp) {
									if (sp == pl.getId()&&pl.getId()==pl.getGroupid()) {
										coo = 1;
									}
								}
							}
							num -= coo;
						}
					}
					count += num;
				}
			}
			
			int countg=0;
			List<Product> gList=productService.getGroupBytrade(tl.getId());
			if(gList.size()>0){
				for(Product gl:gList){
					countg++;
					if (setg.size() > 0) {
						for (Integer sg : setg) {
							if (sg == gl.getGroupid()) {
								countg-=1;
							}
						}
					}
				}
			}
			t.setId(tl.getId());
			t.setParentId(tl.getParentId());
			t.setTradeClass(tl.getTradeClass());
			t.setTradeLevel(tl.getTradeLevel());
			t.setTradeName(tl.getTradeName());
			t.setTradeNo(tl.getTradeNo());
			t.setProductCount(count);
			t.setGroupCount(countg);
			tra.add(t);
		}
		result.put("tradelist", tra);

		return result.setStatus(0, "");
	}
	
	public List<Company> getCompany(List<Company> list,int tradeid){
		List<Company> newlist=new ArrayList<Company>();
		for(Company c:list){
			if(c.getTrade1()==tradeid||c.getTrade2()==tradeid||c.getTrade3()==tradeid||c.getTrade4()==tradeid){
				newlist.add(c);
			}
		}
		return newlist;
	}
	
	public List<Product> getProduct(List<Product> list,int companyid){
		List<Product> newlist=new ArrayList<Product>();
		for(Product c:list){
			if(c.getEnterpriceId()==companyid){
				newlist.add(c);
			}
		}
		return newlist;
	}
}
