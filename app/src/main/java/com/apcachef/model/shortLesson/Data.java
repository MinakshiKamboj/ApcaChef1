package com.apcachef.model.shortLesson;

import java.util.List;

import com.apcachef.model.home.Inr;
import com.apcachef.model.home.Myr;
import com.apcachef.model.home.Usd;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("courses_types")
	private List<CoursesTypesItem> coursesTypes;

	@SerializedName("rows")
	private List<RowsItem> rows;

	@SerializedName("usd")
	private Usd usd;

	@SerializedName("myr")
	private Myr myr;

	@SerializedName("inr")
	private Inr inr;

	public void setCoursesTypes(List<CoursesTypesItem> coursesTypes){
		this.coursesTypes = coursesTypes;
	}

	public List<CoursesTypesItem> getCoursesTypes(){
		return coursesTypes;
	}

	public void setRows(List<RowsItem> rows){
		this.rows = rows;
	}

	public List<RowsItem> getRows(){
		return rows;
	}

	public Usd getUsd() {
		return usd;
	}

	public void setUsd(Usd usd) {
		this.usd = usd;
	}

	public Myr getMyr() {
		return myr;
	}

	public void setMyr(Myr myr) {
		this.myr = myr;
	}

	public Inr getInr() {
		return inr;
	}

	public void setInr(Inr inr) {
		this.inr = inr;
	}
}