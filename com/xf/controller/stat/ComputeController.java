package com.xf.controller.stat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.dao.ISfacilityDao;
import com.xf.security.LoginManage;
import com.xf.service.DeviceService;
import com.xf.service.PollutantService;
import com.xf.service.ProductFillService;
import com.xf.service.ProductService;
import com.xf.service.StaticService;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsParamService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.FactorService;
import com.xf.service.gov.GovFactorService;
import com.xf.service.gov.MotorVehicleService;
import com.xf.service.gov.PlaneService;
import com.xf.service.gov.VehicleRepairingService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.MotorStandartService;
import com.xf.service.stat.NumerationService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/compute")
public class ComputeController {
	@Autowired
	private ComputeService computeService;
	@Autowired
	private GovFactorService govFactorService;
	@Autowired
	private NumerationService numerationService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
	@Autowired
	private GovFactorService gfservice;
	@Autowired
	private VehicleRepairingService vrservice;
	@Autowired
	private PlaneService planeservice;
	@Autowired
	private PollutantService pollservice;
	@Autowired
	private CanyinStatService cyservice;
	@Autowired
	private AnimalsFarmService afservice;
	@Autowired
	private AnimalsWildService awservice;
	@Autowired
	private AnimalsParamService apservice;
	@Autowired
	private StaticService staticservice;
	@Autowired
	private MotorStandartService motorService;
	@Autowired
	private MotorVehicleService mvService;
	@Autowired
	public ProductFillService pfService;
	@Autowired
	public ProductService pService;
	@Autowired
	public ISfacilityDao sfDao;
	@Autowired
	public ComputeService comService;
	@Autowired
	public DeviceService devService;
	@Autowired
	public FactorService facService;
	@Autowired
	public GovFactorService gfService;
	@Autowired
	public PollutantService pollutantService;

	@RequestMapping(value = "/fuel/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object inertFuelres(HttpServletRequest request,@PathVariable int fillyear) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		Compute compute = Compute.getInstance(computeService,fillyear,pollutantService);
//		SmallCompute smallcom= SmallCompute.getInstance(pfService, pService, sfDao, comService, devService, facService, gfService, fillyear);
		if (compute.getFlag() >= 100 || compute.getFlag()==0) {
			compute.setFlag(1);
			new Thread(compute).start();
//			new Thread(smallcom).start();
		} else {
			result.put("process", compute.getFlag());
		}
		
		return result.setStatus(0, Compute.getMsg());
	}
	
	@RequestMapping(value = "/status/{fillyear}", method = RequestMethod.GET)
	@ResponseBody
	public Object getProcess(HttpServletRequest request,@PathVariable int fillyear) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		Compute compute = Compute.getInstance(computeService,fillyear,pollutantService);
		result.put("process", compute.getFlag());
		if(compute.getFlag()==-1){
			return result.setStatus(-2, Compute.getMsg());
		}
		return result.setStatus(0, Compute.getMsg());
	}
	
	@RequestMapping(value = "/computeAll/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object inertAll(HttpServletRequest request,@PathVariable int fillyear,@PathVariable int typeid) {
//		if (!loginManage.isUserLogin(request))
//			return result.setStatus(-1, "No login.");

		//typeid为区分省级计算和市级计算，1为省级，2为市级
		ComputeAll computeAll = ComputeAll.getInstance(computeService,govFactorService,numerationService, gfservice,
				vrservice, planeservice, pollservice, cyservice,afservice,awservice,apservice,staticservice,fillyear,typeid);
		if (computeAll.getFlag() >= 100 || computeAll.getFlag()==0) {
			computeAll.setFlag(1);
			new Thread(computeAll).start();
		} else {
			result.put("process", computeAll.getFlag());
		}
		
		return result.setStatus(0, ComputeAll.getMsg());
	}
	
	@RequestMapping(value = "/statusAll/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllProcess(HttpServletRequest request,@PathVariable int fillyear,@PathVariable int typeid) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");
		ComputeAll computeAll = ComputeAll.getInstance(computeService,govFactorService,numerationService, gfservice, 
				vrservice, planeservice, pollservice, cyservice,afservice,awservice,apservice,staticservice,fillyear,typeid);
		result.put("process", computeAll.getFlag());
		if(computeAll.getFlag()==-1){
			return result.setStatus(-2, ComputeAll.getMsg());
		}
		return result.setStatus(0, ComputeAll.getMsg());
	}
	
	@RequestMapping(value = "/motor/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object inertMotorres(HttpServletRequest request,@PathVariable int fillyear,@PathVariable int typeid) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		ComputeMotor compute = ComputeMotor.getInstance(motorService,mvService,fillyear,typeid);
		if (compute.getFlag() >= 100 || compute.getFlag()==0) {
			compute.setFlag(1);
			new Thread(compute).start();
		} else {
			result.put("process", compute.getFlag());
		}
		
		return result.setStatus(0, ComputeMotor.getMsg());
	}
	
	@RequestMapping(value = "/statusMotor/{fillyear}/{typeid}", method = RequestMethod.GET)
	@ResponseBody
	public Object getMotorProcess(HttpServletRequest request,@PathVariable int fillyear,@PathVariable int typeid) {
		if (!loginManage.isUserLogin(request))
			return result.setStatus(-1, "No login.");

		ComputeMotor compute = ComputeMotor.getInstance(motorService,mvService,fillyear,typeid);
		result.put("process", compute.getFlag());
		if(compute.getFlag()==-1){
			return result.setStatus(-2, ComputeMotor.getMsg());
		}
		return result.setStatus(0, ComputeMotor.getMsg());
	}
}
