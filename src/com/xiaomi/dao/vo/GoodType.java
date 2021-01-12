package com.xiaomi.dao.vo;

public class GoodType {
	private String goodType;
	private double goodPrice;
	
	
	public GoodType() {
		super();
	}
	public GoodType(String goodType, double goodPrice) {
		super();
		this.goodType = goodType;
		this.goodPrice = goodPrice;
	}
	public String getGoodType() {
		return goodType;
	}
	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	
}
