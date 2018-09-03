package com.xf.controller.stat;

import org.springframework.web.servlet.MyDispatcherServlet;

import com.xf.security.LocalYear;
import com.xf.service.PollutantService;
import com.xf.service.StaticService;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsParamService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.GovFactorService;
import com.xf.service.gov.PlaneService;
import com.xf.service.gov.VehicleRepairingService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeAll implements Runnable{
	private static int flag = 0;
	private static String msg;
	private static ComputeAll computeAll;
	public static ComputeService computeService;
	public static GovFactorService govFactorService;
	public static NumerationService numerationService;
	public static GovFactorService gfservice;
	public static PlaneService planeservice;
	public static PollutantService pollservice;
	public static CanyinStatService cyservice;
	public static VehicleRepairingService vrservice;
	public static AnimalsFarmService afservice;
	public static AnimalsWildService awservice;
	public static AnimalsParamService apservice;
	public static StaticService staticservice;
	public static int fillyear;
	public static int typeid;
	public int getFlag() {
		return flag;
	}
	
	public void setFlag(int f) {
		flag = f;
	}

	public void run() {
		try {
			LocalYear curyear = MyDispatcherServlet.getYear();
			curyear.set(fillyear);
			if(typeid==1){
				computeService.deleteAll2(fillyear);
			}else if(typeid==2){
				computeService.deleteAll(fillyear);
			}
			
			flag = 10;
			ComputeConstructiondust computeConstructiondust=new ComputeConstructiondust();
			computeConstructiondust.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=20;
			ComputeConstructionmachine computeConstructionmachine=new ComputeConstructionmachine();
			computeConstructionmachine.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=30;
			ComputeEquipmentfarm computeEquipmentfarm=new ComputeEquipmentfarm();
			computeEquipmentfarm.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=40;
			ComputeFirewood computeFirewood=new ComputeFirewood();
			computeFirewood.compute(computeService, numerationService,fillyear,typeid);
			flag=50;
			ComputeGasStation computeGasStation=new ComputeGasStation();
			computeGasStation.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=60;
			ComputeHouseholdFuel computeHouseholdFuel=new ComputeHouseholdFuel();
			computeHouseholdFuel.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=70;
			ComputeNfertilizer computeNfertilizer=new ComputeNfertilizer();
			computeNfertilizer.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=80;
			ComputeRoaddust computeRoaddust=new ComputeRoaddust();
			computeRoaddust.compute(computeService, govFactorService, numerationService,fillyear,typeid);
			flag=90;
			Numeration numeration=new Numeration();
			numeration.runstart(numerationService,fillyear,typeid);
			Calculate calc=new Calculate();
			calc.cal(numerationService, gfservice, vrservice, planeservice, pollservice, cyservice,afservice,awservice,apservice,staticservice,fillyear,typeid);
			flag = 100;
		} catch (Exception e) {
			msg = e.getMessage();
			flag = -1;
		}
	}

	public static ComputeAll getInstance(ComputeService computeService1,GovFactorService govFactorService1
			,NumerationService numerationService1,GovFactorService gfservice1,VehicleRepairingService vrservice1
			,PlaneService planeservice1,PollutantService pollservice1,CanyinStatService cyservice1,AnimalsFarmService afservice1
			,AnimalsWildService awservice1,AnimalsParamService apservice1,StaticService staticservice1,int fillyear1,int typeid1) {
		if (computeAll == null) {
			synchronized (ComputeAll.class) {
				if (computeAll == null) {
					computeAll = new ComputeAll();
					computeService = computeService1;
					govFactorService=govFactorService1;
					numerationService=numerationService1;
					gfservice=gfservice1;
					vrservice=vrservice1;
					planeservice=planeservice1;
					pollservice=pollservice1;
					cyservice=cyservice1;
					afservice=afservice1;
					awservice=awservice1;
					apservice=apservice1;
					staticservice=staticservice1;
					fillyear=fillyear1;
					typeid=typeid1;
				}
			}
		}
		else {
			if (flag >= 100 || flag <= 0) {
				computeService = computeService1;
				govFactorService=govFactorService1;
				numerationService=numerationService1;
				gfservice=gfservice1;
				vrservice=vrservice1;
				planeservice=planeservice1;
				pollservice=pollservice1;
				cyservice=cyservice1;
				afservice=afservice1;
				awservice=awservice1;
				apservice=apservice1;
				staticservice=staticservice1;
				fillyear=fillyear1;
				typeid=typeid1;
			}
		}
		return computeAll;
	}


	public static String getMsg() {
		return msg;
	}

}
