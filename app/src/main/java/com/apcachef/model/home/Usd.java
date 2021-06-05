package com.apcachef.model.home;

import com.google.gson.annotations.SerializedName;

public class Usd{

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("code")
	private String code;

	@SerializedName("price")
	private String price;

	@SerializedName("line")
	private String line;

	@SerializedName("line_sm")
	private String lineSm;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setLine(String line){
		this.line = line;
	}

	public String getLine(){
		return line;
	}

	public void setLineSm(String lineSm){
		this.lineSm = lineSm;
	}

	public String getLineSm(){
		return lineSm;
	}
}