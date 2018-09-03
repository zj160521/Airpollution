package com.xf.entity;

public class Enterprise {

	private int id;
	private String name;
	private int town;
	private int city;
	private double e_point;
	private double n_point;
	
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public double getE_point() {
		return e_point;
	}
	public void setE_point(double e_point) {
		this.e_point = e_point;
	}
	public double getN_point() {
		return n_point;
	}
	public void setN_point(double n_point) {
		this.n_point = n_point;
	}
	
}
