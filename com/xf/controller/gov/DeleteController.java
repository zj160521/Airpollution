package com.xf.controller.gov;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.entity.Company;
import com.xf.security.LoginManage;
import com.xf.service.gov.AnimalsFarmService;
import com.xf.service.gov.AnimalsWildService;
import com.xf.service.gov.BoatService;
import com.xf.service.gov.CanyinCertifiedService;
import com.xf.service.gov.CanyinNocertService;
import com.xf.service.gov.CanyinStatService;
import com.xf.service.gov.CleanerService;
import com.xf.service.gov.ConstructionDustService;
import com.xf.service.gov.ConstructionService;
import com.xf.service.gov.EnergyConsumeService;
import com.xf.service.gov.EnergySellService;
import com.xf.service.gov.EquipmentFarmService;
import com.xf.service.gov.EquipmentService;
import com.xf.service.gov.FirewoodService;
import com.xf.service.gov.GasstationService;
import com.xf.service.gov.GknumberService;
import com.xf.service.gov.HouseholdFuelService;
import com.xf.service.gov.IndustryService;
import com.xf.service.gov.NfertilizerService;
import com.xf.service.gov.OildepotService;
import com.xf.service.gov.PesticideService;
import com.xf.service.gov.PlaneService;
import com.xf.service.gov.RoadDustService;
import com.xf.service.gov.VehicleActionService;
import com.xf.service.gov.VehicleRepairingService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/deletecom")
public class DeleteController {

	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;
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
	private IndustryService industryService;
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
	private PesticideService pesticideService;
	@Autowired
	private EnergySellService energySellService;
	@Autowired
	private EnergyConsumeService energyConsumeService;
	@Autowired
	private ConstructionService constructionService;
	

	private Company c;

	@RequestMapping(value = "/delet", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request) {

		if (loginManage.isCompanyLogin(request)) {
			c = loginManage.getLoginCompany(request);
		} else {
			return result.setStatus(-1, "需面源登录");
		}
		String Num = request.getParameter("number");
		if (Num != null && Num != "") {
			int num = Integer.parseInt(Num);
			switch (c.getTypeid()) {
			case 3:
				String Id = request.getParameter("id");
				int id = Integer.parseInt(Id);
				if (num == 1)
					canyinStatService.delete(id);
				if (num == 2)
					canyinNocertService.delete(id);
				if (num == 3)
					canyinCertService.delete(id);
				break;
			case 4:
				String Id1 = request.getParameter("id");
				int id1 = Integer.parseInt(Id1);
				if (num == 1)
					animalFarmService.delete(id1);
				if (num == 2)
					animalWildService.delete(id1);
				break;
			case 5:
				String Id2 = request.getParameter("id");
				int id2 = Integer.parseInt(Id2);
				if (num == 1)
					nfertilizerService.delete(id2);
				break;
			case 6:
				String Id3 = request.getParameter("id");
				String fillYear = request.getParameter("fillyear");
				int id3 = Integer.parseInt(Id3);
				int year = 0;
				if(fillYear != null){
					year = Integer.parseInt(fillYear);
				}				
				if (num == 1)
					firewoodService.delete(id3, year);
				else if (num == 2)
					pesticideService.delete(id3, year);
				else if (num == 3)
					equipmentFarmService.delete(id3);
				else if (num == 4)
					nfertilizerService.delete(id3);
				break;
			case 7:
				String Id4_1 = request.getParameter("id");
				String[] Id4 = request.getParameterValues("id[]");
				if (Id4_1 != null) {
					int id4_1 = Integer.parseInt(Id4_1);
					if (num == 3)
						roadDustService.delete(id4_1);
				} else {
					if (Id4.length > 0) {
						for (int i = 0; i < Id4.length; i++) {
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id4[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}

				break;
			case 8:
				String Id5_1 = request.getParameter("id");
				String[] Id5 = request.getParameterValues("id[]");
				if (Id5_1 != null) {
					int id5_1 = Integer.parseInt(Id5_1);
					if (num == 4)
						constructionService.delete(id5_1);
				} else {
					if (Id5.length > 0) {
						for (int i = 0; i < Id5.length; i++) {
							if (num == 1)
								cDustService.delete(Integer.parseInt(Id5[i]));
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id5[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}

				break;
			case 9:
				String Id6 = request.getParameter("id");
				int id6 = Integer.parseInt(Id6);
				if (num == 1)
					industryService.delete(id6);
				if (num == 2)
					energyConsumeService.delete(id6);
				if (num == 3)
					energySellService.delete(id6);
				break;
			case 10:
				String Id7 = request.getParameter("id");
				int id7 = Integer.parseInt(Id7);
				if (num == 1)
					gasstationService.delete(id7);
				if (num == 2)
					oilportService.delete(id7);
				if (num == 3)
					cleanerService.delete(id7);
				if (num == 4)
					houseFuelService.delete(id7);
				if (num == 5)
					vehicleActionService.delete(id7);
				break;
			case 11:
				String Id8 = request.getParameter("id");
				int id8 = Integer.parseInt(Id8);
				if (num == 3)
					cleanerService.delete(id8);
				break;
			case 12:
				String Id9 = request.getParameter("id");
				int id9 = Integer.parseInt(Id9);
				if (num == 1)
					gknumberService.delete(id9);
				break;
			case 13:
				String Id10 = request.getParameter("id");
				int id10 = Integer.parseInt(Id10);
				if (num == 1)
					gasstationService.delete(id10);
				if (num == 2)
					oilportService.delete(id10);
				if (num == 3)
					energySellService.delete(id10);
				break;
			case 14:
				String Id11_1 = request.getParameter("id");
				String[] Id11 = request.getParameterValues("id[]");
				if (Id11_1 != null) {
					int id11_1 = Integer.parseInt(Id11_1);
					if (num == 2)
						vehicleService.delete(id11_1);
				} else {
					if (Id11.length > 0) {
						for (int i = 0; i < Id11.length; i++) {
							if (num == 1)
								boatService.delete(Integer.parseInt(Id11[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}
				break;
			case 15:
				String[] Id12 = request.getParameterValues("id[]");
				if (Id12.length > 0) {
					for (int i = 0; i < Id12.length; i++) {
						if (num == 1)
							boatService.delete(Integer.parseInt(Id12[i]));
					}
				}
				break;
			case 16:
				String Id13 = request.getParameter("id");
				int id13 = Integer.parseInt(Id13);
				if (num == 1)
					planeService.delete(id13);
				break;
			case 17:
				String Id14 = request.getParameter("id");
				int id14 = Integer.parseInt(Id14);
				if (num == 3)
					equipmentFarmService.delete(id14);
				break;
			case 18:
				String Id15 = request.getParameter("id");
				int id15 = Integer.parseInt(Id15);
				if (num == 3)
					energySellService.delete(id15);
				break;
			case 19:
				String Id16 = request.getParameter("id");
				int id16 = Integer.parseInt(Id16);
				if (num == 2)
					energyConsumeService.delete(id16);
				if (num == 3)
					energySellService.delete(id16);
				if (num == 4)
					houseFuelService.delete(id16);
				break;
			case 20:
				String Id17_1 = request.getParameter("id");
				String[] Id17 = request.getParameterValues("id[]");
				if (Id17_1 != null) {
					int id17_1 = Integer.parseInt(Id17_1);
					if (num == 4)
						constructionService.delete(id17_1);
				} else {
					if (Id17.length > 0) {
						for (int i = 0; i < Id17.length; i++) {
							if (num == 1)
								cDustService.delete(Integer.parseInt(Id17[i]));
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id17[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}
				break;
			case 21:
				// int id18 = Integer.parseInt(request.getParameter("id"));
				// if(num == 1) dumpFieldService.delete(id18);
				break;
			case 22:
				int id19 = Integer.parseInt(request.getParameter("id"));
				if (num == 3)
					energySellService.delete(id19);
				break;
			case 23:
				int id20 = Integer.parseInt(request.getParameter("id"));
				int fillyear =  Integer.parseInt(request.getParameter("fillyear"));
				if (num == 2)
					pesticideService.delete(id20, fillyear);
				break;
			case 24:
				int id21 = Integer.parseInt(request.getParameter("id"));
				if (num == 5)
					vehicleActionService.delete(id21);
				break;
			case 25:
				String Id22_1 = request.getParameter("id");
				String[] Id22 = request.getParameterValues("id[]");
				if (Id22_1 != null) {
					int id22_1 = Integer.parseInt(Id22_1);
					if (num == 4)
						constructionService.delete(id22_1);
					if (num == 3)
						roadDustService.delete(id22_1);
				} else {
					if (Id22.length > 0) {
						for (int i = 0; i < Id22.length; i++) {
							if (num == 1)
								cDustService.delete(Integer.parseInt(Id22[i]));
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id22[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}
				break;
			case 26:
				String Id23_1 = request.getParameter("id");
				String[] Id23 = request.getParameterValues("id[]");
				if (Id23_1 != null) {
					int id23_1 = Integer.parseInt(Id23_1);
					if (num == 4)
						constructionService.delete(id23_1);
				} else {
					if (Id23.length > 0) {
						for (int i = 0; i < Id23.length; i++) {
							if (num == 1)
								cDustService.delete(Integer.parseInt(Id23[i]));
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id23[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}
				break;
			case 27:
				int id24 = Integer.parseInt(request.getParameter("id"));
				if (num == 2)
					vehicleService.delete(id24);
				break;
			case 28:
				int id25 = Integer.parseInt(request.getParameter("id"));
				int year2 = Integer.parseInt(request.getParameter("fillyear"));
				if (num == 1)
					firewoodService.delete(id25, year2);
				if (num == 2)
					pesticideService.delete(id25, year2);
				break;
			case 29:
				int id26 = Integer.parseInt(request.getParameter("id"));
				if (num == 4)
					houseFuelService.delete(id26);
				if (num == 2)
					energyConsumeService.delete(id26);
				if (num == 3)
					energySellService.delete(id26);
				if (num == 1)
					gasstationService.delete(id26);
				break;
			case 30:
				// int id27 = Integer.parseInt(request.getParameter("id"));
				// if(num == 1) dumpFieldService.delete(id27);
				break;
			case 31:
				int id28 = Integer.parseInt(request.getParameter("id"));
				if (num == 1)
					gasstationService.delete(id28);
				if (num == 2)
					oilportService.delete(id28);
				break;
			case 32:
				int id29 = Integer.parseInt(request.getParameter("id"));
				if (num == 5)
					vehicleActionService.delete(id29);
				break;
			case 33:
				String Id30_1 = request.getParameter("id");
				String[] Id30 = request.getParameterValues("id[]");
				if (Id30_1 != null) {
					int id30_1 = Integer.parseInt(Id30_1);
					if (num == 4)
						constructionService.delete(id30_1);
					if (num == 3)
						roadDustService.delete(id30_1);
				} else {
					if (Id30.length > 0) {
						for (int i = 0; i < Id30.length; i++) {
							if (num == 1)
								cDustService.delete(Integer.parseInt(Id30[i]));
							if (num == 2)
								equipmentService.delete(Integer.parseInt(Id30[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				}
				break;
			case 34:
				String id31 = request.getParameter("id");
				String[] Id31 = request.getParameterValues("id[]");
				if (num == 2)
					vehicleService.delete(Integer.parseInt(id31));
				if (num == 3)
					roadDustService.delete(Integer.parseInt(id31));
				if (num==1)
					if (Id31.length > 0) {
						for (int i = 0; i < Id31.length; i++) {
							if (num == 1)
								boatService.delete(Integer.parseInt(Id31[i]));
						}
					} else {
						return result.setStatus(-2, "数组为空");
					}
				break;
			case 35:
				int id32 = Integer.parseInt(request.getParameter("id"));
				if (num == 1)
					planeService.delete(id32);
				break;
			case 36:
				int id33 = Integer.parseInt(request.getParameter("id"));
				if (num == 4)
					nfertilizerService.delete(id33);
				if (num == 1)
					firewoodService.delete2(id33,c.getId());
				if (num == 3)
					equipmentFarmService.delete(id33);
				if (num == 2)
					pesticideService.delete2(id33, c.getId());
				break;
			case 37:
				int id34 = Integer.parseInt(request.getParameter("id"));
				if (num == 1)
					industryService.delete(id34);
				break;
			case 38:
				int id35 = Integer.parseInt(request.getParameter("id"));
				if (num == 3)
					cleanerService.delete(id35);
				break;	
			case 39:
				int id36 = Integer.parseInt(request.getParameter("id"));
				if (num == 1)
					canyinStatService.delete(id36);
				if (num == 2)
					canyinNocertService.delete(id36);
				if (num == 3)
					canyinCertService.delete(id36);
				break;
			case 40:
				int id37 = Integer.parseInt(request.getParameter("id"));
				if (num == 1)
					gknumberService.delete(id37);
				break;		
			case 41:
				int id38 = Integer.parseInt(request.getParameter("id"));
				if (num == 4)
					houseFuelService.delete(id38);
				if (num == 2)
					energyConsumeService.delete(id38);
				break;		
				
			default:
				return result.setStatus(-3, "invalid account");
			}
		} else {
			return result.setStatus(-3, "无id或number");
		}
		return result.setStatus(0, "ok");
	}
}
