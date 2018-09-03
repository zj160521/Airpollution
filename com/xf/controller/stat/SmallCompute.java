package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.dao.ISfacilityDao;
import com.xf.dao.gov.IProportionDao;
import com.xf.entity.Devices;
import com.xf.entity.Product;
import com.xf.entity.ProductFill;
import com.xf.entity.Proportion;
import com.xf.entity.SmallFacility;
import com.xf.entity.gov.Factor;
import com.xf.entity.gov.GovFactor;
import com.xf.service.DeviceService;
import com.xf.service.ProductFillService;
import com.xf.service.ProductService;
import com.xf.service.gov.FactorService;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.vo.StatDevice;
import com.xf.vo.StatProd;

public class SmallCompute implements Runnable {
	private static SmallCompute compute;
	public static ProductFillService pfService;
	public static ProductService pService;
	public static ISfacilityDao sfDao;
	public static ComputeService comService;
	public static DeviceService devService;
	public static FactorService facService;
	public static GovFactorService gfService;
	public static IProportionDao proDao;
	public static int fillyear;

	public void productCompute(ProductFillService pfService,
			ProductService pService, ISfacilityDao sfDao,
			ComputeService comService, int fillyear) {

		List<ProductFill> pflist = pfService.getotalYear(fillyear);
		List<Product> plist = pService.getProductfac();
		List<SmallFacility> sflist = sfDao.getRemoval(fillyear);
		List<Proportion> proList = proDao.getByName("企业源-小散企业");

		List<StatProd> splist = new ArrayList<StatProd>();
		for (ProductFill pf : pflist) {
			for (Product pl : plist) {
				if (pf.getProductId() == pl.getId()) {
					int poll = pl.getPollutantId();
					if (pl.getProductName() != null
							&& pl.getProductName().equals("水泥")) {
						if (poll == 1 || poll == 2 || poll == 3 || poll == 9
								|| poll == 10)
							continue;
					}
					if (pl.getProductName() != null
							&& pl.getProductName().equals("砖瓦")) {
						if (poll == 3)
							continue;
					}
					if (pl.getProductName() != null
							&& pl.getProductName().equals("石灰")) {
						if (poll == 1 || poll == 2 || poll == 3)
							continue;
					}

					double statValue = pf.getRealOutput() * pl.getFactor()
							* 0.001;
					for(Proportion pro:proList){
						StatProd statProduct = new StatProd();
						statProduct.setCompanyid(pf.getEnterpriceId());
						statProduct.setProductid(pl.getId());
						statProduct.setPollutantId(pl.getPollutantId());
						statProduct.setFillyear(fillyear);
						statProduct.setStatvalue(statValue*pro.getProportion()*0.01);
						statProduct.setStatmonth(pro.getMonths());
						statProduct.setStat_exp("产品产量*排放因子*（1-去除效率）*0.001");
						statProduct.setStat_valtype("计算产品产量产生的污染物排量");
						statProduct.setStat_value(pf.getRealOutput() + "*"
								+ pl.getFactor() + "*0.001");
						statProduct.setStat_factor(pl.getFactor() + "");
						splist.add(statProduct);
					}
					
				}
			}
		}

		for (StatProd spli : splist) {
			for (SmallFacility sfli : sflist) {
				if (spli.getCompanyid() == sfli.getEnterpriceId()) {
					if (spli.getPollutantId() == 1) {
						spli.setStatvalue(spli.getStatvalue()
								* (1 - sfli.getRemoval1()));
						spli.setStat_dsrate(sfli.getRemoval1());
						spli.setStat_value(spli.getStat_value() + "*(1-"
								+ sfli.getRemoval1() + ")");
					}
					if (spli.getPollutantId() == 2) {
						spli.setStatvalue(spli.getStatvalue()
								* (1 - sfli.getRemoval2()));
						spli.setStat_dsrate(sfli.getRemoval2());
						spli.setStat_value(spli.getStat_value() + "*(1-"
								+ sfli.getRemoval2() + ")");
					}
					if (spli.getPollutantId() == 4
							|| spli.getPollutantId() == 5
							|| spli.getPollutantId() == 6) {
						spli.setStatvalue(spli.getStatvalue()
								* (1 - sfli.getRemoval3()));
						spli.setStat_dsrate(sfli.getRemoval3());
						spli.setStat_value(spli.getStat_value() + "*(1-"
								+ sfli.getRemoval3() + ")");
					}
					if (spli.getPollutantId() == 9) {
						spli.setStatvalue(spli.getStatvalue()
								* (1 - sfli.getRemoval4()));
						spli.setStat_dsrate(sfli.getRemoval4());
						spli.setStat_value(spli.getStat_value() + "*(1-"
								+ sfli.getRemoval4() + ")");
					}
				}
			}
		}
		if (splist.size() > 0)
			comService.addProdres(splist);
	}

	public void fuelCompute(DeviceService devService, FactorService facService,
			ComputeService comService, ISfacilityDao sfDao,
			GovFactorService gfService, int fillyear) {
		List<Devices> dflist = devService.getotalYear(fillyear);
		for(Devices d:dflist){
			d.setFuelcost(Double.parseDouble(d.getRemark()));
		}
		List<Factor> flist = facService.getFuelfactor(1);
		List<SmallFacility> sflist = sfDao.getRemoval(fillyear);
		List<GovFactor> gflist = gfService.getByTypeName("configuration");
		List<Proportion> proList = proDao.getByName("企业源-小散企业");

		double sulfur = 0;
		double ash = 0;
		for (GovFactor gf : gflist) {
			if (gf.getType_x().equals("设备")) {
				if (gf.getType_y().equals("硫分")) {
					sulfur = gf.getFactor();
				}
				if (gf.getType_y().equals("灰分")) {
					ash = gf.getFactor();
				}
			}
		}

		List<StatDevice> sdlist = new ArrayList<StatDevice>();
		for (Devices df : dflist) {
			for (Factor fli : flist) {
				int poll = fli.getPollutantId();
				if (df.getFuel() == fli.getProductId()) {
					if (df.getFuel() == 2001 || df.getParentId() == 2001) {
						if (poll == 1 || poll == 4 || poll == 5 || poll == 6)
							continue;
					}
					double statValue = fli.getFactor() * df.getFuelcost()
							* 0.001;
					for(Proportion pro:proList){
						StatDevice statdevice = new StatDevice();
						statdevice.setCompanyid(df.getEnterpriceId());
						statdevice.setFillyear(fillyear);
						statdevice.setPollutantId(fli.getPollutantId());
						statdevice.setDeviceid(df.getId());
						statdevice.setFuelid(df.getFuel());
						statdevice.setStatvalue(statValue*pro.getProportion()*0.01);
						statdevice.setStatmonth(pro.getMonths());
						statdevice.setStat_exp("燃料消耗量*排放因子*（1-去除效率）*0.001");
						statdevice.setStat_valtype("计算燃料消耗产生污染物的排放量");
						statdevice.setStat_factor(fli.getFactor() + "");
						statdevice.setStat_value(fli.getFactor() + "*"
								+ df.getFuelcost() + "*0.001");
						sdlist.add(statdevice);
					}
				}
			}
		}
		
		for (Devices df : dflist) {
			for (int i = 1; i <= 10; i++) {
				if (df.getFuel() == 2001 || df.getParentId() == 2001) {
					if (i == 1 && sulfur > 0) {
						double statValue = df.getFuelcost() * 17.0
								* sulfur * 0.001;
						for(Proportion pro:proList){
							StatDevice statdevice = new StatDevice();
							statdevice.setStatvalue(statValue*pro.getProportion()*0.01);
							statdevice.setCompanyid(df.getEnterpriceId());
							statdevice.setFillyear(fillyear);
							statdevice.setPollutantId(i);
							statdevice.setDeviceid(df.getId());
							statdevice.setFuelid(df.getFuel());
							statdevice.setStatmonth(pro.getMonths());
							statdevice
									.setStat_exp("燃料消耗量*平均含硫量（%）*17*（1-去除效率）*0.001");
							statdevice.setStat_valtype("计算燃料消耗产生污染物的排放量");
							statdevice.setStat_value(df.getFuelcost() + "*17*"
									+ sulfur + ")*0.001");
							sdlist.add(statdevice);
						}
					}
					if ((i == 4 || i == 5 || i == 6) && ash > 0) {
						double statValue = df.getFuelcost() * ash * 0.01;
						for(Proportion pro:proList){
							StatDevice statdevice = new StatDevice();
							statdevice.setStatvalue(statValue*pro.getProportion()*0.01);
							statdevice.setCompanyid(df.getEnterpriceId());
							statdevice.setFillyear(fillyear);
							statdevice.setPollutantId(i);
							statdevice.setDeviceid(df.getId());
							statdevice.setFuelid(df.getFuel());
							statdevice.setStatmonth(pro.getMonths());
							statdevice.setStat_exp("燃料消耗量*灰分*灰飞比*排放因子*（1-去除效率）*0.01");
							statdevice.setStat_valtype("计算燃料消耗产生污染物的排放量");
							statdevice.setStat_value(df.getFuelcost() +"*"+ ash + "*0.01");
							sdlist.add(statdevice);
						}
					}
				}
			}
		}

		for (StatDevice sd : sdlist) {
			for (SmallFacility sf : sflist) {
				if (sd.getCompanyid() == sf.getEnterpriceId()) {
					if (sd.getPollutantId() == 1) {
						sd.setStatvalue(sd.getStatvalue()
								* (1 - sf.getRemoval1()));
						sd.setStat_value(sd.getStat_value() + "*(1-"
								+ sf.getRemoval1() + ")");
						sd.setStat_dsrate(sf.getRemoval1());
					}
					if (sd.getPollutantId() == 2) {
						sd.setStatvalue(sd.getStatvalue()
								* (1 - sf.getRemoval2()));
						sd.setStat_value(sd.getStat_value() + "*(1-"
								+ sf.getRemoval2() + ")");
						sd.setStat_dsrate(sf.getRemoval2());
					}
					if (sd.getPollutantId() == 4 || sd.getPollutantId() == 5
							|| sd.getPollutantId() == 6) {
						sd.setStatvalue(sd.getStatvalue()
								* (1 - sf.getRemoval3()));
						sd.setStat_value(sd.getStat_value() + "*(1-"
								+ sf.getRemoval3() + ")");
						sd.setStat_dsrate(sf.getRemoval3());
					}
					if (sd.getPollutantId() == 9) {
						sd.setStatvalue(sd.getStatvalue()
								* (1 - sf.getRemoval4()));
						sd.setStat_value(sd.getStat_value() + "*(1-"
								+ sf.getRemoval4() + ")");
						sd.setStat_dsrate(sf.getRemoval4());
					}
				}
			}
		}
		
		if (sdlist.size() > 0)
			comService.addFuelres(sdlist);
	}

	public void run() {
		productCompute(pfService, pService, sfDao, comService, fillyear);
		fuelCompute(devService, facService, comService, sfDao, gfService,
				fillyear);
	}

	public static SmallCompute getInstance(ProductFillService pfService1,
			ProductService pService1, ISfacilityDao sfDao1,
			ComputeService comService1, DeviceService devService1,
			FactorService facService1, GovFactorService gfService1,
			int fillyear1) {
		if (compute == null) {
			synchronized (SmallCompute.class) {
				if (compute == null) {
					compute = new SmallCompute();
					pfService = pfService1;
					pService = pService1;
					sfDao = sfDao1;
					comService = comService1;
					devService = devService1;
					facService = facService1;
					gfService = gfService1;
					fillyear = fillyear1;
				}
			}
		}
		return compute;
	}
}
