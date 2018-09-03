package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.ConstructionDust;
import com.xf.entity.gov.GovFactor;
import com.xf.entity.gov.GovStat;
import com.xf.service.gov.GovFactorService;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeConstructiondust {

	public void compute(ComputeService computeService,
			GovFactorService govFactorService,
			NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeConstructiondust");
		List<ConstructionDust> list =null;
		if(typeid==1){
			list = computeService.getAllConstructiondust2(fillyear);
		}else if(typeid==2){
			list = computeService.getAllConstructiondust(fillyear);
		}
		
		List<GovFactor> factorList = govFactorService
				.getByTypeName("constructiondust");
		GovFactor factor1 = factorList.get(0);
		GovFactor factor2 = factorList.get(1);
		GovFactor factor3 = factorList.get(2);
		GovFactor factor4 = factorList.get(3);

		List<GovStat> resList = new ArrayList<GovStat>();
		if(list != null && list.size() > 0){
			for (ConstructionDust dust : list) {
				GovStat stat1 = new GovStat();
				stat1.setCity(dust.getCity());
				stat1.setFillyear(dust.getFillyear());
				stat1.setPollutantId(4);
				stat1.setProvince(dust.getProvince());
				stat1.setStattype("constructiondust");
				stat1.setTown(dust.getTown());
				stat1.setStatvalue(dust.getHasStartedArea() * factor1.getFactor() * factor2.getFactor());
				stat1.setStat_exp("E=A*EF*K");
				stat1.setStat_valtype("计算施工扬尘");
				stat1.setStat_factor(factor1.getFactor()+" "+factor2.getFactor());
				stat1.setStat_value(dust.getHasStartedArea() +" X "+ factor1.getFactor() +" X "+ factor2.getFactor());
				stat1.setStat_dsrate(0);
				resList.add(stat1);

				GovStat stat2 = new GovStat();
				stat2.setCity(dust.getCity());
				stat2.setFillyear(dust.getFillyear());
				stat2.setPollutantId(6);
				stat2.setProvince(dust.getProvince());
				stat2.setStattype("constructiondust");
				stat2.setTown(dust.getTown());
				stat2.setStatvalue(dust.getHasStartedArea() * factor3.getFactor()
						* factor4.getFactor());
				stat2.setStat_exp("E=A*EF*K");
				stat2.setStat_valtype("计算施工扬尘");
				stat2.setStat_factor(factor3.getFactor()+" "+factor4.getFactor());
				stat2.setStat_value(dust.getHasStartedArea() +" X "+ factor3.getFactor() +" X "+ factor4.getFactor());
				stat2.setStat_dsrate(0);
				resList.add(stat2);
			}
		}

		if(resList.size()>0){
			int count=0;
			List<GovStat> newlist=new ArrayList<GovStat>();
			for(GovStat sp:resList) {
				if(count > 0 && count % 100 == 0) {
					if(typeid==1){
						numerationService.addGovStat2(newlist);
					}else if(typeid==2){
						numerationService.addGovStat(newlist);
					}
					
					newlist.clear();
				}
				else {
					newlist.add(sp);
				}
				count++;
			}
			if(newlist.size() > 0){
				if(typeid==1){
					numerationService.addGovStat2(newlist);
				}else if(typeid==2){
					numerationService.addGovStat(newlist);
				}
			}
		}
	}

}
