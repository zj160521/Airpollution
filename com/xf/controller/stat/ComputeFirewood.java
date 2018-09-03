package com.xf.controller.stat;

import java.util.ArrayList;
import java.util.List;

import com.xf.entity.gov.GovStat;
import com.xf.service.stat.ComputeService;
import com.xf.service.stat.NumerationService;

public class ComputeFirewood {

	public void compute(ComputeService computeService,NumerationService numerationService,int fillyear,int typeid) {
		System.out.println("ComputeFirewood");
			List<GovStat> list = null;
			if(typeid==1){
				list = computeService.getAllFirewood2(fillyear);
			}else if(typeid==2){
				list = computeService.getAllFirewood(fillyear);
			}
			for(GovStat stat:list){
				stat.setStattype("firewood");
				stat.setStat_exp("E农作物=农作物产量*谷草比*焚烧比例*谷草干燥比*燃烧效率*EF/1000");
				stat.setStat_valtype("计算秸秆和薪柴");
			}
			
			if(list.size() > 0){
				int count=0;
				List<GovStat> newlist=new ArrayList<GovStat>();
				for(GovStat sp:list) {
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
