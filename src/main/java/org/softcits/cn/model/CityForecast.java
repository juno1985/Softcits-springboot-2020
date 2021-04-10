package org.softcits.cn.model;

import java.util.List;

public class CityForecast {
	
	String city_name;
	List<Forecast> forcastList;
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public List<Forecast> getForcastList() {
		return forcastList;
	}
	public void setForcastList(List<Forecast> forcastList) {
		this.forcastList = forcastList;
	}
	
	

}
