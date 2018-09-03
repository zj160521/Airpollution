package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

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
import com.xf.entity.CompanyProduct;
import com.xf.entity.CompanyProductList;
import com.xf.entity.Material;
import com.xf.entity.Pollutant;
import com.xf.entity.Product;
import com.xf.entity.ProductList;
import com.xf.entity.Static;
import com.xf.entity.gov.Factor;
import com.xf.security.LoginManage;
import com.xf.security.Tools;
import com.xf.service.MaterialService;
import com.xf.service.PollutantService;
import com.xf.service.ProductService;
import com.xf.service.StaticService;
import com.xf.service.gov.FactorService;
import com.xf.service.stat.ProductClassifyService;
import com.xf.vo.IntString;
import com.xf.vo.ProductTest;

@Scope("prototype")
@Controller
@RequestMapping(value = "/proclassify")
public class ProductClassifyController {
	@Autowired
	private ProductClassifyService theService;
	@Autowired
	private PollutantService pullService;
	@Autowired
	private FactorService factorService;
	@Autowired
	private ProductService proService;
	@Autowired
	private MaterialService maService;
	@Autowired
	private StaticService StaticService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	public static final int STATUS_NO = 0;
	public static final int STATUS_FILL = 1;
	public static final int STATUS_CHECKED = 2;

	private Object checkAccount(HttpServletRequest request) {
		if (!loginManage.isUserLogin(request)) {
			return result.setStatus(-1, "No login.");
		}
		return null;
	}

	// 计算类型
	@RequestMapping(value = "/factortype", method = RequestMethod.GET)
	@ResponseBody
	private Object factorType(HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			int productid = 0;
			int groupid = 0;
			String s = new String();

			s = request.getParameter("groupid");
			if (s != null && Tools.isInteger(s))
				productid = Integer.parseInt(s);

			Product product = proService.getById(productid);
			if (product != null && product.getIsgroup() == 1) {
				groupid = productid;
				productid = 0;
			}

			List<HashMap<String, Object>> facList = new ArrayList<HashMap<String, Object>>();

			List<Static> ftlist = StaticService.findType();
			if (ftlist != null) {
				for (Static ft : ftlist) {
					List<HashMap<String, Object>> maList = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> ftPany = new HashMap<String, Object>();
					ftPany.put("id", ft.getId());
					ftPany.put("typeId", ft.getUnit());
					ftPany.put("typeName", ft.getName());

					Product pro = proService.getById(productid);
					if (Integer.parseInt(ft.getUnit()) == 3) {
						List<IntString> list = maService
								.getMaterialFactor(productid);
						if (pro != null && pro.getGroupid() != pro.getId()) {
							List<Material> malist = maService
									.getGroupMaterial(pro.getGroupid());
							if (malist != null) {
								for (Material mal : malist) {
									HashMap<String, Object> maPany = new HashMap<String, Object>();
									List<Material> m = maService
											.getIdbyname(mal.getMaterialName());
									maPany.put("id", m.get(0).getId());
									maPany.put("name", mal.getMaterialName());
									maList.add(maPany);
								}
							}
						} else if (list.size() > 0 && pro != null
								&& pro.getGroupid() == pro.getId()) {
							for (IntString li : list) {
								HashMap<String, Object> maPany = new HashMap<String, Object>();
								maPany.put("id", li.getIt());
								maPany.put("name", li.getStr());
								maList.add(maPany);
							}
						}
						List<Material> malist = maService
								.getGroupMaterial(groupid);
						if (malist != null) {
							for (Material mal : malist) {
								HashMap<String, Object> maPany = new HashMap<String, Object>();
								List<Material> m = maService.getIdbyname(mal
										.getMaterialName());
								maPany.put("id", m.get(0).getId());
								maPany.put("name", mal.getMaterialName());
								maList.add(maPany);
							}
						}
					} else if (Integer.parseInt(ft.getUnit()) == 4) {
						List<Static> stlist = StaticService
								.getProductfuel(productid);
						if (stlist.size() > 0 && pro != null
								&& pro.getGroupid() == pro.getId()) {
							for (Static stl : stlist) {
								HashMap<String, Object> stPany = new HashMap<String, Object>();
								stPany.put("id", stl.getId());
								stPany.put("name", stl.getName());
								maList.add(stPany);
							}

						}
						if ((pro != null && pro.getId() != pro.getGroupid())
								|| groupid > 0) {
							List<Static> stalist = StaticService.getFuel();
							HashSet<String> set = new LinkedHashSet<String>();

							if (stalist != null) {
								for (Static sta : stalist) {
									set.add(sta.getName());
								}
							}

							for (String sett : set) {
								for (Static sta : stalist) {
									if (sta.getName().equals(sett)) {
										HashMap<String, Object> stPany = new HashMap<String, Object>();
										stPany.put("id", sta.getId());
										stPany.put("name", sta.getName());
										maList.add(stPany);
										break;
									}
								}
							}
						}
					}
					if (Integer.parseInt(ft.getUnit()) > 2)
						ftPany.put("list", maList);
					facList.add(ftPany);
				}
			}
			result.put("factorType", facList);

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/returnFactor", method = RequestMethod.GET)
	@ResponseBody
	private Object productClass(HttpServletRequest request) {
		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			int groupid = 0;
			String s = request.getParameter("groupid");
			if (s != null && Tools.isInteger(s))
				groupid = Integer.parseInt(s);

			ProductList list = new ProductList();
			Product product = proService.getById(groupid);
			if (product != null && product.getIsgroup() == 1) {
				list.setIsgroup(1);
				list.setProductName(null);
				list.setGroupName(product.getProductName());
			} else {
				list.setIsgroup(0);
				list.setProductName(product.getProductName());
				list.setGroupName(null);
			}
			List<HashMap<String, Object>> pfList = new ArrayList<HashMap<String, Object>>();
			List<Pollutant> polist = pullService.getTen();
			if (polist != null) {
				for (Pollutant poli : polist) {
					List<HashMap<String, Object>> facList = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> poPany = new HashMap<String, Object>();
					poPany.put("pollutantId", poli.getId());
					poPany.put("pollutantName", poli.getPollutantName());

					List<Factor> faclist = factorService.getProductfactor(
							groupid, poli.getId());
					if (faclist != null) {
						for (Factor facli : faclist) {
							HashMap<String, Object> facPany = new HashMap<String, Object>();
							facPany.put("typeid", facli.getTypeid());
							facPany.put("factor", facli.getFactor());
							facPany.put("typeid2", facli.getMaterialId());
							facPany.put("groupId", facli.getGroupid());
							facPany.put("formulaId", facli.getFormulaId());
							facList.add(facPany);
						}
					}
					poPany.put("formulas", facList);
					pfList.add(poPany);
				}
			}
			list.setList(pfList);
			result.put("factorList", list);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	// 添加产品组
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	@ResponseBody
	private Object addGroup(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			Product prg = new Product();
			String s = new String();

			s = request.getParameter("groupName");
			if (!s.isEmpty())
				prg.setProductName(s);
			s = request.getParameter("trade1");
			if (s != null && Tools.isInteger(s))
				prg.setTrade1(Integer.parseInt(s));
			s = request.getParameter("trade2");
			if (s != null && Tools.isInteger(s))
				prg.setTrade2(Integer.parseInt(s));
			s = request.getParameter("trade3");
			if (s != null && Tools.isInteger(s))
				prg.setTrade3(Integer.parseInt(s));
			s = request.getParameter("trade4");
			if (s != null && Tools.isInteger(s))
				prg.setTrade4(Integer.parseInt(s));

			if (prg != null) {
				if (theService.checkName(prg.getProductName()) != null)
					return result.setStatus(-2, "组名重复");

				theService.addGroup(prg);
				theService.updateGroup(prg);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	// 产品分组
	@RequestMapping(value = "/intoGroup", method = RequestMethod.POST)
	@ResponseBody
	private Object intoGroup(@RequestBody String products,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			if (products != null) {
				JSONObject objson = JSONObject.fromObject(products);
				if (objson.has("productId")) {
					List list = objson.getJSONArray("productId");

					if (objson.has("groupId")) {
						String id = objson.getString("groupId");
						int groupid = Integer.parseInt(id);
						for (int i = 0; i < list.size(); i++) {
							int j = Integer.parseInt(list.get(i).toString());
							theService.updatePGroup(0, j);
							theService.updatePGroup(groupid, j);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	// 修改产品组
	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
	@ResponseBody
	private Object updateGroup(@RequestBody ProductTest products,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			String s = new String();
			Product prg = new Product();

			if (products != null) {
				List<Integer> list = products.getProductId();
				if (list != null && list.size() > 0) {
					for (Integer id : list) {
						theService.updateNullByid(id, id);
					}
				}

				if (products.getGroupId() != null) {
					prg.setId(products.getGroupId());
				}
				
				if (products.getGroupName() != null) {
					prg.setProductName(products.getGroupName());
				}
				if (products.getRemark() != null) {
					prg.setRemark(products.getRemark());
				}
				
				if (products.getTrade1() != null) {
					prg.setTrade1(products.getTrade1());
				}
				if (products.getTrade2() != null) {
					prg.setTrade2(products.getTrade2());
				}
				if (products.getTrade3() != null) {
					prg.setTrade3(products.getTrade3());
				}
				if (products.getTrade4() != null) {
					prg.setTrade4(products.getTrade4());
				}
				if (prg != null && prg.getId() != 0)
					theService.updateGroup(prg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	// 删除产品组
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	@ResponseBody
	private Object deleteGroup(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			int groupid = 0;
			String s = new String();

			s = request.getParameter("groupId");
			if (s != null && Tools.isInteger(s))
				groupid = Integer.parseInt(s);

			theService.deleteGroup(groupid);
			factorService.deleteGrf(groupid);
			List<Product> plist = theService.findProBygid(groupid);
			if (plist.size() > 0) {
				for (Product pli : plist) {
					theService.updateNullByid(pli.getId(), pli.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	// 对单个产品进行添加因子
	@RequestMapping(value = "/addFactor", method = RequestMethod.POST)
	@ResponseBody
	private Object addFactor(@RequestBody String pfactor,
			HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			int k = 0;
			int product = 0;
			String s = new String();
			JSONObject objson = JSONObject.fromObject(pfactor);
			JSONArray glist = objson.getJSONArray("pollutantList");

			s = objson.getString("groupId");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				product = Integer.parseInt(s);

			Product pro = proService.getById(product);
			if (pro != null && pro.getIsgroup() == 0) {

				if (product > 0)
					factorService.deleteFacBypid(product);

				if (glist != null) {
					for (int i = 0; i < glist.size(); i++) {
						JSONObject pollutant = (JSONObject) glist.get(i);
						JSONArray flist = pollutant.getJSONArray("formulas");

						if (flist != null) {
							for (int j = 0; j < flist.size(); j++) {
								Factor factor = new Factor();
								JSONObject formulas = (JSONObject) flist.get(j);

								factor.setProductId(product);
								factor.setPollutantId(Integer
										.parseInt(pollutant
												.getString("pollutantId")));

								s = formulas.getString("typeid");
								if (s != null && Tools.isInteger(s))
									factor.setTypeid(Integer.parseInt(s));

								if (factor.getTypeid() > 2) {
									s = formulas.getString("typeid2");
									if (s != null && !s.isEmpty()
											&& Tools.isInteger(s))
										factor.setMaterialId(Integer
												.parseInt(s));
								}
								s = formulas.getString("factor");
								if (!s.isEmpty() && Tools.isNumeric(s))
									factor.setFactor(Double.parseDouble(s));
								if (factor.getFactor() != 0
										&& factor.getPollutantId() > 0)
									factorService.addFactor(factor);
							}
						}
					}
				}
			} else if (pro != null && pro.getIsgroup() == 1) {

				List<Factor> faList = new ArrayList<Factor>();
				String str = new String();

				if (product > 0)
					factorService.deleteGrf(product);

				if (glist != null) {
					for (int i = 0; i < glist.size(); i++) {
						JSONObject pollutant = (JSONObject) glist.get(i);
						JSONArray flist = pollutant.getJSONArray("formulas");

						if (flist != null) {
							for (int j = 0; j < flist.size(); j++) {
								Factor factor = new Factor();
								JSONObject formulas = (JSONObject) flist.get(j);

								factor.setPollutantId(Integer
										.parseInt(pollutant
												.getString("pollutantId")));
								factor.setGroupid(product);
								str = formulas.getString("typeid");
								if (!str.isEmpty() && Tools.isInteger(str))
									factor.setTypeid(Integer.parseInt(str));
								if (factor.getTypeid() > 2) {
									str = formulas.getString("typeid2");
									if (str != null && Tools.isInteger(str))
										factor.setMaterialId(Integer
												.parseInt(str));
								}
								str = formulas.getString("factor");
								if (!str.isEmpty())
									factor.setFactor(Double.parseDouble(str));
								if (factor.getFactor() != 0
										&& factor.getPollutantId() > 0)
									faList.add(factor);
							}
						}
					}
				}
				if (faList != null)
					factorService.addGroupfactor(faList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}

	@RequestMapping(value = "/CompanyProduct", method = RequestMethod.POST)
	@ResponseBody
	private Object findProduct(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		try {
			int groupid = 0;
			int tradeid = 0;
			int status = 0;
			int year=0;
			int districtid=0;
			int isPass = 0;

			String s = new String();

			String productName = request.getParameter("productName");
			s = request.getParameter("isgroup");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				groupid = Integer.parseInt(s);
			s = request.getParameter("tradeid");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				tradeid = Integer.parseInt(s);
			s = request.getParameter("status");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				status = Integer.parseInt(s);
			s = request.getParameter("year");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				year = Integer.parseInt(s);
			s = request.getParameter("districtid");
			if (s != null && !s.equals("") && Tools.isInteger(s))
				districtid = Integer.parseInt(s);

			if(groupid == 0){
				isPass =3;
			}
			HashSet<Integer> setg = new HashSet<Integer>();
			HashSet<Integer> setp = new HashSet<Integer>();
			HashSet<String> setc = new HashSet<String>();

			List<Factor> fList = factorService.getAll();
			if (fList.size() > 0) {
				for (Factor fl : fList) {
					setp.add(fl.getProductId());
					setg.add(fl.getGroupid());
				}
			}

			List<CompanyProduct> pList2 = new ArrayList<CompanyProduct>();
			List<CompanyProduct> pListAll = new ArrayList<CompanyProduct>();
			List<CompanyProduct> pList = proService.findProduct(groupid,
					productName, tradeid,year,districtid,isPass);
			if (pList.size() > 0) {
				for (CompanyProduct pl : pList) {
					setc.add(pl.getEnterpriceName());
				}
			}
			if (pList.size() > 0) {

				Iterator<CompanyProduct> itr = pList.iterator();
				while (itr.hasNext()) {
					itr.next().setStatus(STATUS_FILL);
				}

				itr = pList.iterator();
				while (itr.hasNext()) {
					CompanyProduct c = itr.next();
					if (setg.size() > 0) {
						for (Integer sg : setg) {
							if (sg == c.getGroupid()) {
								itr.remove();
								c.setStatus(STATUS_CHECKED);
								pList2.add(c);
								break;
							} else {
								c.setStatus(STATUS_FILL);
							}
						}
					}
				}

				itr = pList.iterator();
				while (itr.hasNext()) {
					CompanyProduct c = itr.next();
					if (setp.size() > 0) {
						for (Integer sp : setp) {
							if (c.getProductId() == c.getGroupid()
									&& sp == c.getProductId()) {
								itr.remove();
								c.setStatus(STATUS_CHECKED);
								pList2.add(c);
								break;
							} else {
								c.setStatus(STATUS_FILL);
							}
						}
					}
				}
			}
			if (groupid == 0) {
				switch (status) {
				case STATUS_FILL:
					List<CompanyProductList> cList = new ArrayList<CompanyProductList>();
					if (setc.size() > 0) {
						for (String sc : setc) {
							List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
							CompanyProductList cpl = new CompanyProductList();
							cpl.setCompanyName(sc);
							int j = 0;
							int companyId = 0;
							if (pList.size() > 0) {
								for (CompanyProduct pl : pList) {
									if (sc.equals(pl.getEnterpriceName())) {
										CompanyProduct comp = new CompanyProduct();
										comp.setProductId(pl.getProductId());
										comp.setProductName(pl.getProductName());
										comp.setStatus(pl.getStatus());
										comp.setGroupid(pl.getGroupid());
										comp.setGroupName(pl.getGroupName());
										comp.setTrade1(pl.getTrade1());
										comp.setTrade2(pl.getTrade2());
										comp.setTrade3(pl.getTrade3());
										comp.setTrade4(pl.getTrade4());
										complist.add(comp);
										companyId = pl.getId();
										j = 1;
									}
								}
							}
							cpl.setCompanyId(companyId);
							cpl.setList(complist);
							if (j == 1) {
								cList.add(cpl);
							}
						}
					}
					result.put("productList", cList);
					break;
				case STATUS_CHECKED:
					List<CompanyProductList> cList2 = new ArrayList<CompanyProductList>();
					if (setc.size() > 0) {
						for (String sc : setc) {
							List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
							CompanyProductList cpl = new CompanyProductList();
							cpl.setCompanyName(sc);
							int j = 0;
							int companyId = 0;
							if (pList2.size() > 0) {
								for (CompanyProduct pl : pList2) {
									if (sc.equals(pl.getEnterpriceName())) {
										CompanyProduct comp = new CompanyProduct();
										comp.setProductId(pl.getProductId());
										comp.setProductName(pl.getProductName());
										comp.setStatus(pl.getStatus());
										comp.setGroupid(pl.getGroupid());
										comp.setGroupName(pl.getGroupName());
										comp.setTrade1(pl.getTrade1());
										comp.setTrade2(pl.getTrade2());
										comp.setTrade3(pl.getTrade3());
										comp.setTrade4(pl.getTrade4());
										complist.add(comp);
										companyId = pl.getId();
										j = 1;
									}
								}
							}
							cpl.setCompanyId(companyId);
							cpl.setList(complist);
							if (j == 1) {
								cList2.add(cpl);
							}
						}
					}
					result.put("productList", cList2);
					break;
				default:
					pListAll.addAll(pList);
					pListAll.addAll(pList2);

					List<CompanyProductList> cListAll = new ArrayList<CompanyProductList>();
					if (setc.size() > 0) {
						for (String sc : setc) {
							List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
							CompanyProductList cpl = new CompanyProductList();
							cpl.setCompanyName(sc);
							int j = 0;
							int companyId = 0;

							if (pListAll.size() > 0) {
								for (CompanyProduct pl : pListAll) {
									if (sc.equals(pl.getEnterpriceName())) {
										CompanyProduct comp = new CompanyProduct();
										comp.setProductId(pl.getProductId());
										comp.setProductName(pl.getProductName());
										comp.setStatus(pl.getStatus());
										comp.setGroupid(pl.getGroupid());
										comp.setGroupName(pl.getGroupName());
										comp.setTrade1(pl.getTrade1());
										comp.setTrade2(pl.getTrade2());
										comp.setTrade3(pl.getTrade3());
										comp.setTrade4(pl.getTrade4());
										complist.add(comp);
										companyId = pl.getId();
										j = 1;
									}
								}
							}
							cpl.setCompanyId(companyId);
							cpl.setList(complist);
							if (j == 1) {
								cListAll.add(cpl);
							}
						}
					}
					result.put("productList", cListAll);
				}
			} else {
				switch (status) {
				case STATUS_FILL:
					List<CompanyProductList> cList = new ArrayList<CompanyProductList>();
					for (int i = 0; i < pList.size(); i++) {
						List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
						CompanyProductList cpl = new CompanyProductList();
						cpl.setCompanyName("group");
						int j = 0;
						if (pList.size() > 0) {
							for (int k = 0; k < pList.size(); k++) {
								if (k == i) {
									CompanyProduct comp = new CompanyProduct();
									CompanyProduct pl = pList.get(k);
									comp.setProductId(pl.getProductId());
									comp.setProductName(pl.getProductName());
									comp.setStatus(pl.getStatus());
									comp.setGroupid(pl.getGroupid());
									comp.setGroupName(pl.getGroupName());
									comp.setTrade1(pl.getTrade1());
									comp.setTrade2(pl.getTrade2());
									comp.setTrade3(pl.getTrade3());
									comp.setTrade4(pl.getTrade4());
									complist.add(comp);
									j = 1;
								}
							}
						}
						cpl.setList(complist);
						if (j == 1) {
							cList.add(cpl);
						}
					}
					result.put("productList", cList);
					break;
				case STATUS_CHECKED:
					List<CompanyProductList> cList2 = new ArrayList<CompanyProductList>();
					for (int i = 0; i < pList2.size(); i++) {
						List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
						CompanyProductList cpl = new CompanyProductList();
						cpl.setCompanyName("group");
						int j = 0;
						if (pList2.size() > 0) {
							for (int k = 0; k < pList2.size(); k++) {
								if (k == i) {
									CompanyProduct comp = new CompanyProduct();
									CompanyProduct pl = pList2.get(k);
									comp.setProductId(pl.getProductId());
									comp.setProductName(pl.getProductName());
									comp.setStatus(pl.getStatus());
									comp.setGroupid(pl.getGroupid());
									comp.setGroupName(pl.getGroupName());
									comp.setTrade1(pl.getTrade1());
									comp.setTrade2(pl.getTrade2());
									comp.setTrade3(pl.getTrade3());
									comp.setTrade4(pl.getTrade4());
									complist.add(comp);
									j = 1;
								}
							}
						}
						cpl.setList(complist);
						if (j == 1) {
							cList2.add(cpl);
						}
					}
					result.put("productList", cList2);
					break;
				default:
					pListAll.addAll(pList);
					pListAll.addAll(pList2);

					List<CompanyProductList> cListAll = new ArrayList<CompanyProductList>();
					for (int i = 0; i < pListAll.size(); i++) {
						List<CompanyProduct> complist = new ArrayList<CompanyProduct>();
						CompanyProductList cpl = new CompanyProductList();
						cpl.setCompanyName("group");
						int j = 0;
						if (pListAll.size() > 0) {
							for (int k = 0; k < pListAll.size(); k++) {
								if (k == i) {
									CompanyProduct comp = new CompanyProduct();
									CompanyProduct pl = pListAll.get(k);
									comp.setProductId(pl.getProductId());
									comp.setProductName(pl.getProductName());
									comp.setStatus(pl.getStatus());
									comp.setGroupid(pl.getGroupid());
									comp.setGroupName(pl.getGroupName());
									comp.setMaterialId(pl.getMaterialId());
									comp.setMaterialName(pl.getMaterialName());
									comp.setTrade1(pl.getTrade1());
									comp.setTrade2(pl.getTrade2());
									comp.setTrade3(pl.getTrade3());
									comp.setTrade4(pl.getTrade4());
									complist.add(comp);
									j = 1;
								}
							}
						}
						cpl.setList(complist);
						if (j == 1) {
							cListAll.add(cpl);
						}
					}
					result.put("productList", cListAll);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}
		return result.setStatus(0, "");
	}
}
