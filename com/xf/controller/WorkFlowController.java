package com.xf.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.dao.ISfacilityDao;
import com.xf.entity.Company;
import com.xf.entity.CompanyFill;
import com.xf.entity.Condition;
import com.xf.entity.ControlFacility;
import com.xf.entity.DevFill;
import com.xf.entity.Devices;
import com.xf.entity.Elec;
import com.xf.entity.Material;
import com.xf.entity.Outlet;
import com.xf.entity.ProduceStep;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;
import com.xf.entity.Searchform;
import com.xf.entity.SmallFacility;
import com.xf.entity.User;
import com.xf.entity.gov.AccountStat;
import com.xf.security.LoginManage;
import com.xf.security.PageHelper;
import com.xf.security.Tools;
import com.xf.service.CompanyFillService;
import com.xf.service.CompanyService;
import com.xf.service.ConfigService;
import com.xf.service.ControlFacilityService;
import com.xf.service.DevFillService;
import com.xf.service.DeviceService;
import com.xf.service.ElecFillService;
import com.xf.service.ElecService;
import com.xf.service.FacilityFillService;
import com.xf.service.MaterialFillService;
import com.xf.service.MaterialService;
import com.xf.service.OutletFillService;
import com.xf.service.OutletService;
import com.xf.service.ProduceStepService;
import com.xf.service.ProductFillService;
import com.xf.service.ProductService;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.BoatService;
import com.xf.service.gov.CanyinCertifiedService;
import com.xf.service.gov.CanyinNocertService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.CleanerService;
import com.xf.service.gov.ConstructionDustService;
import com.xf.service.gov.ConstructionService;
import com.xf.service.gov.DumpFieldService;
import com.xf.service.gov.EnergyConsumeService;
import com.xf.service.gov.EnergySellService;
import com.xf.service.gov.EquipmentFarmService;
import com.xf.service.gov.EquipmentService;
import com.xf.service.gov.FirewoodService;
import com.xf.service.gov.GasstationService;
import com.xf.service.gov.GknumberService;
import com.xf.service.gov.HouseholdFuelService;
import com.xf.service.gov.NfertilizerService;
import com.xf.service.gov.OildepotService;
import com.xf.service.gov.PesticideService;
import com.xf.service.gov.PlaneService;
import com.xf.service.gov.RoadDustService;
import com.xf.service.gov.VehicleActionService;
import com.xf.service.gov.VehicleRepairingService;
import com.xf.service.stat.DetailsOfindustryService;
import com.xf.vo.DetailsOfindustry;
import com.xf.vo.IntString;

@Scope("prototype")
@Controller
@RequestMapping(value = "/workflow")
public class WorkFlowController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyFillService cfillService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private DetailsOfindustryService detailService;
	@Autowired
	private MaterialService materialService;

	public static final int STATUS_INIT = 0;
	public static final int STATUS_FILL = 1;
	public static final int STATUS_UNCHECK = 2;
	public static final int STATUS_CHECKED = 3;

	@Autowired
	private DeviceService devService;
	@Autowired
	private ElecService elecService;
	@Autowired
	private ControlFacilityService facilityService;
	@Autowired
	private OutletService outletService;
	@Autowired
	private ProduceStepService stepService;

	@Autowired
	private DevFillService devfillService;
	@Autowired
	private ElecFillService elecfillService;
	@Autowired
	private FacilityFillService ffillService;
	@Autowired
	private MaterialFillService mfillService;
	@Autowired
	private OutletFillService ofillService;
	@Autowired
	private ProductFillService pfillService;

	@Autowired
	private AnimalsFarmService animalFarmService;
	@Autowired
	private AnimalsWildService animalWildService;
	@Autowired
	private BoatService boatService;
	@Autowired
	private CanyinCertifiedService canyinCertService;
	@Autowired
	private CanyinNocertService canyinNocertService;
	@Autowired
	private CanyinStatService canyinStatService;
	@Autowired
	private CleanerService cleanerService;
	@Autowired
	private ConstructionDustService cDustService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentFarmService equipmentFarmService;
	@Autowired
	private FirewoodService firewoodService;
	@Autowired
	private GasstationService gasstationService;
	@Autowired
	private GknumberService gknumberService;
	@Autowired
	private HouseholdFuelService houseFuelService;
	@Autowired
	private NfertilizerService nfertilizerService;
	@Autowired
	private OildepotService oilportService;
	@Autowired
	private PlaneService planeService;
	@Autowired
	private RoadDustService roadDustService;
	@Autowired
	private VehicleActionService vehicleActionService;
	@Autowired
	private VehicleRepairingService vehicleService;
	@Autowired
	private EnergyConsumeService ConsumeService;
	@Autowired
	private EnergySellService SellService;
	@Autowired
	private PesticideService pesticideService;
	@Autowired
	private ConstructionService constructionService;
	@Autowired
	private DumpFieldService dumpService;
	@Autowired
	private ISfacilityDao sfacilityDao;
	@Autowired
	private ProductService pService;

	private Company c;

	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	@ResponseBody
	public Object fillCommit(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;
		companyService.updateRemark(c.getId());

		return changeStatus2(c.getId(), STATUS_UNCHECK, STATUS_FILL);
	}

	@RequestMapping(value = "/checked/{fillyear}", method = RequestMethod.POST)
	@ResponseBody
	public Object fillChecked(HttpServletRequest request, @PathVariable int fillyear) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");

		if (u.getTypeid() > 1) {
			return changeStatus3(c.getId(), STATUS_CHECKED, STATUS_UNCHECK, fillyear);
		} else {
			return changeStatus(c.getId(), STATUS_CHECKED, fillyear);
		}
	}

	@RequestMapping(value = "/rollback", method = RequestMethod.POST)
	@ResponseBody
	public Object fillRollback(HttpServletRequest request) {

		Object ret = checkAccount(request);
		if (ret != null)
			return ret;

		int fillyear = 0;
		String s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s))
			fillyear = Integer.parseInt(s);

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");

		//管理员都具有把已通过退回的权力
		if (u.getTypeid() > 1) {
//			return changeStatus3(c.getId(), STATUS_FILL, STATUS_UNCHECK, fillyear);
			return changeStatus(c.getId(), STATUS_FILL, fillyear);
		} else {
			return changeStatus(c.getId(), STATUS_FILL, fillyear);
		}
	}

	@RequestMapping(value = "/small/update", method = RequestMethod.POST)
	@ResponseBody
	public Object smcompanyUpate(HttpServletRequest request, @RequestBody Company com) {

		int companyId = 0;
		if (loginManage.isUserLogin(request)){
			companyId = com.getId();
		}else if(loginManage.isCompanyLogin(request)){
			Company company = loginManage.getLoginCompany(request);
			companyId = company.getId();
		}

		cfillService.delBycomIdYear(companyId, com.getFillyear());
		devService.delBycomIdYear(companyId, com.getFillyear());
		pfillService.delBycomIdYear(companyId, com.getFillyear());
		sfacilityDao.delBycomIdYear(companyId, com.getFillyear());
		if(com.getClist() != null){
			com.getClist().setEnterpriceId(companyId);
			com.getClist().setFillyear(com.getFillyear());
			com.getClist().setAll_status(1);
			cfillService.add(com.getClist());
		}
		if(com.getDlist()!=null){
			List<Devices> list=com.getDlist();
			for(Devices device:list){
				device.setEnterpriceId(companyId);
				device.setFillyear(com.getFillyear());
				device.setRemark(device.getFuelcost()+"");
				
				List<Devices> devicelist=devService.getByCompanyId(device.getEnterpriceId());
				if(devicelist.size()>0){
					String s=devicelist.get(devicelist.size()-1).getDeviceSerial();
					String[] sz=s.split("L");
					int num=Integer.parseInt(sz[1]);
					num+=1;
					device.setDeviceSerial("GL"+num);
				}else{
					device.setDeviceSerial("GL1");
				}
				device.setFillyear(com.getFillyear());
				if(device.getDeviceModel() != null && device.getDeviceModel() != "")
					devService.add(device);	
			}
		}
		if(com.getPlist()!=null){
			List<ProductFill> list=com.getPlist();
			for(ProductFill pf:list){
				pf.setFillyear(com.getFillyear());
				pf.setEnterpriceId(companyId);
                if(pf.getMaterialId()>0){
                	pf.setProduceStepId(pf.getMaterialId());
                	if(pf.getProductId()>0){
                		if(pf.getRealOutput()>0)
        					pfillService.add(pf);
                		Product p = new Product();
                		p.setUnit(pf.getProductUnit());
                		p.setId(pf.getProductId());
                		p.setProductName(pf.getProductName());
                		pService.update(p);
    				}else{
    					Product p = new Product();
    					p.setEnterpriceId(companyId);
    					p.setProductName(pf.getProductName());
    					p.setUnit(pf.getProductUnit());
    					if(pf.getProductName() != null && pf.getProductName() != "")
    						pService.add(p);
    					List<Product> prdList = pService.getByCompany(companyId);
    					int prdId = prdList.get(prdList.size()-1).getId();
    					pf.setProductId(prdId);
    					if(pf.getRealOutput()>0)
        					pfillService.add(pf);
    				}
				}else{
					Material m = new Material();
					m.setEnterpriceId(companyId);
					m.setMaterialName(pf.getMaterialName());
					m.setFillyear(com.getFillyear());
					if(pf.getMaterialName() != null && pf.getMaterialName() != "")
						materialService.add(m);
					List<Material>  mtrList = materialService.getByCompany(companyId);
					int mtrId = mtrList.get(mtrList.size()-1).getId();
					pf.setProduceStepId(mtrId);
					if(pf.getProductId()>0){
						if(pf.getRealOutput()>0)
        					pfillService.add(pf);
						Product p = new Product();
                		p.setUnit(pf.getProductUnit());
                		p.setId(pf.getProductId());
                		p.setProductName(pf.getProductName());
                		pService.update(p);
    				}else{
    					Product p = new Product();
    					p.setEnterpriceId(companyId);
    					p.setProductName(pf.getProductName());
    					p.setUnit(pf.getProductUnit());
    					if(pf.getProductName() != null && pf.getProductName() != "")
    						pService.add(p);
    					List<Product> prdList = pService.getByCompany(companyId);
    					int prdId = prdList.get(prdList.size()-1).getId();
    					pf.setProductId(prdId);
    					if(pf.getRealOutput()>0)
        					pfillService.add(pf);
    				}
				}
			}
		}
		if(com.getSlist()!=null){
			List<SmallFacility> list=com.getSlist();
			for(SmallFacility sf:list){
				sf.setFillyear(com.getFillyear());
				sf.setEnterpriceId(companyId);
					if(sf.getTechnique1()+sf.getTechnique2()+sf.getTechnique3()+sf.getTechnique4()>0)
						sfacilityDao.add(sf);
			}
		}
		return result.setStatus(0, "");
	}
	
	@RequestMapping(value = "/small/checked", method = RequestMethod.POST)
	@ResponseBody
	public Object smfillChecked(HttpServletRequest request, @RequestBody Company com) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		int fillyear = com.getFillyear();
		
		for(Integer id:com.getCompanyId()){
			CompanyFill cf=new CompanyFill();
			cf.setFillyear(fillyear);
			cf.setEnterpriceId(id);
			CompanyFill cfill=cfillService.getbyCompanyYear(cf);
			if(cfill!=null){
				int ids[]={id};
				if (u.getTypeid() > 1) {
					changeStatusByid3(ids, STATUS_CHECKED, STATUS_UNCHECK, fillyear, 1);
				} else {
					changeStatusByid(ids, STATUS_CHECKED, fillyear, 1);
				}
			}
			
			List<DevFill> dlist=devfillService.getByCompanyId(id, fillyear, 0);
			if(dlist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(DevFill d:dlist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
					changeStatusByid3(ids, STATUS_CHECKED, STATUS_UNCHECK, fillyear, 4);
				} else {
					changeStatusByid(ids, STATUS_CHECKED, fillyear, 4);
				}
			}
			
			List<SmallFacility> sflist=sfacilityDao.getByCompanyYear(id, fillyear);
			if(sflist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(SmallFacility d:sflist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
					changeStatusByid3(ids, STATUS_CHECKED, STATUS_UNCHECK, fillyear, 3);
				} else {
					changeStatusByid(ids, STATUS_CHECKED, fillyear, 3);
				}
			}
			
			List<ProductFill> pflist=pfillService.getByYear(fillyear, id);
			if(pflist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(ProductFill d:pflist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
					changeStatusByid3(ids, STATUS_CHECKED, STATUS_UNCHECK, fillyear, 2);
				} else {
					changeStatusByid(ids, STATUS_CHECKED, fillyear, 2);
				}
			}
		}

		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/small/rollback", method = RequestMethod.POST)
	@ResponseBody
	public Object smfillRollback(HttpServletRequest request, @RequestBody Company com) {

		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		int fillyear = com.getFillyear();
		
		for(Integer id:com.getCompanyId()){
			CompanyFill cf=new CompanyFill();
			cf.setFillyear(fillyear);
			cf.setEnterpriceId(id);
			CompanyFill cfill=cfillService.getbyCompanyYear(cf);
			if(cfill!=null){
				int ids[]={id};
				if (u.getTypeid() > 1) {
//					changeStatusByid3(ids, STATUS_FILL, STATUS_UNCHECK, fillyear, 1);
					changeStatusByid(ids, STATUS_FILL, fillyear, 1);
				} else {
					changeStatusByid(ids, STATUS_FILL, fillyear, 1);
				}
			}
			
			List<DevFill> dlist=devfillService.getByCompanyId(id, fillyear, 0);
			if(dlist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(DevFill d:dlist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
//					changeStatusByid3(ids, STATUS_FILL, STATUS_UNCHECK, fillyear, 4);
					changeStatusByid(ids, STATUS_FILL, fillyear, 4);
				} else {
					changeStatusByid(ids, STATUS_FILL, fillyear, 4);
				}
			}
			
			List<SmallFacility> sflist=sfacilityDao.getByCompanyYear(id, fillyear);
			if(sflist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(SmallFacility d:sflist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
//					changeStatusByid3(ids, STATUS_FILL, STATUS_UNCHECK, fillyear, 3);
					changeStatusByid(ids, STATUS_FILL, fillyear, 3);
				} else {
					changeStatusByid(ids, STATUS_FILL, fillyear, 3);
				}
			}
			
			List<ProductFill> pflist=pfillService.getByYear(fillyear, id);
			if(pflist.size()>0){
				List<Integer> idlist=new ArrayList<Integer>();
				for(ProductFill d:pflist){
					idlist.add(d.getId());
				}
				int size=idlist.size();
				int ids[]=new int[size];
				for(int i=0;i<size;i++){
					ids[i]=idlist.get(i);
				}
				if (u.getTypeid() > 1) {
//					changeStatusByid3(ids,STATUS_FILL, STATUS_UNCHECK, fillyear, 2);
					changeStatusByid(ids, STATUS_FILL, fillyear, 2);
				} else {
					changeStatusByid(ids, STATUS_FILL, fillyear, 2);
				}
			}
		}
		
		
		return result.setStatus(0, "ok");
	}

	@RequestMapping(value = "/company/status", method = RequestMethod.POST)
	@ResponseBody
	public Object getCompanyStatus(HttpServletRequest request) {
		Condition con = new Condition();
		Searchform searchform  = new Searchform();
		String s = new String();
		int num = 0;
		s = request.getParameter("companyName");
		if (s != null && !s.isEmpty() && !s.equals("undefined"))
			con.setCompanyName(s);

		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setFillyear(Integer.parseInt(s));

		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setProvince(Integer.parseInt(s));

		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setCityid(Integer.parseInt(s));

		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTownid(Integer.parseInt(s));

		s = request.getParameter("tradeid");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid1(Integer.parseInt(s));

		s = request.getParameter("tradeid2");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid2(Integer.parseInt(s));

		s = request.getParameter("tradeid3");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid3(Integer.parseInt(s));

		s = request.getParameter("tradeid4");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid4(Integer.parseInt(s));

		s = request.getParameter("num");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			num = Integer.parseInt(s);
		
		s = request.getParameter("cur_page");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			searchform.setCur_page(Integer.parseInt(s));
		
		s = request.getParameter("page_rows");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			searchform.setPage_rows(Integer.parseInt(s));
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		User u = loginManage.getLoginUser(request);
		return companyStatusList(num, u, con, searchform);
	}

	@RequestMapping(value = "/small/status", method = RequestMethod.POST)
	@ResponseBody
	public Object companyStatus(HttpServletRequest request) {
		Condition con = new Condition();
		String s = new String();
		int num = 0;
		s = request.getParameter("companyName");
		if (s != null && !s.isEmpty() && !s.equals("undefined"))
			con.setCompanyName(s);

		s = request.getParameter("fillyear");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setFillyear(Integer.parseInt(s));

		s = request.getParameter("province");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setProvince(Integer.parseInt(s));

		s = request.getParameter("city");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setCityid(Integer.parseInt(s));

		s = request.getParameter("town");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTownid(Integer.parseInt(s));

		s = request.getParameter("tradeid");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid1(Integer.parseInt(s));

		s = request.getParameter("tradeid2");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid2(Integer.parseInt(s));

		s = request.getParameter("tradeid3");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid3(Integer.parseInt(s));

		s = request.getParameter("tradeid4");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setTradeid4(Integer.parseInt(s));

		s = request.getParameter("type");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			con.setType(Integer.parseInt(s));

		s = request.getParameter("num");
		if (s != null && Tools.isInteger(s) && !s.equals("undefined"))
			num = Integer.parseInt(s);

//		if (!loginManage.isUserLogin(request))
//			return result.setStatus(-1, "No login.");
		if (loginManage.isUserLogin(request)){
			User u = loginManage.getLoginUser(request);
			return smallCompanyStatus(num, u, con,0);
		}else{
			Company com = loginManage.getLoginCompany(request);
			User u = new User();
			u.setTypeid(com.getTypeid());
			u.setCity(com.getCity());
			u.setTown(com.getTown());
			return smallCompanyStatus(num, u, con,com.getId());
		}
	}

	@RequestMapping(value = "/small/changeStatus", method = RequestMethod.GET)
	@ResponseBody
	public Object changeStatus(HttpServletRequest request) {
		Company com = loginManage.getLoginCompany(request);
		cfillService.changeStatus(com.getId());
		return result.setStatus(0, "ok");
	}
	@RequestMapping(value = "/small/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestBody IntString is) {
		if(is.getStr().equals("1")){
			pfillService.deletepfill(is.getIt());
		}else if(is.getStr().equals("2")){
			devService.delete(is.getIt());
		}else if(is.getStr().equals("3")){
			sfacilityDao.delete(is.getIt());
		}
		return result.setStatus(0, "ok");
	}

	private Object checkAccount(HttpServletRequest request) {
		if (loginManage.isCompanyLogin(request)) {
			c = loginManage.getLoginCompany(request);
		} else if (loginManage.isUserLogin(request)) {
			String s = request.getParameter("accountid");
			if (s != null && Tools.isInteger(s)) {
				int accountid = Integer.parseInt(s);
				c = companyService.getById(accountid);
			} else
				return result.setStatus(-1, "need accountid.");
		} else {
			return result.setStatus(-1, "No login.");
		}

		return null;
	}

	private void changeStatusByid(int[] id, int status, int fillyear, int type) {
		try {
			for (int i = 0; i < id.length; i++) {
				switch (type) {
				case 1:
					cfillService.smallSetstatus(status, id[i], fillyear);
					break;
				case 2:
					pfillService.smallSetstatus(status, id[i], fillyear);
					break;
				case 3:
					sfacilityDao.setstatus(status, id[i], fillyear);
					break;
				case 4:
					devfillService.smallSetstatus(status, id[i], fillyear);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void changeStatusByid3(int[] id, int status, int originStatus, int fillyear, int type) {
		try {
			for (int i = 0; i < id.length; i++) {
				switch (type) {
				case 1:
					cfillService.smallSetstatus3(status, id[i], originStatus, fillyear);
					break;
				case 2:
					pfillService.smallSetstatus3(status, id[i], originStatus, fillyear);
					break;
				case 3:
					sfacilityDao.setstatus3(status, id[i], originStatus, fillyear);
					break;
				case 4:
					devfillService.smallSetstatus3(status, id[i], originStatus, fillyear);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object changeStatus(int companyid, int status, int fillyear) {

		try {
			switch (c.getTypeid()) {
			case 2:
				cfillService.setstatus(status, companyid, fillyear);
				devfillService.setstatus(status, companyid, fillyear);
				elecService.setstatus(status, companyid, fillyear);
				elecfillService.setstatus(status, companyid, fillyear);
				ffillService.setstatus(status, companyid, fillyear);
				mfillService.setstatus(status, companyid, fillyear);
				ofillService.setstatus(status, companyid, fillyear);
				pfillService.setstatus(status, companyid, fillyear);
				break;
			case 5:
				nfertilizerService.setstatus(status, companyid, fillyear);
				break;
			case 6:
				equipmentFarmService.setstatus(status, companyid, fillyear);
				firewoodService.setstatus(status, companyid, fillyear);
				pesticideService.setstatus(status, companyid, fillyear);
				nfertilizerService.setstatus(status, companyid, fillyear);
				break;
			case 7:
				roadDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				break;
			case 8:
				constructionService.setstatus(status, companyid, fillyear);
				cDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				break;
			case 9:
				SellService.setstatus(status, companyid, fillyear);
				ConsumeService.setstatus(status, companyid, fillyear);
				break;
			case 10:
				cleanerService.setstatus(status, companyid, fillyear);
				houseFuelService.setstatus(status, companyid, fillyear);
				break;
			case 11:
				cleanerService.setstatus(status, companyid, fillyear);
				break;
			case 13:
				SellService.setstatus(status, companyid, fillyear);
				break;
			case 14:
				vehicleService.setstatus(status, companyid, fillyear);
				boatService.setstatus(status, companyid, fillyear);
				break;
			case 15:
				boatService.setstatus(status, companyid, fillyear);
				break;
			case 17:
				equipmentFarmService.setstatus(status, companyid, fillyear);
				break;
			case 18:
				SellService.setstatus(status, companyid, fillyear);
				break;
			case 19:
				ConsumeService.setstatus(status, companyid, fillyear);
				SellService.setstatus(status, companyid, fillyear);
				houseFuelService.setstatus(status, companyid, fillyear);
				break;
			case 20:
				constructionService.setstatus(status, companyid, fillyear);
				cDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				break;
			case 22:
				SellService.setstatus(status, companyid, fillyear);
				break;
			case 23:
				pesticideService.setstatus(status, companyid, fillyear);
				break;
			case 25:
				constructionService.setstatus(status, companyid, fillyear);
				cDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				roadDustService.setstatus(status, companyid, fillyear);
				break;
			case 26:
				constructionService.setstatus(status, companyid, fillyear);
				cDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				break;
			case 27:
				vehicleService.setstatus(status, companyid, fillyear);
				break;
			case 28:
				firewoodService.setstatus(status, companyid, fillyear);
				pesticideService.setstatus(status, companyid, fillyear);
				break;
			case 29:
				ConsumeService.setstatus(status, companyid, fillyear);
				SellService.setstatus(status, companyid, fillyear);
				houseFuelService.setstatus(status, companyid, fillyear);
				break;
			case 31:
				
				
				break;
			case 32:
				
				
				break;
			case 33:
				constructionService.setstatus(status, companyid, fillyear);
				cDustService.setstatus(status, companyid, fillyear);
				equipmentService.setstatus(status, companyid, fillyear);
				break;
			case 34:
				vehicleService.setstatus(status, companyid, fillyear);
				roadDustService.setstatus(status, companyid, fillyear);
				boatService.setstatus(status, companyid, fillyear);
				break;
			case 35:
				
				break;
			case 36:
				nfertilizerService.setstatus(status, companyid, fillyear);
				firewoodService.setstatus(status, companyid, fillyear);
				equipmentFarmService.setstatus(status, companyid, fillyear);
				pesticideService.setstatus(status, companyid, fillyear);
				break;
			case 37:
				
				break;
			case 38:
				cleanerService.setstatus(status, companyid, fillyear);
				break;	
			case 39:
				
				break;
			case 40:
				
				break;		
			case 41:
				houseFuelService.setstatus(status, companyid, fillyear);
				ConsumeService.setstatus(status, companyid, fillyear);
				break;		
			default:
				return result.setStatus(-3, "invalid account");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception.");
		}

		return result.setStatus(0, "ok");
	}

	private Object changeStatus2(int companyid, int status, int originStatus) {
		try {
			switch (c.getTypeid()) {
			case 2:
				cfillService.setstatus2(status, companyid, originStatus);
				devfillService.setstatus2(status, companyid, originStatus);
				elecService.setstatus2(status, companyid, originStatus);
				elecfillService.setstatus2(status, companyid, originStatus);
				ffillService.setstatus2(status, companyid, originStatus);
				mfillService.setstatus2(status, companyid, originStatus);
				ofillService.setstatus2(status, companyid, originStatus);
				pfillService.setstatus2(status, companyid, originStatus);
				sfacilityDao.setstatus2(status, companyid, originStatus);
				break;
			case 3:
				canyinCertService.setstatus2(status, companyid, originStatus);
				canyinNocertService.setstatus2(status, companyid, originStatus);
				canyinStatService.setstatus2(status, companyid, originStatus);
				break;
			case 4:
				animalFarmService.setstatus2(status, companyid, originStatus);
				animalWildService.setstatus2(status, companyid, originStatus);
				break;
			case 5:
				nfertilizerService.setstatus2(status, companyid, originStatus);
				break;
			case 6:
				equipmentFarmService.setstatus2(status, companyid, originStatus);
				firewoodService.setstatus2(status, companyid, originStatus);
				pesticideService.setstatus2(status, companyid, originStatus);
				nfertilizerService.setstatus2(status, companyid, originStatus);
				animalFarmService.setstatus2(status, companyid, originStatus);
				animalWildService.setstatus2(status, companyid, originStatus);
				break;
			case 7:
				roadDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);
				break;
			case 8:
				dumpService.setstatus2(status, companyid, originStatus);
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);
				roadDustService.setstatus2(status, companyid, originStatus);
				break;
			case 9:
				SellService.setstatus2(status, companyid, originStatus);
				ConsumeService.setstatus2(status, companyid, originStatus);
				break;
			case 10:
				oilportService.setstatus2(status, companyid, originStatus);
				gasstationService.setstatus2(status, companyid, originStatus);
				cleanerService.setstatus2(status, companyid, originStatus);
				houseFuelService.setstatus2(status, companyid, originStatus);
				vehicleActionService.setstatus2(status, companyid, originStatus);
				dumpService.setstatus2(status, companyid, originStatus);
				break;
			case 11:
				cleanerService.setstatus2(status, companyid, originStatus);
				break;
			case 12:
				gknumberService.setstatus2(status, companyid, originStatus);
				break;
			case 13:
				oilportService.setstatus2(status, companyid, originStatus);
				gasstationService.setstatus2(status, companyid, originStatus);
				SellService.setstatus2(status, companyid, originStatus);
				break;
			case 14:
				vehicleService.setstatus2(status, companyid, originStatus);
				boatService.setstatus2(status, companyid, originStatus);
				roadDustService.setstatus2(status, companyid, originStatus);
				break;
			case 15:
				boatService.setstatus2(status, companyid, originStatus);
				break;
			case 16:
				planeService.setstatus2(status, companyid, originStatus);
				break;
			case 17:
				equipmentFarmService.setstatus2(status, companyid, originStatus);
				break;
			case 18:
				SellService.setstatus2(status, companyid, originStatus);
				break;
			case 19:
				ConsumeService.setstatus2(status, companyid, originStatus);
				SellService.setstatus2(status, companyid, originStatus);
				houseFuelService.setstatus2(status, companyid, originStatus);
				break;
			case 20:
				dumpService.setstatus2(status, companyid, originStatus);
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);
				break;
			case 21:
				dumpService.setstatus2(status, companyid, originStatus);
				break;
			case 22:
				SellService.setstatus2(status, companyid, originStatus);
				break;
			case 23:
				pesticideService.setstatus2(status, companyid, originStatus);
				break;
			case 24:
				vehicleActionService.setstatus2(status, companyid, originStatus);
				break;
			case 25:
				dumpService.setstatus2(status, companyid, originStatus);
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);
				canyinCertService.setstatus2(status, companyid, originStatus);
				canyinNocertService.setstatus2(status, companyid, originStatus);
				canyinStatService.setstatus2(status, companyid, originStatus);
				roadDustService.setstatus2(status, companyid, originStatus);
				break;
			case 26:
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);
				break;
			case 27:
				vehicleService.setstatus2(status, companyid, originStatus);
				break;
			case 28:
				firewoodService.setstatus2(status, companyid, originStatus);
				pesticideService.setstatus2(status, companyid, originStatus);
				break;
			case 29:
				ConsumeService.setstatus2(status, companyid, originStatus);
				SellService.setstatus2(status, companyid, originStatus);
				houseFuelService.setstatus2(status, companyid, originStatus);
				gasstationService.setstatus2(status, companyid, originStatus);
				break;
			case 30:
				dumpService.setstatus2(status, companyid, originStatus);
				break;
			case 31:
				gasstationService.setstatus2(status, companyid, originStatus);
				oilportService.setstatus2(status, companyid, originStatus);
				break;
			case 32:
				vehicleActionService.setstatus2(status, companyid, originStatus);
				break;
			case 33:
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);		
				break;
			case 34:
				vehicleService.setstatus2(status, companyid, originStatus);
				roadDustService.setstatus2(status, companyid, originStatus);
				boatService.setstatus2(status, companyid, originStatus);
				break;
			case 35:
				planeService.setstatus2(status, companyid, originStatus);
				break;
			case 36:
				nfertilizerService.setstatus2(status, companyid, originStatus);
				firewoodService.setstatus2(status, companyid, originStatus);
				equipmentFarmService.setstatus2(status, companyid, originStatus);
				pesticideService.setstatus2(status, companyid, originStatus);
				break;
			case 37:
				
				break;
			case 38:
				cleanerService.setstatus2(status, companyid, originStatus);
				break;	
			case 39:
				canyinStatService.setstatus2(status, companyid, originStatus);
				canyinNocertService.setstatus2(status, companyid, originStatus);
				canyinCertService.setstatus2(status, companyid, originStatus);
				break;
			case 40:
				gknumberService.setstatus2(status, companyid, originStatus);
				break;		
			case 41:
				houseFuelService.setstatus2(status, companyid, originStatus);
				ConsumeService.setstatus2(status, companyid, originStatus);
				break;		
			default:
				return result.setStatus(-3, "invalid account");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception.");
		}

		return result.setStatus(0, "ok");
	}

	private Object changeStatus3(int companyid, int status, int originStatus, int fillyear) {
		try {
			switch (c.getTypeid()) {
			case 2:
				cfillService.setstatus3(status, companyid, originStatus, fillyear);
				devfillService.setstatus3(status, companyid, originStatus, fillyear);
				elecService.setstatus3(status, companyid, originStatus, fillyear);
				elecfillService.setstatus2(status, companyid, originStatus);
				ffillService.setstatus3(status, companyid, originStatus, fillyear);
				mfillService.setstatus3(status, companyid, originStatus, fillyear);
				ofillService.setstatus3(status, companyid, originStatus, fillyear);
				pfillService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 5:
				nfertilizerService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 6:
				equipmentFarmService.setstatus3(status, companyid, originStatus, fillyear);
				firewoodService.setstatus3(status, companyid, originStatus, fillyear);
				pesticideService.setstatus3(status, companyid, originStatus, fillyear);
				nfertilizerService.setstatus(status, companyid, fillyear);
				break;
			case 7:
				roadDustService.setstatus3(status, companyid, originStatus, fillyear);
				equipmentService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 8:
				constructionService.setstatus3(status, companyid, originStatus, fillyear);
				cDustService.setstatus3(status, companyid, originStatus, fillyear);
				equipmentService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 9:
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				ConsumeService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 10:
				cleanerService.setstatus3(status, companyid, originStatus, fillyear);
				houseFuelService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 11:
				cleanerService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 13:
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 14:
				vehicleService.setstatus3(status, companyid, originStatus, fillyear);
				boatService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 15:
				boatService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 17:
				equipmentFarmService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 18:
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 19:
				ConsumeService.setstatus3(status, companyid, originStatus, fillyear);
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				houseFuelService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 20:
				constructionService.setstatus3(status, companyid, originStatus, fillyear);
				cDustService.setstatus3(status, companyid, originStatus, fillyear);
				equipmentService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 22:
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 23:
				pesticideService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 25:
				constructionService.setstatus3(status, companyid, originStatus, fillyear);
				cDustService.setstatus3(status, companyid, originStatus, fillyear);
				equipmentService.setstatus3(status, companyid, originStatus, fillyear);
				roadDustService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 26:
				constructionService.setstatus3(status, companyid, originStatus, fillyear);
				cDustService.setstatus3(status, companyid, originStatus, fillyear);
				equipmentService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 27:
				vehicleService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 28:
				firewoodService.setstatus3(status, companyid, originStatus, fillyear);
				pesticideService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 29:
				ConsumeService.setstatus3(status, companyid, originStatus, fillyear);
				SellService.setstatus3(status, companyid, originStatus, fillyear);
				houseFuelService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 31:
				gasstationService.setstatus2(status, companyid, originStatus);
				oilportService.setstatus2(status, companyid, originStatus);
				break;
			case 32:
				vehicleActionService.setstatus2(status, companyid, originStatus);
				break;
			case 33:
				constructionService.setstatus2(status, companyid, originStatus);
				cDustService.setstatus2(status, companyid, originStatus);
				equipmentService.setstatus2(status, companyid, originStatus);		
				break;
			case 34:
				vehicleService.setstatus2(status, companyid, originStatus);
				roadDustService.setstatus2(status, companyid, originStatus);
				boatService.setstatus3(status, companyid, originStatus, fillyear);
				break;
			case 35:
				planeService.setstatus2(status, companyid, originStatus);
				break;
			case 36:
				nfertilizerService.setstatus2(status, companyid, originStatus);
				firewoodService.setstatus2(status, companyid, originStatus);
				equipmentFarmService.setstatus2(status, companyid, originStatus);
				pesticideService.setstatus2(status, companyid, originStatus);
				break;
			case 37:
				
				break;
			case 38:
				cleanerService.setstatus2(status, companyid, originStatus);
				break;	
			case 39:
				canyinStatService.setstatus2(status, companyid, originStatus);
				canyinNocertService.setstatus2(status, companyid, originStatus);
				canyinCertService.setstatus2(status, companyid, originStatus);
				break;
			case 40:
				gknumberService.setstatus2(status, companyid, originStatus);
				break;		
			case 41:
				houseFuelService.setstatus2(status, companyid, originStatus);
				ConsumeService.setstatus2(status, companyid, originStatus);
				break;
			case 42:
				
				break;
			default:
				return result.setStatus(-3, "invalid account");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception.");
		}

		return result.setStatus(0, "ok");
	}

	// public int companyStatus(int companyid) {
	// int ret = STATUS_INIT;
	//
	// try {
	//
	// CompanyFill cfill = cfillService.yearFill(companyid);
	// if (cfill == null || cfill.getStatus() == 0)
	// return ret;
	//
	// List<Elec> eflist = elecService.yearList();
	// List<ControlFacility> flist = facilityService.yearList();
	// List<Outlet> olist = outletService.yearList();
	// List<ProduceStep> slist = stepService.yearList();
	// List<Devices> dlist = devService.yearList();
	//
	// for (Devices d : dlist) {
	// if (d.getStatus() < STATUS_UNCHECK) {
	// return STATUS_INIT;
	// } else {
	// ret = d.getStatus();
	// }
	// }
	//
	// for (ProduceStep p : slist) {
	// if (p.getStatus() < STATUS_UNCHECK) {
	// return STATUS_INIT;
	// } else {
	// ret = Math.min(ret, p.getStatus());
	// }
	// }
	//
	// for (Outlet o : olist) {
	// if (o.getStatus() < STATUS_UNCHECK) {
	// return STATUS_INIT;
	// } else {
	// ret = Math.min(ret, o.getStatus());
	// }
	// }
	//
	// for (ControlFacility f : flist) {
	// if (f.getStatus() < STATUS_UNCHECK) {
	// return STATUS_INIT;
	// } else {
	// ret = Math.min(ret, f.getStatus());
	// }
	// }
	//
	// for (Elec e : eflist) {
	// if (e.getStatus() < STATUS_UNCHECK) {
	// return STATUS_INIT;
	// } else {
	// ret = Math.min(ret, e.getStatus());
	// }
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return -2;
	// }
	//
	// return ret;
	// }

	@SuppressWarnings("unused")
	private void govStatusList(int status, int accountid) {

		List<Integer> clist1 = new ArrayList<Integer>();

		int curyear = configService.getLastYear();

		List<AccountStat> alist = canyinStatService.getAccountStat(curyear);

		for (AccountStat o : alist) {
			if (o.getCnt() > 0)
				clist1.add(o.getId());
		}

	}

	private Object companyStatusList(int status, User u, Condition con,Searchform searchform) {

		try {
			int fillyear = con.getFillyear();
			List<Company> clist;
			List<Company> clistall = new ArrayList<Company>();
			if (u.getCity() > 0 && u.getTypeid() > 2 && u.getTown() > 0) {
				con.setCityid(u.getCity());
				con.setTownid(u.getTown());
				clist = companyService.getCityTown(con);
			} else {
				if (u.getTypeid() > 2 && u.getCity() > 0) {
					con.setCityid(u.getCity());
					clist = companyService.getCityTown(con);
				} else {
					clist = companyService.getCityTown(con);
				}
			}
			List<CompanyFill> cflist = cfillService.getyearList(con);
			List<Elec> eflist = elecService.getyearList(con);
			List<ControlFacility> flist = facilityService.getyearList(con);
			List<Outlet> olist = outletService.getyearList(con);
			List<ProduceStep> slist = stepService.getyearList(con);
			List<Devices> dlist = devService.getyearList(con);

			List<Company> clist2 = new ArrayList<Company>();
			List<Company> clist3 = new ArrayList<Company>();

			Iterator<Company> itr = clist.iterator();
			while (itr.hasNext()) {
				itr.next().setStatus(STATUS_FILL);
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (Devices obj : dlist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (CompanyFill obj : cflist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (Elec obj : eflist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (ControlFacility obj : flist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (Outlet obj : olist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				for (ProduceStep obj : slist) {
					if (c.getId() == obj.getEnterpriceId()) {
						if (obj.getStatus() == STATUS_UNCHECK) {
							itr.remove();
							c.setStatus(STATUS_UNCHECK);
							clist2.add(c);
							break;
						} else if (obj.getStatus() == STATUS_CHECKED) {
							c.setStatus(STATUS_CHECKED);
						} else {
							c.setStatus(STATUS_FILL);
						}
					}
				}
			}

			itr = clist.iterator();
			while (itr.hasNext()) {
				Company c = itr.next();
				if (c.getStatus() == STATUS_CHECKED) {
					itr.remove();
					clist3.add(c);
				}
			}

			switch (status) {
			case STATUS_FILL:
				List<Integer> cid = new ArrayList<Integer>();
				if (clist.size() > 0) {
					for (Company cli : clist) {
						int companyid = cli.getId();
						cid.add(companyid);
					}
				}
				List<Object> pageHelper = PageHelper.pageHelper(clist, searchform);
				result.put("data", pageHelper);
				result.put("searchform", searchform);
				details(cid, fillyear);
				break;
			case STATUS_UNCHECK:
				List<Integer> cid2 = new ArrayList<Integer>();
				if (clist2.size() > 0) {
					for (Company cli : clist2) {
						int companyid = cli.getId();
						cid2.add(companyid);
					}
				}
				List<Object> pageHelper1 = PageHelper.pageHelper(clist2, searchform);
				result.put("data", pageHelper1);
				result.put("searchform", searchform);
				details(cid2, fillyear);
				break;
			case STATUS_CHECKED:
				List<Integer> cid3 = new ArrayList<Integer>();
				if (clist3.size() > 0) {
					for (Company cli : clist3) {
						int companyid = cli.getId();
						cid3.add(companyid);
					}
				}
				List<Object> pageHelper11 = PageHelper.pageHelper(clist3, searchform);
				result.put("data", pageHelper11);
				result.put("searchform", searchform);
				details(cid3, fillyear);
				break;
			default:
				clistall.addAll(clist3);
				clistall.addAll(clist2);
				clistall.addAll(clist);
				List<Integer> cidall = new ArrayList<Integer>();
				if (clistall.size() > 0) {
					for (Company cli : clistall) {
						int companyid = cli.getId();
						cidall.add(companyid);
					}
				}
				List<Object> pageHelper111 = PageHelper.pageHelper(clistall, searchform);
				result.put("data", pageHelper111);
				result.put("searchform", searchform);
				details(cidall, fillyear);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	private Object smallCompanyStatus(int status, User u, Condition con,int companyId) {

		try {
			int fillyear = con.getFillyear();
			List<Integer> yearList = cfillService.getSmall();
			int is=0;
			for(Integer it:yearList){
				if(it==fillyear)
					is+=1;
			}
			if(is == 0)
				return result.setStatus(0, "no data!");
			List<Company> cList;
			con.setIsmall(1);
			if (u.getCity() > 0 && u.getTypeid() > 2 && u.getTown() > 0) {
				con.setCityid(u.getCity());
				con.setTownid(u.getTown());
				cList = companyService.getCityTown(con);
			} else {
				if (u.getTypeid() > 2 && u.getCity() > 0) {
					con.setCityid(u.getCity());
					cList = companyService.getCityTown(con);
				} else {
					cList = companyService.getCityTown(con);
				}
			}

			for (Company c : cList) {
				c.setPassword("******");

				CompanyFill cf = new CompanyFill();
				cf.setEnterpriceId(c.getId());
				cf.setFillyear(fillyear);
				cf.setStatus(status);
				CompanyFill cfill = cfillService.getbyCompanyYear(cf);
				if(cfill==null){
					cfill=new CompanyFill();
					cfill.setEnterpriceId(c.getId());
					cfill.setFillyear(fillyear);
					c.setAll_status(cfill.getAll_status());
				}
				c.setClist(cfill);
				
				List<Material> mList = materialService.getByCompany(companyId);
				c.setmList(mList);
				
				SmallFacility sf = new SmallFacility();
				sf.setFillyear(fillyear);
				sf.setEnterpriceId(c.getId());
				sf.setStatus(status);
				List<SmallFacility> sfill = sfacilityDao.getAll(sf);
				c.setSlist(sfill);

				Devices df = new Devices();
				df.setFillyear(fillyear);
				df.setStatus(status);
				df.setEnterpriceId(c.getId());
				List<Devices> dfill = devService.getByCompanyYear2(df);
				for(Devices dev:dfill){
					if(dev.getRemark() != null && dev.getRemark()!= ""){
						Double  fuelCost = Double.parseDouble(dev.getRemark());
						dev.setFuelcost(fuelCost);
					}
				}
				c.setDlist(dfill);

				List<ProductFill> pflist=pfillService.getByYear(fillyear, c.getId());
				for(ProductFill pf:pflist){
					pf.setMaterialId(pf.getProduceStepId());
					Product p=pService.getById(pf.getProductId());
					Material m=materialService.getById(pf.getProduceStepId());
					if(p!=null){
						String unit = p.getUnit();
						pf.setProductUnit(unit);
						pf.setProductName(p.getProductName());
					}
					if(m!=null){
						pf.setMaterialName(m.getMaterialName());
						pf.setMaterialUnit(m.getUnit());
					}
					
				}
				c.setPlist(pflist);
				
//				if(status==0){
//					int one=0;
//					int two=0;
//					int three=0;
//					for(Devices d:c.getDlist()){
//						if(d.getStatus()<3){
//							if(d.getStatus()==3){
//								three=3;
//							}
//							if(d.getStatus()==2){
//								two=2;
//							}
//							if(d.getStatus()==1){
//								one=1;
//							}
//						}
//					}
//					for(SmallFacility d:c.getSlist()){
//						if(d.getStatus()<3){
//							if(d.getStatus()==3){
//								three=3;
//							}
//							if(d.getStatus()==2){
//								two=2;
//							}
//							if(d.getStatus()==1){
//								one=1;
//							}
//						}
//					}
//					if(c.getClist().getStatus()<3){
//						if(c.getClist().getStatus()==3){
//							three=3;
//						}
//						if(c.getClist().getStatus()==2){
//							two=2;
//						}
//						if(c.getClist().getStatus()==1){
//							one=1;
//						}
//					}
//					if(one==1){
//						c.setAll_status(one);
//					}else if(two==2){
//						c.setAll_status(two);
//					}else if(three==3){
//						c.setAll_status(three);
//					}
//				}else{
//					c.setAll_status(status);
//				}
			}

			if(companyId == 0){
				List<Company> newlist=new ArrayList<Company>();
				for(Company c:cList){
					if(c.getClist().getAll_status()==status&&status>0){
						newlist.add(c);
					}else if(status==0){
						newlist.add(c);
					}
				}
				result.put("list", newlist);
			}else if(companyId > 0){
				List<Company> newlist=new ArrayList<Company>();
				for(Company c:cList){
					if(c.getId()==companyId){
						newlist.add(c);
					}
				}
				
				result.put("list", newlist);
				result.put("fillyear", fillyear);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-2, "exception");
		}

		return result.setStatus(0, "ok");
	}

	private void details(List<Integer> companyid, int fillyear) {
		List<DetailsOfindustry> com = detailService.getCompanyfill(companyid, fillyear);
		Map<Integer,List<DetailsOfindustry>> comMap = new HashMap<Integer, List<DetailsOfindustry>>();
		for(DetailsOfindustry doi : com){
			List<DetailsOfindustry> list = comMap.get(doi.getCompanyId());
			if(list == null){
				list = new ArrayList<DetailsOfindustry>();
			}
			list.add(doi);
			comMap.put(doi.getCompanyId(), list);
		}
		List<DetailsOfindustry> co = detailService.getCompanyvalue(companyid);
		for (DetailsOfindustry c : co) {
			List<DetailsOfindustry> list = comMap.get(c.getId());
			if(list != null && list.size() > 0){
				for (DetailsOfindustry comf : list) {
					c.setFillyear(fillyear);
					c.setGDP(comf.getGDP());
					c.setDaysOfWork(comf.getDaysOfWork());
					c.setHoursOfDay(comf.getHoursOfDay());
					c.setStatus(comf.getStatus());
					break;
				}
			}
		}
		List<DetailsOfindustry> comdelist = new ArrayList<DetailsOfindustry>();
		List<DetailsOfindustry> comde = detailService.getDevice(companyid, fillyear);
		for (DetailsOfindustry comd : comde) {
			if (comd.getDeviceSerial() != null && comd.getDeviceSerial().contains("GL"))
				comdelist.add(comd);
		}
		for (DetailsOfindustry comd : comde) {
			if (comd.getDeviceSerial() != null && comd.getDeviceSerial().contains("YL"))
				comdelist.add(comd);
		}
		List<DetailsOfindustry> comelec = detailService.getElec(companyid, fillyear);
		List<DetailsOfindustry> compoll = detailService.getPollutant(companyid, fillyear);

		List<DetailsOfindustry> prolist = new ArrayList<DetailsOfindustry>();
		List<DetailsOfindustry> compro = detailService.getProduct(companyid, fillyear);
		
		if (compro != null && compro.size() > 0) {
			List<DetailsOfindustry> material = detailService.getMaterial();
			Map<Integer,List<DetailsOfindustry>> map = new HashMap<Integer, List<DetailsOfindustry>>();
			for(DetailsOfindustry doi : material){
				List<DetailsOfindustry> list = map.get(doi.getProductfId());
				if(list == null){
					list = new ArrayList<DetailsOfindustry>();
				}
				list.add(doi);
				map.put(doi.getProductfId(), list);
			}
			for (DetailsOfindustry proli : compro) {
				if (proli.getProductfId() > 0) {
					List<DetailsOfindustry> commat = map.get(proli.getProductfId());
					if(commat == null)
						continue;
					for (int i = 0; i < commat.size(); i++) {
						DetailsOfindustry malis = new DetailsOfindustry();
						if (i == 0) {
							malis.setCompanyName(proli.getCompanyName());
							malis.setCity(proli.getCity());
							malis.setTown(proli.getTown());
							malis.setTradeNo1(proli.getTradeNo1());
							malis.setTradeNo2(proli.getTradeNo2());
							malis.setTradeNo3(proli.getTradeNo3());
							malis.setTradeNo4(proli.getTradeNo4());
							malis.setE_point(proli.getE_point());
							malis.setN_point(proli.getN_point());
							malis.setTradeName(proli.getTradeName());
							malis.setStepSerial(proli.getStepSerial());
							malis.setPrhoursPerDay(proli.getPrhoursPerDay());
							malis.setPrdaysPerYear(proli.getPrdaysPerYear());
							malis.setProductName(proli.getProductName());
							malis.setPm1(proli.getPm1());
							malis.setPm2(proli.getPm2());
							malis.setPm3(proli.getPm3());
							malis.setPm4(proli.getPm4());
							malis.setPm5(proli.getPm5());
							malis.setPm6(proli.getPm6());
							malis.setPm7(proli.getPm7());
							malis.setPm8(proli.getPm8());
							malis.setPm9(proli.getPm9());
							malis.setPm10(proli.getPm10());
							malis.setPm11(proli.getPm11());
							malis.setPm12(proli.getPm12());
							malis.setPrdesignOutput(proli.getPrdesignOutput());
							malis.setPunit(proli.getPunit());
						}
						malis.setMaterialName(commat.get(i).getMaterialName());
						malis.setConsumeOfYear(commat.get(i).getConsumeOfYear());
						malis.setUnit(commat.get(i).getUnit());
						prolist.add(malis);
					}
					if (commat.size() <= 0) {
						prolist.add(proli);
					}
				}
			}
		}
		List<DetailsOfindustry> faclist = new ArrayList<DetailsOfindustry>();
		List<DetailsOfindustry> comfac = detailService.getFacility(companyid, fillyear);
		if (comfac != null) {
			List<DetailsOfindustry> step2 = detailService.getStep();
			Map<Integer,List<DetailsOfindustry>> map = new HashMap<Integer, List<DetailsOfindustry>>();
			for(DetailsOfindustry doi : step2){
				List<DetailsOfindustry> list = map.get(doi.getFacilityId());
				if(list == null){
					list = new ArrayList<DetailsOfindustry>();
				}
				list.add(doi);
				map.put(doi.getFacilityId(), list);
			}
			
			List<DetailsOfindustry> device = detailService.getDeviceAll();
			Map<Integer,List<DetailsOfindustry>> map2 = new HashMap<Integer, List<DetailsOfindustry>>();
			for(DetailsOfindustry doi : device){
				List<DetailsOfindustry> list = map2.get(doi.getFacilityId());
				if(list == null){
					list = new ArrayList<DetailsOfindustry>();
				}
				list.add(doi);
				map2.put(doi.getFacilityId(), list);
			}
			for (DetailsOfindustry fac : comfac) {
				if (fac.getFacilityId() > 0) {
					List<DetailsOfindustry> step = map.get(fac.getFacilityId());
					if (step != null) {
						for (DetailsOfindustry stepli : step) {
							DetailsOfindustry malis = new DetailsOfindustry();
							malis.setCompanyName(fac.getCompanyName());
							malis.setCity(fac.getCity());
							malis.setTown(fac.getTown());
							malis.setTradeNo1(fac.getTradeNo1());
							malis.setTradeNo2(fac.getTradeNo2());
							malis.setTradeNo3(fac.getTradeNo3());
							malis.setTradeNo4(fac.getTradeNo4());
							malis.setE_point(fac.getE_point());
							malis.setN_point(fac.getN_point());
							malis.setTradeName(fac.getTradeName());
							malis.setFoutletSerial(fac.getFoutletSerial());
							malis.setFpollutantName(fac.getFpollutantName());
							malis.setTechnique1(fac.getTechnique1());
							malis.setTechnique2(fac.getTechnique2());
							malis.setFacilityModel(fac.getFacilityModel());
							malis.setFstepSerial(stepli.getFstepSerial());
							malis.setFstepName(stepli.getFstepName());
							malis.setFmaterialName(fac.getFmaterialName());
							malis.setFmaterialConsume(fac.getFmaterialConsume());
							malis.setYearCost(fac.getYearCost());
							malis.setDisRate(fac.getDisRate());
							malis.setFdaysPerYear(fac.getFdaysPerYear());
							malis.setNH3Release(fac.getNH3Release());
							malis.setCollectRate(fac.getCollectRate());
							faclist.add(malis);
						}
					}
					List<DetailsOfindustry> devi = map2.get(fac.getFacilityId());
					if (devi != null) {
						for (DetailsOfindustry deli : devi) {
							DetailsOfindustry malis = new DetailsOfindustry();
							malis.setCompanyName(fac.getCompanyName());
							malis.setCity(fac.getCity());
							malis.setTown(fac.getTown());
							malis.setTradeNo1(fac.getTradeNo1());
							malis.setTradeNo2(fac.getTradeNo2());
							malis.setTradeNo3(fac.getTradeNo3());
							malis.setTradeNo4(fac.getTradeNo4());
							malis.setE_point(fac.getE_point());
							malis.setN_point(fac.getN_point());
							malis.setTradeName(fac.getTradeName());
							malis.setFoutletSerial(fac.getFoutletSerial());
							malis.setFpollutantName(fac.getFpollutantName());
							malis.setTechnique1(fac.getTechnique1());
							malis.setTechnique2(fac.getTechnique2());
							malis.setFacilityModel(fac.getFacilityModel());
							malis.setFdeviceSerial(deli.getFdeviceSerial());
							malis.setFdeviceName(deli.getFdeviceName());
							malis.setFdeviceName2(deli.getFdeviceName2());
							malis.setFmaterialName(fac.getFmaterialName());
							malis.setFmaterialConsume(fac.getFmaterialConsume());
							malis.setYearCost(fac.getYearCost());
							malis.setDisRate(fac.getDisRate());
							malis.setFdaysPerYear(fac.getFdaysPerYear());
							malis.setNH3Release(fac.getNH3Release());
							malis.setCollectRate(fac.getCollectRate());
							faclist.add(malis);
						}
					}
					if (step != null && devi != null && step.size() <= 0 && devi.size() <= 0) {
						faclist.add(fac);
					}
				}
			}
		}
		export(co, comdelist, prolist, compoll, faclist, comelec);
	}

	@SuppressWarnings("deprecation")
	public void export(List<DetailsOfindustry> com, List<DetailsOfindustry> comde, List<DetailsOfindustry> compro,
			List<DetailsOfindustry> compoll, List<DetailsOfindustry> comfac, List<DetailsOfindustry> comelec) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = sheet.createRow((int) 0);
		HSSFRow rowgy = sheet.createRow((int) com.size() + 2);
		HSSFRow rowpr = sheet.createRow((int) com.size() + comde.size() + 4);
		HSSFRow rowpo = sheet.createRow((int) com.size() + comde.size() + compro.size() + 6);
		HSSFRow rowfac = sheet.createRow((int) com.size() + comde.size() + compro.size() + compoll.size() + 8);
		HSSFRow rowel = sheet
				.createRow((int) com.size() + comde.size() + compro.size() + compoll.size() + comfac.size() + 10);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("序列号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("公司");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("地址(市、州、盟)");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("地址(区、市、旗)");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("行业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("联系人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("联系电话");
		cell.setCellStyle(style);

		cell = row.createCell((short) 7);
		cell.setCellValue("一级行业代码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("二级行业代码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("三级行业代码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("四级行业代码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("经度");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("纬度");
		cell.setCellStyle(style);

		cell = row.createCell((short) 13);
		cell.setCellValue("GDP");
		cell.setCellStyle(style);
		cell = row.createCell((short) 14);
		cell.setCellValue("年份");
		cell.setCellStyle(style);
		cell = row.createCell((short) 15);
		cell.setCellValue("年生产天数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);
		cell.setCellValue("日生产小时");
		cell.setCellStyle(style);
		

		HSSFCell cellgy = rowgy.createCell((short) 0);
		cellgy.setCellValue("序列号");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 1);
		cellgy.setCellValue("公司");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 2);
		cellgy.setCellValue("地址(市、州、盟)");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 3);
		cellgy.setCellValue("地址(区、市、旗)");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 4);
		cellgy.setCellValue("行业");
		cellgy.setCellStyle(style);

		cellgy = rowgy.createCell((short) 5);
		cellgy.setCellValue("一级行业代码");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 6);
		cellgy.setCellValue("二级行业代码");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 7);
		cellgy.setCellValue("三级行业代码");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 8);
		cellgy.setCellValue("四级行业代码");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 9);
		cellgy.setCellValue("经度");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 10);
		cellgy.setCellValue("纬度");
		cellgy.setCellStyle(style);

		cellgy = rowgy.createCell((short) 11);
		cellgy.setCellValue("锅窑炉编号");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 12);
		cellgy.setCellValue("锅窑炉类型");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 13);
		cellgy.setCellValue("锅窑炉类型");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 14);
		cellgy.setCellValue("锅窑炉型号");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 15);
		cellgy.setCellValue("启用年份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 16);
		cellgy.setCellValue("锅炉蒸吨");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 17);
		cellgy.setCellValue("主要产品名称");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 18);
		cellgy.setCellValue("产品年产量");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 19);
		cellgy.setCellValue("单位");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 20);
		cellgy.setCellValue("主要原料名称");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 21);
		cellgy.setCellValue("原料年消耗量");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 22);
		cellgy.setCellValue("单位");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 23);
		cellgy.setCellValue("燃料");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 24);
		cellgy.setCellValue("日工作频率");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 25);
		cellgy.setCellValue("年工作频率");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 26);
		cellgy.setCellValue("硫分");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 27);
		cellgy.setCellValue("灰份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 28);
		cellgy.setCellValue("挥发份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 29);
		cellgy.setCellValue("1月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 30);
		cellgy.setCellValue("2月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 31);
		cellgy.setCellValue("3月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 32);
		cellgy.setCellValue("4月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 33);
		cellgy.setCellValue("5月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 34);
		cellgy.setCellValue("6月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 35);
		cellgy.setCellValue("7月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 36);
		cellgy.setCellValue("8月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 37);
		cellgy.setCellValue("9月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 38);
		cellgy.setCellValue("10月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 39);
		cellgy.setCellValue("11月份");
		cellgy.setCellStyle(style);
		cellgy = rowgy.createCell((short) 40);
		cellgy.setCellValue("12月份");
		cellgy.setCellStyle(style);

		HSSFCell cellpr = rowpr.createCell((short) 0);
		cellpr.setCellValue("序列号");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 1);
		cellpr.setCellValue("公司");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 2);
		cellpr.setCellValue("地址(市、州、盟)");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 3);
		cellpr.setCellValue("地址(区、市、旗)");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 4);
		cellpr.setCellValue("行业");
		cellpr.setCellStyle(style);

		cellpr = rowpr.createCell((short) 5);
		cellpr.setCellValue("一级行业代码");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 6);
		cellpr.setCellValue("二级行业代码");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 7);
		cellpr.setCellValue("三级行业代码");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 8);
		cellpr.setCellValue("四级行业代码");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 9);
		cellpr.setCellValue("经度");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 10);
		cellpr.setCellValue("纬度");
		cellpr.setCellStyle(style);

		cellpr = rowpr.createCell((short) 11);
		cellpr.setCellValue("工段编号");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 12);
		cellpr.setCellValue("日工作频率");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 13);
		cellpr.setCellValue("年工作频率");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 14);
		cellpr.setCellValue("产品名称");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 15);
		cellpr.setCellValue("1月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 16);
		cellpr.setCellValue("2月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 17);
		cellpr.setCellValue("3月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 18);
		cellpr.setCellValue("4月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 19);
		cellpr.setCellValue("5月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 20);
		cellpr.setCellValue("6月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 21);
		cellpr.setCellValue("7月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 22);
		cellpr.setCellValue("8月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 23);
		cellpr.setCellValue("9月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 24);
		cellpr.setCellValue("10月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 25);
		cellpr.setCellValue("11月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 26);
		cellpr.setCellValue("12月份");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 27);
		cellpr.setCellValue("年总产量");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 28);
		cellpr.setCellValue("单位");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 29);
		cellpr.setCellValue("原料名称");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 30);
		cellpr.setCellValue("原料年消耗量");
		cellpr.setCellStyle(style);
		cellpr = rowpr.createCell((short) 31);
		cellpr.setCellValue("单位");
		cellpr.setCellStyle(style);

		HSSFCell cellpo = rowpo.createCell((short) 0);
		cellpo.setCellValue("序列号");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 1);
		cellpo.setCellValue("公司");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 2);
		cellpo.setCellValue("地址(市、州、盟)");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 3);
		cellpo.setCellValue("地址(区、市、旗)");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 4);
		cellpo.setCellValue("行业");
		cellpo.setCellStyle(style);

		cellpo = rowpo.createCell((short) 5);
		cellpo.setCellValue("一级行业代码");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 6);
		cellpo.setCellValue("二级行业代码");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 7);
		cellpo.setCellValue("三级行业代码");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 8);
		cellpo.setCellValue("四级行业代码");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 9);
		cellpo.setCellValue("经度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 10);
		cellpo.setCellValue("纬度");
		cellpo.setCellStyle(style);

		cellpo = rowpo.createCell((short) 11);
		cellpo.setCellValue("排放口编号");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 12);
		cellpo.setCellValue("排放口高度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 13);
		cellpo.setCellValue("排放口直径");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 14);
		cellpo.setCellValue("经度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 15);
		cellpo.setCellValue("纬度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 16);
		cellpo.setCellValue("污染物名称");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 17);
		cellpo.setCellValue("年排放量");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 18);
		cellpo.setCellValue("年均排放浓度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 19);
		cellpo.setCellValue("第一季度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 20);
		cellpo.setCellValue("第二季度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 21);
		cellpo.setCellValue("第三季度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 22);
		cellpo.setCellValue("第四季度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 23);
		cellpo.setCellValue("排放口废气流速");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 24);
		cellpo.setCellValue("排放口废气温度");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 25);
		cellpo.setCellValue("废气排放量");
		cellpo.setCellStyle(style);
		cellpo = rowpo.createCell((short) 26);
		cellpo.setCellValue("年总废气排放量");
		cellpo.setCellStyle(style);

		HSSFCell cellfac = rowfac.createCell((short) 0);
		cellfac.setCellValue("序列号");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 1);
		cellfac.setCellValue("公司");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 2);
		cellfac.setCellValue("地址(市、州、盟)");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 3);
		cellfac.setCellValue("地址(区、市、旗)");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 4);
		cellfac.setCellValue("行业");
		cellfac.setCellStyle(style);

		cellfac = rowfac.createCell((short) 5);
		cellfac.setCellValue("一级行业代码");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 6);
		cellfac.setCellValue("二级行业代码");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 7);
		cellfac.setCellValue("三级行业代码");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 8);
		cellfac.setCellValue("四级行业代码");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 9);
		cellfac.setCellValue("经度");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 10);
		cellfac.setCellValue("纬度");
		cellfac.setCellStyle(style);

		cellfac = rowfac.createCell((short) 11);
		cellfac.setCellValue("对应排放口");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 12);
		cellfac.setCellValue("治理污染物");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 13);
		cellfac.setCellValue("治理工艺");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 14);
		cellfac.setCellValue("治理工艺");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 15);
		cellfac.setCellValue("治理工艺设备型号");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 16);
		cellfac.setCellValue("工段编号");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 17);
		cellfac.setCellValue("工段名称");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 18);
		cellfac.setCellValue("设备编号");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 19);
		cellfac.setCellValue("设备类型");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 20);
		cellfac.setCellValue("设备名称");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 21);
		cellfac.setCellValue("主要药剂名称");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 22);
		cellfac.setCellValue("主要药剂消耗量");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 23);
		cellfac.setCellValue("费用");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 24);
		cellfac.setCellValue("效率");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 25);
		cellfac.setCellValue("设备运行天数");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 26);
		cellfac.setCellValue("逃逸率");
		cellfac.setCellStyle(style);
		cellfac = rowfac.createCell((short) 27);
		cellfac.setCellValue("废气收集率");
		cellfac.setCellStyle(style);

		HSSFCell cellel = rowel.createCell((short) 0);
		cellel.setCellValue("序列号");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 1);
		cellel.setCellValue("公司");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 2);
		cellel.setCellValue("地址(市、州、盟)");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 3);
		cellel.setCellValue("地址(区、市、旗)");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 4);
		cellel.setCellValue("行业");
		cellel.setCellStyle(style);

		cellel = rowel.createCell((short) 5);
		cellel.setCellValue("一级行业代码");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 6);
		cellel.setCellValue("二级行业代码");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 7);
		cellel.setCellValue("三级行业代码");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 8);
		cellel.setCellValue("四级行业代码");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 9);
		cellel.setCellValue("经度");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 10);
		cellel.setCellValue("纬度");
		cellel.setCellStyle(style);

		cellel = rowel.createCell((short) 11);
		cellel.setCellValue("用电设备");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 12);
		cellel.setCellValue("日用电量");
		cellel.setCellStyle(style);
		cellel = rowel.createCell((short) 13);
		cellel.setCellValue("年用电量");
		cellel.setCellStyle(style);

		String s = new String();
		double j = 0;
		int k = 0;
		// 锅窑炉
		for (int i = 0; i < comde.size(); i++) {
			rowgy = sheet.createRow((int) com.size() + 3 + i);
			DetailsOfindustry deindus = comde.get(i);

			rowgy.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				rowgy.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				rowgy.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				rowgy.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				rowgy.createCell((short) 4).setCellValue(s);

			s = deindus.getTradeNo1();
			if (s != null)
				rowgy.createCell((short) 5).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				rowgy.createCell((short) 6).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				rowgy.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				rowgy.createCell((short) 8).setCellValue(s);
			j = deindus.getE_point();
			rowgy.createCell((short) 9).setCellValue(j);
			j = deindus.getN_point();
			rowgy.createCell((short) 10).setCellValue(j);

			s = deindus.getDeviceSerial();
			if (s != null)
				rowgy.createCell((short) 11).setCellValue(s);
			s = deindus.getDeviceName();
			if (s != null)
				rowgy.createCell((short) 12).setCellValue(s);
			s = deindus.getDeviceName2();
			if (s != null)
				rowgy.createCell((short) 13).setCellValue(s);
			s = deindus.getDeivceModel();
			if (s != null)
				rowgy.createCell((short) 14).setCellValue(s);
			j = deindus.getServiceLife();
			rowgy.createCell((short) 15).setCellValue(j);
			j = deindus.getShippingTon();
			rowgy.createCell((short) 16).setCellValue(j);
			s = deindus.getProductName1();
			if (s != null)
				rowgy.createCell((short) 17).setCellValue(s);
			j = deindus.getProductTotalYear();
			rowgy.createCell((short) 18).setCellValue(j);
			s = deindus.getProductUnit();
			if (s != null)
				rowgy.createCell((short) 19).setCellValue(s);
			s = deindus.getMaterialName1();
			if (s != null)
				rowgy.createCell((short) 20).setCellValue(s);
			j = deindus.getMaterialConsume();
			rowgy.createCell((short) 21).setCellValue(j);
			s = deindus.getMaterialUnit();
			if (s != null)
				rowgy.createCell((short) 22).setCellValue(s);
			s = deindus.getFuel();
			if (s != null)
				rowgy.createCell((short) 23).setCellValue(s);
			j = deindus.getHoursPerDay();
			rowgy.createCell((short) 24).setCellValue(j);
			j = deindus.getDaysPerYear();
			rowgy.createCell((short) 25).setCellValue(j);
			j = deindus.getSContent();
			rowgy.createCell((short) 26).setCellValue(j);
			j = deindus.getAshContent();
			rowgy.createCell((short) 27).setCellValue(j);
			j = deindus.getVocContent();
			rowgy.createCell((short) 28).setCellValue(j);
			j = deindus.getM1();
			rowgy.createCell((short) 29).setCellValue(j);
			j = deindus.getM2();
			rowgy.createCell((short) 30).setCellValue(j);
			j = deindus.getM3();
			rowgy.createCell((short) 31).setCellValue(j);
			j = deindus.getM4();
			rowgy.createCell((short) 32).setCellValue(j);
			j = deindus.getM5();
			rowgy.createCell((short) 33).setCellValue(j);
			j = deindus.getM6();
			rowgy.createCell((short) 34).setCellValue(j);
			j = deindus.getM7();
			rowgy.createCell((short) 35).setCellValue(j);
			j = deindus.getM8();
			rowgy.createCell((short) 36).setCellValue(j);
			j = deindus.getM9();
			rowgy.createCell((short) 37).setCellValue(j);
			j = deindus.getM10();
			rowgy.createCell((short) 38).setCellValue(j);
			j = deindus.getM11();
			rowgy.createCell((short) 39).setCellValue(j);
			j = deindus.getM12();
			rowgy.createCell((short) 40).setCellValue(j);
		}
		// 总产值
		for (int i = 0; i < com.size(); i++) {
			row = sheet.createRow((int) i + 1);
			DetailsOfindustry deindus = com.get(i);

			row.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				row.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				row.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				row.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				row.createCell((short) 4).setCellValue(s);
			s = deindus.getContact();
			if (s != null)
				row.createCell((short) 5).setCellValue(s);
			s = deindus.getContactNo();
			if (s != null)
				row.createCell((short) 6).setCellValue(s);
			
			s = deindus.getTradeNo1();
			if (s != null)
				row.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				row.createCell((short) 8).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				row.createCell((short) 9).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				row.createCell((short) 10).setCellValue(s);
			
			j = deindus.getE_point();
			row.createCell((short) 11).setCellValue(j);
			j = deindus.getN_point();
			row.createCell((short) 12).setCellValue(j);

			j = deindus.getGDP();
			row.createCell((short) 13).setCellValue(j);
			k = deindus.getFillyear();
			row.createCell((short) 14).setCellValue(k);
			j = deindus.getDaysOfWork();
			row.createCell((short) 15).setCellValue(j);
			j = deindus.getHoursOfDay();
			row.createCell((short) 16).setCellValue(j);
		}
		// 产品、原辅料
		for (int i = 0; i < compro.size(); i++) {
			rowpr = sheet.createRow((int) comde.size() + com.size() + 5 + i);
			DetailsOfindustry deindus = compro.get(i);

			rowpr.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				rowpr.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				rowpr.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				rowpr.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				rowpr.createCell((short) 4).setCellValue(s);

			s = deindus.getTradeNo1();
			if (s != null)
				rowpr.createCell((short) 5).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				rowpr.createCell((short) 6).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				rowpr.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				rowpr.createCell((short) 8).setCellValue(s);
			j = deindus.getE_point();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 9).setCellValue("");
			else
				rowpr.createCell((short) 9).setCellValue(j);
			j = deindus.getN_point();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 10).setCellValue("");
			else
				rowpr.createCell((short) 10).setCellValue(j);

			s = deindus.getStepSerial();
			if (s != null)
				rowpr.createCell((short) 11).setCellValue(s);
			k = deindus.getPrhoursPerDay();
			if (deindus.getCompanyName() == null && k == 0)
				rowpr.createCell((short) 12).setCellValue("");
			else
				rowpr.createCell((short) 12).setCellValue(k);
			k = deindus.getPrdaysPerYear();
			if (deindus.getCompanyName() == null && k == 0)
				rowpr.createCell((short) 13).setCellValue("");
			else
				rowpr.createCell((short) 13).setCellValue(k);
			s = deindus.getProductName();
			if (s != null)
				rowpr.createCell((short) 14).setCellValue(s);
			j = deindus.getPm1();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 15).setCellValue("");
			else
				rowpr.createCell((short) 15).setCellValue(j);
			j = deindus.getPm2();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 16).setCellValue("");
			else
				rowpr.createCell((short) 16).setCellValue(j);
			j = deindus.getPm3();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 17).setCellValue("");
			else
				rowpr.createCell((short) 17).setCellValue(j);
			j = deindus.getPm4();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 18).setCellValue("");
			else
				rowpr.createCell((short) 18).setCellValue(j);
			j = deindus.getPm5();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 19).setCellValue("");
			else
				rowpr.createCell((short) 19).setCellValue(j);
			j = deindus.getPm6();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 20).setCellValue("");
			else
				rowpr.createCell((short) 20).setCellValue(j);
			j = deindus.getPm7();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 21).setCellValue("");
			else
				rowpr.createCell((short) 21).setCellValue(j);
			j = deindus.getPm8();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 22).setCellValue("");
			else
				rowpr.createCell((short) 22).setCellValue(j);
			j = deindus.getPm9();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 23).setCellValue("");
			else
				rowpr.createCell((short) 23).setCellValue(j);
			j = deindus.getPm10();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 24).setCellValue("");
			else
				rowpr.createCell((short) 24).setCellValue(j);
			j = deindus.getPm11();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 25).setCellValue("");
			else
				rowpr.createCell((short) 25).setCellValue(j);
			j = deindus.getPm12();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 26).setCellValue("");
			else
				rowpr.createCell((short) 26).setCellValue(j);
			j = deindus.getPrdesignOutput();
			if (deindus.getCompanyName() == null && j == 0)
				rowpr.createCell((short) 27).setCellValue("");
			else
				rowpr.createCell((short) 27).setCellValue(j);
			s = deindus.getPunit();
			if (s != null)
				rowpr.createCell((short) 28).setCellValue(s);
			s = deindus.getMaterialName();
			if (s != null)
				rowpr.createCell((short) 29).setCellValue(s);
			j = deindus.getConsumeOfYear();
			if (deindus.getMaterialName() == null && j == 0)
				rowpr.createCell((short) 30).setCellValue("");
			else
				rowpr.createCell((short) 30).setCellValue(j);
			s = deindus.getUnit();
			if (s != null)
				rowpr.createCell((short) 31).setCellValue(s);
		}
		// 污染物排放口
		for (int i = 0; i < compoll.size(); i++) {
			rowpo = sheet.createRow((int) compro.size() + com.size() + comde.size() + 7 + i);
			DetailsOfindustry deindus = compoll.get(i);

			rowpo.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				rowpo.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				rowpo.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				rowpo.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				rowpo.createCell((short) 4).setCellValue(s);

			s = deindus.getTradeNo1();
			if (s != null)
				rowpo.createCell((short) 5).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				rowpo.createCell((short) 6).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				rowpo.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				rowpo.createCell((short) 8).setCellValue(s);
			j = deindus.getE_point();
			rowpo.createCell((short) 9).setCellValue(j);
			j = deindus.getN_point();
			rowpo.createCell((short) 10).setCellValue(j);

			s = deindus.getOutletSerial();
			if (s != null)
				rowpo.createCell((short) 11).setCellValue(s);
			j = deindus.getOutletHeight();
			rowpo.createCell((short) 12).setCellValue(j);
			j = deindus.getOutletDiameter();
			rowpo.createCell((short) 13).setCellValue(j);
			j = deindus.getE_outlet();
			rowpo.createCell((short) 14).setCellValue(j);
			j = deindus.getN_outlet();
			rowpo.createCell((short) 15).setCellValue(j);
			s = deindus.getPollutantName();
			if (s != null)
				rowpo.createCell((short) 16).setCellValue(s);
			j = deindus.getTotalAmount();
			rowpo.createCell((short) 17).setCellValue(j);
			j = deindus.getYearNongdu();
			rowpo.createCell((short) 18).setCellValue(j);
			j = deindus.getS1Nongdu();
			rowpo.createCell((short) 19).setCellValue(j);
			j = deindus.getS2Nongdu();
			rowpo.createCell((short) 20).setCellValue(j);
			j = deindus.getS3Nongdu();
			rowpo.createCell((short) 21).setCellValue(j);
			j = deindus.getS4Nongdu();
			rowpo.createCell((short) 22).setCellValue(j);
			j = deindus.getOutletVelocity();
			rowpo.createCell((short) 23).setCellValue(j);
			j = deindus.getOutletTemperature();
			rowpo.createCell((short) 24).setCellValue(j);
			j = deindus.getOutletFlow();
			rowpo.createCell((short) 25).setCellValue(j);
			j = deindus.getOutletTotal();
			rowpo.createCell((short) 26).setCellValue(j);
		}
		// 治理设施
		for (int i = 0; i < comfac.size(); i++) {
			rowfac = sheet.createRow((int) compro.size() + com.size() + comde.size() + compoll.size() + 9 + i);
			DetailsOfindustry deindus = comfac.get(i);

			rowfac.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				rowfac.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				rowfac.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				rowfac.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				rowfac.createCell((short) 4).setCellValue(s);

			s = deindus.getTradeNo1();
			if (s != null)
				rowfac.createCell((short) 5).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				rowfac.createCell((short) 6).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				rowfac.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				rowfac.createCell((short) 8).setCellValue(s);
			j = deindus.getE_point();
			rowfac.createCell((short) 9).setCellValue(j);
			j = deindus.getN_point();
			rowfac.createCell((short) 10).setCellValue(j);

			s = deindus.getFoutletSerial();
			if (s != null)
				rowfac.createCell((short) 11).setCellValue(s);
			s = deindus.getFpollutantName();
			if (s != null)
				rowfac.createCell((short) 12).setCellValue(s);
			s = deindus.getTechnique1();
			if (s != null)
				rowfac.createCell((short) 13).setCellValue(s);
			s = deindus.getTechnique2();
			if (s != null)
				rowfac.createCell((short) 14).setCellValue(s);
			s = deindus.getFacilityModel();
			if (s != null)
				rowfac.createCell((short) 15).setCellValue(s);
			s = deindus.getFstepSerial();
			if (s != null)
				rowfac.createCell((short) 16).setCellValue(s);
			s = deindus.getFstepName();
			if (s != null)
				rowfac.createCell((short) 17).setCellValue(s);
			s = deindus.getFdeviceSerial();
			if (s != null)
				rowfac.createCell((short) 18).setCellValue(s);
			s = deindus.getFdeviceName();
			if (s != null)
				rowfac.createCell((short) 19).setCellValue(s);
			s = deindus.getFdeviceName2();
			if (s != null)
				rowfac.createCell((short) 20).setCellValue(s);
			s = deindus.getFmaterialName();
			if (s != null)
				rowfac.createCell((short) 21).setCellValue(s);
			j = deindus.getFmaterialConsume();
			rowfac.createCell((short) 22).setCellValue(j);
			j = deindus.getYearCost();
			rowfac.createCell((short) 23).setCellValue(j);
			j = deindus.getDisRate();
			rowfac.createCell((short) 24).setCellValue(j);
			k = deindus.getFdaysPerYear();
			rowfac.createCell((short) 25).setCellValue(k);
			j = deindus.getNH3Release();
			rowfac.createCell((short) 26).setCellValue(j);
			j = deindus.getCollectRate();
			rowfac.createCell((short) 27).setCellValue(j);
		}
		// 用电信息
		for (int i = 0; i < comelec.size(); i++) {
			rowel = sheet.createRow(
					(int) compro.size() + com.size() + comde.size() + compoll.size() + comfac.size() + 11 + i);
			DetailsOfindustry deindus = comelec.get(i);

			rowel.createCell((short) 0).setCellValue(i + 1);
			s = deindus.getCompanyName();
			if (s != null)
				rowel.createCell((short) 1).setCellValue(s);
			s = deindus.getCity();
			if (s != null)
				rowel.createCell((short) 2).setCellValue(s);
			s = deindus.getTown();
			if (s != null)
				rowel.createCell((short) 3).setCellValue(s);
			s = deindus.getTradeName();
			if (s != null)
				rowel.createCell((short) 4).setCellValue(s);

			s = deindus.getTradeNo1();
			if (s != null)
				rowel.createCell((short) 5).setCellValue(s);
			s = deindus.getTradeNo2();
			if (s != null)
				rowel.createCell((short) 6).setCellValue(s);
			s = deindus.getTradeNo3();
			if (s != null)
				rowel.createCell((short) 7).setCellValue(s);
			s = deindus.getTradeNo4();
			if (s != null)
				rowel.createCell((short) 8).setCellValue(s);
			j = deindus.getE_point();
			rowel.createCell((short) 9).setCellValue(j);
			j = deindus.getN_point();
			rowel.createCell((short) 10).setCellValue(j);

			s = deindus.getElecDeviceName();
			if (s != null)
				rowel.createCell((short) 11).setCellValue(s);
			j = deindus.getElecPerDay();
			rowel.createCell((short) 12).setCellValue(j);
			j = deindus.getElecPerYear();
			rowel.createCell((short) 13).setCellValue(j);
		}

		try {
			String fileName = "工业源填报信息统计表.xls";
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			File fp = new File(path);
			if (!fp.exists()) {
				fp.mkdirs();// 目录不存在的情况下，创建目录。
			}

			path += "/" + fileName;
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private Date String(double j) {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes"})
	@RequestMapping(value = "/industryport")
	@ResponseBody
	private ResponseEntity<byte[]> download() {
		try {
			String fileName = "工业源填报信息统计表.xls";
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/" + fileName;

			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			String dfileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);
			return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
