package com.xf.security;

import java.util.ArrayList;
import java.util.Calendar;

public class SeperateTable {

	private static ArrayList<String> tables = new ArrayList<String>();

	private SeperateTable() {
		tables.clear();
		tables.add("ap_animals_farm");
		tables.add("ap_animals_wild");
		tables.add("ap_boat");
		tables.add("ap_canyin_certified");
		tables.add("ap_canyin_nocert");
		tables.add("ap_canyin_stat");
		tables.add("ap_cleaner");
		tables.add("ap_construction");
		tables.add("ap_constructiondust");
		tables.add("ap_control_facilities");
		tables.add("ap_devfill");
		tables.add("ap_devices");
		tables.add("ap_dump_field");
		tables.add("ap_elec");
		tables.add("ap_elec_fill");
		tables.add("ap_energy_consume");
		tables.add("ap_energy_sell");
		tables.add("ap_enterprise_fill");
		tables.add("ap_equipment");
		tables.add("ap_equipment_farm");
		tables.add("ap_facilities_fill");
		tables.add("ap_facility_device");
		tables.add("ap_facility_step");
		tables.add("ap_factor");
		tables.add("ap_producestep");
		tables.add("ap_firewood");
		tables.add("ap_firewood_param");
		tables.add("ap_gasstation");
		tables.add("ap_gknumber");
		tables.add("ap_govfactor");
		tables.add("ap_household_fuel");
		tables.add("ap_industry");
		tables.add("ap_material_fill");
		tables.add("ap_motor_vehicle");
		tables.add("ap_motor_vehicle_pc");
		tables.add("ap_nfertilizer");
		tables.add("ap_oildepot");
		tables.add("ap_outlet");
		tables.add("ap_outlet_fill");
		tables.add("ap_pesticide");
		tables.add("ap_plane");
		tables.add("ap_product_fill");
		tables.add("ap_road_dust");
		tables.add("ap_smallFacility");
		tables.add("ap_stat_device");
		tables.add("ap_stat_gov");
		tables.add("ap_stat_gov_pc");
		tables.add("ap_stat_motor");
		tables.add("ap_stat_motor_pc");
		tables.add("ap_stat_product");
		tables.add("ap_vehicle_action");
		tables.add("ap_vehicle_factor");
		tables.add("ap_vehicle_repairing");
	}

	private static SeperateTable self = null;

	public static SeperateTable instance() {

		if (self == null) {
			self = new SeperateTable();
		}

		return self;
	}

	public ArrayList<String> get() {
		return tables;
	}
	
	public ArrayList<String> getYears() {
		ArrayList<String> years = new ArrayList<String>();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - 1;
		
		for(int y=2015; y <= year; y++) {
			years.add(String.valueOf(y));
		}
		
		return years;
	}

}
